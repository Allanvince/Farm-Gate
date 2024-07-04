package com.vince.shambafirm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vince.shambafirm.R

class FarmRecordAdapter(private var farmRecords: List<HashMap<String, String>>) :
    RecyclerView.Adapter<FarmRecordAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.farm_record_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = farmRecords[position]

        // Bind farm record data to TextViews
        holder.farmName.text = "Farm Name:"
        holder.farmNameTextView.text = record["Farm Name"]

        holder.locationKey.text = "Farm Location:"
        holder.locationTextView.text = record["Farm Location"]

        holder.farmSizeKey.text = "Farm Size:"
        holder.farmSizeTextView.text = record["Farm Size"]

        holder.numberOfLabourersKey.text = "No. Of Labourers:"
        holder.numberOfLabourersTextView.text = record["No. Of Labourers"]

        holder.typesOfCropsGrown.text = "Types of Crops Grown:"
        holder.typesOfCropsGrownTextView.text = record["Types Of Crops"]
    }

    override fun getItemCount(): Int {
        return farmRecords.size
    }

    fun updateRecords(newRecords: List<HashMap<String, String>>) {
        farmRecords = newRecords
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val farmNameTextView: TextView = itemView.findViewById(R.id.farmNameTextView)
        val farmName: TextView = itemView.findViewById(R.id.farmNameKey)

        val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
        val locationKey: TextView = itemView.findViewById(R.id.locationNameKey)

        val farmSizeTextView: TextView = itemView.findViewById(R.id.farmSizeTextView)
        val  farmSizeKey: TextView = itemView.findViewById(R.id.farmSizeNameKey)

        val numberOfLabourersTextView: TextView = itemView.findViewById(R.id.numberOfLabourersTextView)
        val numberOfLabourersKey: TextView = itemView.findViewById(R.id.labourersNameKey)

        val typesOfCropsGrownTextView: TextView = itemView.findViewById(R.id.typesOfCropsGrownTextView)
        val  typesOfCropsGrown: TextView = itemView.findViewById(R.id.typesOfCropsNameKey)
    }
}