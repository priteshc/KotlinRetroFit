package com.example.habbitesapp

import android.app.Application

class Myapp : Application() {

    companion object{
        var BASE_URL:String="https://jsonplaceholder.typicode.com/"
        lateinit var instance: Myapp
           // private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}