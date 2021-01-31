package com.example.meine_zitaten

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.zitate.view.*


class ZitateViewPagerAdapter : RecyclerView.Adapter<ZitateViewPagerAdapter.ZitateViewHolder>() {

    private var zitaten = emptyList<Zitate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZitateViewHolder {

        val zitateView = LayoutInflater.from(parent.context).inflate(R.layout.zitate, parent, false)
            return ZitateViewHolder(zitateView)
    }

    override fun getItemCount()= zitaten.size

    override fun onBindViewHolder(holder: ZitateViewHolder, position: Int) {

        with(zitaten[position]){
            holder.content.text = text
            holder.author.text = author
            holder.year.text = year
        }
    }

    fun setZitate(zitaten: List<Zitate>){// damit wir in der Lage sind, von außen die Zitate des Adapters zu setzen
        this.zitaten = zitaten
        notifyDataSetChanged()
    }

    inner class ZitateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val content = itemView.textView1
        val author = itemView.textView2
        val year = itemView.textView3
    }

}