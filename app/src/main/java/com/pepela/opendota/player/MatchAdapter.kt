package com.pepela.opendota.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pepela.data.match.model.Match
import com.pepela.data.match.model.Side
import com.pepela.opendota.R
import com.pepela.opendota.extension.setBackgroundColorRes

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    var items: List<Match> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = items[position]
        with(match) {
            holder.matchIdTv.text = id.toString()

            holder.itemView.setBackgroundColorRes(if (side == Side.RADIANT && radiantWin)
                R.color.green else R.color.red)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val matchIdTv: TextView = view.findViewById(R.id.match_id_tv)
    }

}
