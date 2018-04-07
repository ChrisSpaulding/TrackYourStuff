package com.example.maheshbhattarai.sqlite_database_demo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.SharedPreferences
import android.R.id.edit
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.location.*
import java.nio.file.Files.size
import android.util.Log
import android.widget.Toast
import com.example.maheshbhattarai.sqlite_database_demo.R.layout.activity_locationlogger
import java.io.IOException
import java.util.*
import android.location.LocationManager
import android.provider.Settings.Secure
import android.provider.Settings.Secure.isLocationProviderEnabled
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.example.maheshbhattarai.sqlite_database_demo.R.id.locationTextView
import kotlinx.android.synthetic.main.activity_locationlogger.*


class LocationLogger : AppCompatActivity() {
    var permissionResults : Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locationlogger)

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationListener = MyLocationListener()


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions( this,  arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),permissionResults )

            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 10f, locationListener)
        }

        locationTextView.setText("Location: \n Long: ${locationListener.longitude} \n Lat: ${locationListener.latitude}")
}





    }

 class MyLocationListener:LocationListener {
    var longitude : Double =0.0
     var latitude : Double =0.0
override fun onLocationChanged(loc: Location) {
longitude = loc.longitude
Log.v(TAG, longitude.toString())
latitude = loc.latitude
Log.v(TAG, latitude.toString())



}

override fun onProviderDisabled(provider:String) {}

override fun onProviderEnabled(provider:String) {}

override fun onStatusChanged(provider:String, status:Int, extras:Bundle) {}
}