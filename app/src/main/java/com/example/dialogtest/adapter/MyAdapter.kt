package com.example.dialogtest.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dialogtest.R
import com.example.dialogtest.entity.Gift
import com.example.dialogtest.entity.GiftRecord
import com.example.dialogtest.entity.Item

class MyAdapter(val context:Context,val data:List<GiftRecord>): RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.tv_gift_name)
        val image: ImageView = view.findViewById(R.id.iv_gift_image)
        val corner: TextView = view.findViewById(R.id.tv_tag)
        val count: TextView = view.findViewById(R.id.tv_gift_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val gift = data[position]

        holder.name.text = gift.getGiftInfo().gift_name;
//        holder.image.setImageResource(Glide.with(context).load(gift.getGiftInfo().gift_url).into(image))
        Glide.with(context).load(gift.getGiftInfo().gift_url).centerCrop().into(holder.image);
        val giftDesc = gift.getGiftInfo().gift_desc ?: ""
        holder.corner.text = giftDesc
        if(holder.corner.text.equals("")){
            holder.corner.visibility = View.INVISIBLE
        } else{
            holder.corner.visibility = View.VISIBLE
        }
        holder.count.text = gift.gift_num+"个";
    }
}