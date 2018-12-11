package com.example.ubnt.blowingcandle

import android.app.admin.DeviceAdminReceiver
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast



class ScreenOffAdminReciever : DeviceAdminReceiver() {


    private fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onEnabled(context: Context, intent: Intent) {
        showToast(context,
                "设备管理器使能")
    }

    override fun onDisabled(context: Context, intent: Intent) {
        showToast(context,
                "设备管理器没有使能")
    }
}
