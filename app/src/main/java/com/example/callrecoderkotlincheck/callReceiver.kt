package com.example.callrecoderkotlincheck

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.view.Gravity
import android.widget.Toast
import androidx.core.content.ContextCompat

class callReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE) == TelephonyManager.EXTRA_STATE_OFFHOOK) {
            showToast(context, "Call Started...")
            val input = "This is Service Started from BCReceiver"
            val serviceIntent = Intent(context, ExampleService::class.java)
            serviceIntent.putExtra("inputExtra", "Foreground Service is running...")
            ContextCompat.startForegroundService(context, serviceIntent)

        } else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE) == TelephonyManager.EXTRA_STATE_IDLE) {
            showToast(context, "Call Ended...")
            val serviceIntent = Intent(context, ExampleService::class.java)
            context.stopService(serviceIntent)
        }
    }

    fun showToast(context: Context?, message: String?) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}
