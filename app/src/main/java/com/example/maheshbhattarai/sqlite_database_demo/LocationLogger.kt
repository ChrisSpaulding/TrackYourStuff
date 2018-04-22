package com.example.maheshbhattarai.sqlite_database_demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.*
import android.location.*
import android.util.Log
import android.widget.Toast
import android.os.Looper
import android.support.v4.app.JobIntentService
import com.example.maheshbhattarai.sqlite_database_demo.database.AppDatabase
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationListener
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.activity_locationlogger.*
import java.util.*

class LocationLogger : AppCompatActivity() {
    private var mDb: AppDatabase? = null
    lateinit var CurrentlatLng: LatLng
    lateinit var mLocationRequest: LocationRequest
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var mLocationCallback: LocationCallback
    lateinit var mSettingsClient: SettingsClient
    lateinit var mLocationSettingsRequest: LocationSettingsRequest
    private val REQUEST_CHECK_SETTINGS = 0x1
    lateinit var location: Location
    val REQUEST_LOCATION = 1011
    var currentLatitude: Double = 0.0 //currently do nothing
    var currentLongitude: Double = 0.0 //currently do nothing
    var sleepTime : Long = 60000
    private var mTrackThread : trackingThead = trackingThead()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locationlogger)
        btn_gpsLocation.setOnClickListener{
            displayGPSLocation()
        }
        mTrackThread.start()
    }

    fun storeGPSLocation(long :Double, lat : Double){
        val time = Date()
        val mLocation : com.example.maheshbhattarai.sqlite_database_demo.database.Location = com.example.maheshbhattarai.sqlite_database_demo.database.Location(time,long,lat)
        mDb = AppDatabase.getInMemoryDatabase(applicationContext)
        mDb?.locationDao()?.insertLocation(mLocation)
    }

    fun displayGPSLocation(){
       // initLocation()
        launchIntent(5)
    }

    fun launchIntent( x:Int){
        var intent : Intent = Intent()
        intent.putExtra("length",x )
        var mLoc : LocationIntentService = LocationIntentService()
    JobIntentService.enqueueWork(this,LocationIntentService::class.java, 2018, intent)
    }

    private fun initLocation()  {
        try {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this@LocationLogger)
            getLastLocation()
            try {
                mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                        .addOnSuccessListener(this, object : OnSuccessListener<LocationSettingsResponse> {
                            override fun onSuccess(p0: LocationSettingsResponse?) {
                                mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                        mLocationCallback, Looper.myLooper());
                            }
                        }).addOnFailureListener(this, object : OnFailureListener {
                            override fun onFailure(p0: java.lang.Exception) {
                                val statusCode = (p0 as ApiException).getStatusCode();
                                when (statusCode) {
                                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                                        Log.i("Location", "Location settings are not satisfied. Attempting to upgrade " +
                                                "location settings ");
                                        try {
                                            // Show the dialog by calling startResolutionForResult(), and check the
                                            // result in onActivityResult().
                                            val rae = p0 as ResolvableApiException
                                            rae.startResolutionForResult(this@LocationLogger, REQUEST_CHECK_SETTINGS);
                                        } catch (sie: IntentSender.SendIntentException) {
                                            Log.i("Location", "PendingIntent unable to execute request.");
                                        }
                                    }
                                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE ->
                                        Toast.makeText(this@LocationLogger, "Location settings are inadequate, and cannot be \"+\n" +
                                                "                                    \"fixed here. Fix in Settings.", Toast.LENGTH_LONG).show();

                                }
                            }
                        })
            } catch (unlikely: SecurityException) {
                Log.e("Location", "Lost location permission. Could not request updates. $unlikely")
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getLastLocation() {
        try {
            mFusedLocationClient.lastLocation?.addOnCompleteListener { task ->
                if (task.isSuccessful && task.result != null) {
                    location = task.result
                    CurrentlatLng = LatLng(location.latitude, location.longitude)
                    Log.e("CurrentLatLong2", "lat " + location.latitude + " long " + location.longitude)
                    currentLatitude = location.latitude
                    Log.i("Lat**", "$currentLatitude")
                    currentLongitude = location.longitude
                    Log.i("long **", "$currentLongitude")
                    displayValues(location.longitude, location.latitude)
                    "The current Latitude is $currentLatitude and the Longitude is $currentLongitude".toast(this)
                    storeGPSLocation(currentLongitude,currentLatitude)
                } else {
                    Log.w("Location", "Failed to get location.")
                }
            }
        } catch (unlikely: SecurityException) {
            Log.e("Location", "Lost location permission.$unlikely")
        }
    }

    fun displayValues(long : Double, lat :Double){
        val longTxt = "Logitude: $long"
        val latTxt = "Latitude: $lat"
        locationTextView.text= "Location:"
        longTxtView.text= longTxt
        latTxtView.text=latTxt
    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mTrackThread!!.isAlive()){
                mTrackThread?.interrupt()}
    }

    /////////////////////////////////COntinous Tracking Thread//////////////////////
    private inner class trackingThead: Thread(){


       override fun run(){
           while(!interrupted()) {
               initLocation()
               Thread.sleep(sleepTime)
               Log.i("sleep", "run run run run redrum redrum")
           }


       }


    }
}