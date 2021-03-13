package com.example.backgroundtask1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Message
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

class PhoneReceiverActivity: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {

            if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                Toast.makeText(context,"Incoming Call..",Toast.LENGTH_LONG).show()
                /*showToast(context,"Incoming Call")
                PHONE_RECEIVER = 20.toString()
                Log.e("onReceive","Phone receiver Triggered $PHONE_RECEIVER")
*/
        }
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}