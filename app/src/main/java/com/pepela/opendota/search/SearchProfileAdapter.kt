package com.pepela.opendota.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.pepela.data.player.model.SearchProfile
import com.pepela.opendota.R
import com.pepela.opendota.extension.loadFromUrl
import com.pepela.opendota.extension.toSimpleDateFormat
import com.pepela.opendota.extension.visible

class SearchProfileAdapter : RecyclerView.Adapter<SearchProfileAdapter.ViewHolder>() {

    var items: List<SearchProfile> = arrayListOf()
    var listener: ProfileClickListener? = null

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_profile, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = items[position]
        holder.itemView.setOnClickListener { listener?.onProfileClicked(profile) }
        holder.avatar.loadFromUrl(profile.avatar)
        holder.name.text = profile.name
        if (profile.lastPlayed != null) {
            holder.lastPlayed.visible()
            holder.lastPlayed.text = profile.lastPlayed!!.toSimpleDateFormat()
        } else {
            holder.lastPlayed.visibility = INVISIBLE
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView = itemView.findViewById(R.id.avatar_iv)
        val name: TextView = itemView.findViewById(R.id.name_tv)
        val lastPlayed: TextView = itemView.findViewById(R.id.last_player_tv)
    }

}
