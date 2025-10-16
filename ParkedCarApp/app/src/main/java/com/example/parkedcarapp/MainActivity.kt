package com.example.parkedcarapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var btnParkHere: Button
    private var carMarker: Marker? = null

    // 🔹 Permission launcher
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                showUserLocation()
            } else {
                showPermissionRationale()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        btnParkHere = findViewById(R.id.btnParkedHere)

        // set up the map
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // button click → save parked car location
        btnParkHere.setOnClickListener {
            markCarAtCurrentLocation()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        checkLocationPermission()
        restoreCarLocation() // ✅ show previously parked car
    }

    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> showUserLocation()

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> showPermissionRationale()

            else -> requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun showPermissionRationale() {
        AlertDialog.Builder(this)
            .setTitle("Location permission required")
            .setMessage("We need your location to show where you parked your car.")
            .setPositiveButton("OK") { _, _ ->
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showUserLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) return

        map.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val userLatLng = LatLng(location.latitude, location.longitude)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 16f))
            }
        }
    }

    private fun markCarAtCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            checkLocationPermission()
            return
        }

        // Try getting the last known location first
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                saveAndShowCarMarker(location)
            } else {
                // 🔹 No cached location, request a fresh one
                fusedLocationClient.getCurrentLocation(
                    com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                    null
                ).addOnSuccessListener { freshLocation ->
                    if (freshLocation != null) {
                        saveAndShowCarMarker(freshLocation)
                    } else {
                        Toast.makeText(
                            this,
                            "Unable to get current location. Try moving outdoors or enabling GPS.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    // helper function
    private fun saveAndShowCarMarker(location: Location) {
        val carLatLng = LatLng(location.latitude, location.longitude)
        carMarker?.remove()

        carMarker = map.addMarker(
            MarkerOptions()
                .position(carLatLng)
                .title("My Parked Car")
                .icon(vectorToBitmapDescriptor(R.drawable.ic_car))
        )
        saveLocation(carLatLng)
        Toast.makeText(this, "Car location saved!", Toast.LENGTH_SHORT).show()
    }


    private fun saveLocation(latLng: LatLng) {
        val prefs = getPreferences(MODE_PRIVATE)
        prefs.edit().apply {
            putString("latitude", latLng.latitude.toString())
            putString("longitude", latLng.longitude.toString())
            apply()
        }
    }

    private fun vectorToBitmapDescriptor(vectorResId: Int): com.google.android.gms.maps.model.BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(this, vectorResId)
            ?: return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)

        val bitmap = android.graphics.Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            android.graphics.Bitmap.Config.ARGB_8888
        )
        val canvas = android.graphics.Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun restoreCarLocation() {
        val prefs = getPreferences(MODE_PRIVATE)
        val lat = prefs.getString("latitude", null)?.toDoubleOrNull()
        val lng = prefs.getString("longitude", null)?.toDoubleOrNull()

        if (lat != null && lng != null) {
            val carLatLng = LatLng(lat, lng)

            carMarker = map.addMarker(
                MarkerOptions()
                    .position(carLatLng)
                    .title("My Parked Car")
                    .icon(vectorToBitmapDescriptor(R.drawable.ic_car))
            )

        }
    }
}
