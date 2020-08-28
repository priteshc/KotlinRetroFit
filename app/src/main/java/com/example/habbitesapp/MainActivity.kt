package com.example.habbitesapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habbitesapp.adapter.HabbitAdapter
import com.example.habbitesapp.model.DataModel
import com.example.habbitesapp.model.Details
import com.example.habbitesapp.retrofit.ApiClient
import com.example.habbitesapp.util.PrefManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

   lateinit var prefs : PrefManager
   lateinit var list : ArrayList<Details>
    lateinit var progerssProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = PrefManager(this)

        rv.layoutManager = LinearLayoutManager(this)

        add.setOnClickListener {
            val intent = Intent(this,AddNewHabbit::class.java)
            startActivity(intent)
        }

        list = arrayListOf()

        test()?.let {
            list = test()
            val adapter = HabbitAdapter()
            adapter.setlist(list)

            //now adding the adapter to recyclerview
            rv.adapter = adapter

        }


        list?.let {
            rv.visibility = View.VISIBLE
            tv_no_data.visibility = View.GONE
        }?: run {
            rv.visibility = View.GONE
            tv_no_data.visibility = View.VISIBLE
        }


        progerssProgressDialog=ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getData()

    }


    val test :()-> ArrayList<Details> = {
        val gson = Gson()
        val clist = prefs.list1
        val type: Type = object : TypeToken<List<Details?>?>() {}.type
        clist?.let {
            if(!TextUtils.isEmpty(it))
            list = gson.fromJson(clist, type)
        }
        list
    }


    fun getData(){

        val call: Call<List<DataModel>> = ApiClient.getclient.getPhotos()
        call.enqueue(object : Callback<List<DataModel>> {

            override fun onResponse(call: Call<List<DataModel>>?, response: Response<List<DataModel>>?) {
                progerssProgressDialog.dismiss()
                println(response!!.body()!!.size)

            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
                println("error")
            }

        })

    }
}