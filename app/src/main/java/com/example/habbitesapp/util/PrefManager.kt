package com.example.habbitesapp.util

import android.content.Context
import android.content.SharedPreferences
import com.example.habbitesapp.model.Details

class PrefManager(context: Context) {
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "Habbit"
    val MYTEST = "mytest"
    val MYLIST = "list"
    val pref:SharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    var test: String?
    get() = pref.getString(MYTEST,"")
    set(value) = pref.edit().putString(MYTEST,value).apply()

        var list1: String?
        get() = pref.getString(MYLIST,"")
        set(value) = pref.edit().putString(MYLIST,value).apply()


}