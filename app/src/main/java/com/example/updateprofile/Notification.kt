package com.example.updateprofile

import android.app.Activity
import android.widget.Toast

object Notification {

    fun showToast(activity: Activity, notif : String){
        Toast.makeText(activity,notif,Toast.LENGTH_SHORT).show()
    }
}