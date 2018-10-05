package com.pepela.opendota.player

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pepela.data.match.model.Match
import com.pepela.opendota.R

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    var items: List<Match> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = items[position]
        holder.match_id_tv.text = match.id.toString()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val match_id_tv: TextView = view.findViewById(R.id.match_id_tv)
    }

}
