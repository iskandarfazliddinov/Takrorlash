package com.example.takrorlash.Adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.takrorlash.Data.Data
import com.example.takrorlash.R
import kotlinx.android.synthetic.main.item_user.view.*

class Adapter( val data :ArrayList<Data>):RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var listener:((Int,Int,String)->Unit)? = null

    data class ViewHolder(val view :View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val image :ImageView = findViewById(R.id.user_img)
            image.setImageResource(data[position].Image_data)
            user_text_1.text =data[position].Text_data
        }
        holder.itemView.clilc_card.setOnClickListener {
            listener?.invoke(position,data[position].Image_data,data[position].Text_data)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun SetOnClilcListener(f:(Int,Int,String)->Unit){
        listener = f
    }
    fun delet(i:Int){
        data.removeAt(i)
        notifyDataSetChanged()
    }
    fun archive(i:Int , news: Data){
        data.add(i,news)
        notifyDataSetChanged()
    }
}