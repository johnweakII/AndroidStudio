package com.example.mywatertracker

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.*
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) finish()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ✅ Request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        findViewById<Button>(R.id.btnAddWater).setOnClickListener {
            startWaterService() // ✅ Start foreground service only after user click
        }
    }

    private fun startWaterService() {
        val intent = Intent(this, WaterTrackerService::class.java)
        ContextCompat.startForegroundService(this, intent)
    }
}
