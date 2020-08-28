package com.example.habbitesapp.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habbitesapp.R
import com.example.habbitesapp.model.Details
import kotlinx.android.synthetic.main.childview.view.*

class HabbitAdapter : RecyclerView.Adapter<HabbitAdapter.Moviholder>() {
   lateinit var datalist : ArrayList<Details>

    fun setlist(mylist : ArrayList<Details>){
        this.datalist = mylist
       notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabbitAdapter.Moviholder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.childview, parent, false)
        return Moviholder(v)

    }

    override fun getItemCount(): Int {

        return datalist.size
    }

    override fun onBindViewHolder(holder: HabbitAdapter.Moviholder, position: Int) {

        holder.bind(datalist[position])

    }


  class Moviholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      fun bind(movie: Details) {
         itemView.txt1?.text = movie.title
          itemView.txt2?.text = movie.designation
      }

  }

}