package com.example.minor

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log

class SessionHandle(context: Context) {

    var session = PreferenceManager.getDefaultSharedPreferences(context)
    var reqSession:SharedPreferences.Editor = session.edit()
    fun setRegId(Id: Int){
        reqSession.putInt("Regid", Id)
        Log.i("Id",Id.toString())
        reqSession.commit()
    }

    fun getRegId():Int
    {
        return session.getInt("Regid",0)
    }
}