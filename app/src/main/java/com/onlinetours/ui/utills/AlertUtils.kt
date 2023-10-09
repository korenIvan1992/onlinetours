package com.onlinetours.ui.utills

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.onlinetours.R

object AlertUtils {


    fun showAlertNoCustom(activity: Activity?, titleId: String,callBack : DialogInterface.OnClickListener) {
        try {
            if (activity != null && !activity.isFinishing) {
                activity.runOnUiThread(Runnable {
                    val alertDialog =
                        AlertDialog.Builder(activity)
                            .setPositiveButton(activity.getString(R.string.ok), callBack)
                            .create()
                    alertDialog.setMessage(titleId)
                    if (!activity.isFinishing) alertDialog.show()
                })
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}
