package com.example.habbitesapp

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.habbitesapp.model.Details
import com.example.habbitesapp.util.PrefManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.add_new_habbits.*
import java.lang.reflect.Type


class AddNewHabbit : AppCompatActivity() {
    lateinit var list : ArrayList<Details>
  lateinit var pref : PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_habbits)

        pref = PrefManager(this)

        list = arrayListOf()


        pref.list1?.let {

            if(!TextUtils.isEmpty(it)) {
                test()
            }
        }


        submit.setOnClickListener {
           val mydata = Details(title1.text.toString(),design.text.toString(),"mypath")
           list.add(mydata)
           val gson = Gson()
           val json = gson.toJson(list)
           pref.list1 = json
       }

    }

    val test: () -> ArrayList<Details>? = {
        val gson = Gson()
        val clist = pref.list1
        val type: Type = object : TypeToken<List<Details?>?>() {}.type
            list = gson.fromJson(clist, type)
            list
    }
}