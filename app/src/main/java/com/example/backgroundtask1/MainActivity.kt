package com.example.backgroundtask1

import android.content.*
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

fun Context.showToast(msg: String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

var BATTERY_STATUS = ""
var PHONE_RECEIVER = ""
var IS_BOUND = false



class MainActivity : AppCompatActivity() {

    private val broadcastReceiver = BroadcastReceiverActivity()
    private val phoneReceiver = PhoneReceiverActivity()
    private var myService: BoundServiceActivity?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//Use start() join() and sleep() methods in single program to execute different threads.
        val threadObj = ThreadsActivity()
        threadObj.main()


//Receiver for Incoming Calls
        phoneReceiver
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_PHONE_STATE), 1)
        }
        println(PHONE_RECEIVER)
        Log.e("phonereceiver", "phone $PHONE_RECEIVER")


//Register receiver  for battery status.
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(broadcastReceiver, intentFilter)
        Log.e("battery", "registered receiver")
        val batteryInfo = findViewById<TextView>(R.id.outputTextView)
        batteryInfo.text = "Battery Level is $BATTERY_STATUS"
        println(BATTERY_STATUS)
        Log.e("battery", "battery status is $BATTERY_STATUS")
        Log.e("battery", "textview data is ${batteryInfo.text}")



//Music Player with raw file to play song in background.
        val serviceIntent = Intent(applicationContext,MusicServiceActivity::class.java)
        startMusicButton.setOnClickListener {
            startService(serviceIntent)
        }
        stopMusicButton.setOnClickListener {
            stopService(serviceIntent)
        }



//Bind local service from activity.

        bindLocalService()
        bindLocalServiceButton.setOnClickListener {
            //getting time using local service
            showTime()

        }
    }



    private fun bindLocalService() {
        val bindIntent = Intent(applicationContext,BoundServiceActivity::class.java)
        bindService(bindIntent,myConnection,Context.BIND_AUTO_CREATE)
    }

    private val myConnection = object : ServiceConnection{
        override fun onServiceDisconnected(className: ComponentName?) {
            IS_BOUND = false
        }

        override fun onServiceConnected(name: ComponentName?, service : IBinder?) {
            val binder = service as BoundServiceActivity.LocalBinder
            myService = binder.getService()
            IS_BOUND = true
        }

    }

    private fun showTime() {
        val time = myService?.getCurrentTime()
        showToast("Current Time is:  ${time.toString()}")
    }


    //UnregisterReceiver for Battery Status
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
}