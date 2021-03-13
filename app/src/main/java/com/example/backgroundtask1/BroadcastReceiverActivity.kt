package com.example.backgroundtask1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log
import android.widget.Toast

//Register receiver  for  battery status.

class BroadcastReceiverActivity : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent?.action == "android.intent.action.BATTERY_CHANGED"){
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)
            BATTERY_STATUS = level.toString().plus("").plus("%")
            Log.e("Battery", "onReceive: Battery Level $level")
            Toast.makeText(context,"Broadcast Receiver Triggered $level",Toast.LENGTH_SHORT).show()
        }
    }
}