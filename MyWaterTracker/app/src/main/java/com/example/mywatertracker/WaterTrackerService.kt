package com.example.mywatertracker

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.*
import android.widget.Toast
import androidx.core.app.NotificationCompat

class WaterTrackerService : Service() {

    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "WaterTrackerChannel"
        const val EXTRA_ADD_WATER = "extra_add_water"
    }

    private var waterLevel = 2500.0 // Starting level (ml)
    private val handler = Handler(Looper.getMainLooper())
    private val decreaseRate = 0.144 // ml every 5 seconds

    private val decreaseTask = object : Runnable {
        override fun run() {
            waterLevel -= decreaseRate
            if (waterLevel < 0) waterLevel = 0.0
            updateNotification()
            handler.postDelayed(this, 5000)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

        // ✅ Delay startForeground slightly so system allows it
        Handler(Looper.getMainLooper()).postDelayed({
            val notification = createNotification("Starting tracker...")
            startForeground(NOTIFICATION_ID, notification)
            handler.post(decreaseTask)
        }, 1000)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val addedWater = intent?.getDoubleExtra(EXTRA_ADD_WATER, 0.0) ?: 0.0
        if (addedWater > 0) {
            waterLevel += addedWater
            updateNotification()
            Toast.makeText(this, "Added $addedWater ml!", Toast.LENGTH_SHORT).show()
        }
        return START_STICKY
    }

    private fun createNotification(content: String): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("My Water Tracker")
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun updateNotification() {
        val content = "Water level: %.1f ml".format(waterLevel)
        val notification = createNotification(content)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Water Tracker Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(decreaseTask)
    }
}
