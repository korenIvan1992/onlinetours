package com.onlinetours.data.db.manager

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("CommitPrefEdits")
class PreferenceManager
constructor(context: Context) {

    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        pref = context.getSharedPreferences(PREF_NAME, 0)
        editor = pref.edit()
    }

    companion object {
        private const val PREF_NAME = "PreferenceManager.ONLINE_TOURS"
        private const val TEST = "PreferenceManager.ONLINE_TOURS.TEST"
    }

    fun setTestPref(test: String) {
        editor.putString(TEST, test)
        editor.commit()
    }

    fun getTestPref(): String? {
        return pref.getString(TEST, "seef")
    }

}
