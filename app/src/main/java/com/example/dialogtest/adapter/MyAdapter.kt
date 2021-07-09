package com.example.dialogtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dialogtest.R
import com.example.dialogtest.entity.Item

class MyAdapter(val context:Context,val data:List<Item>): RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.name)
        val image: ImageView = view.findViewById(R.id.image)
        val price: TextView = view.findViewById(R.id.price)
        val button: Button = view.findViewById(R.id.buyButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        val holder = ViewHolder(view)
        holder.button.setOnClickListener {
            var position = holder.adapterPosition
            var fruit = data[position]
            Toast.makeText(context,"购买了"+fruit.name+",价格为"+fruit.price,Toast.LENGTH_SHORT).show()
        }
        return holder
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val fruit = data[position]
        holder.name.text = fruit.name
        holder.price.text = fruit.price.toString()
        holder.image.setImageResource(fruit.image)
    }
}