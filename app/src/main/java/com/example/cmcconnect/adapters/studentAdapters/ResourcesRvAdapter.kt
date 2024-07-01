package com.example.cmcconnect.adapters.studentAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmcconnect.R
import com.example.cmcconnect.model.RessourceDto

class ResourcesRvAdapter : RecyclerView.Adapter<ResourcesRvAdapter.ResourcesViewHolder>() {

    private var allResources: List<RessourceDto> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourcesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_resource_item, parent, false)
        return ResourcesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResourcesViewHolder, position: Int) {
        val resource = allResources[position]
        if (allResources != null) {
            holder.bind(resource)
        }
    }

    override fun getItemCount(): Int {
        return allResources.size ?: 0
    }
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newResources : List<RessourceDto>){
        allResources = newResources
        notifyDataSetChanged()
    }


    inner class ResourcesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon : ImageView = itemView.findViewById(R.id.icon)
        private val title : TextView = itemView.findViewById(R.id.title)
        private val date : TextView = itemView.findViewById(R.id.date)
        fun bind(resource : RessourceDto){
            if (resource.type == "pdf"){
                icon.setImageResource(R.drawable.icon_pdf)
            }else{
                icon.setImageResource(R.drawable.icon_video)
            }
            title.text = resource.title
            date.text = resource.pubDate.toString()
        }

    }


}