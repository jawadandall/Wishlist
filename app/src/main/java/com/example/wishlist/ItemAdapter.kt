package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter (private val items: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemNameTextView: TextView
        val itemUrlTextView: TextView
        val itemPriceTextView: TextView

        init {
            itemNameTextView =itemView.findViewById(R.id.itemNameTv)
            itemPriceTextView = itemView.findViewById(R.id.itemPriceTv)
            itemUrlTextView = itemView.findViewById(R.id.itemUrlTv)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        // Set item views based on views and data model
        holder.itemNameTextView.text = item.itemName
        holder.itemPriceTextView.text = item.itemPrice
        holder.itemUrlTextView.text = item.itemUrl

        holder.itemView.setOnLongClickListener{
            items.removeAt(holder.adapterPosition)
            this.notifyDataSetChanged()
            true
        }

        holder.itemView.setOnClickListener{
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.itemUrl))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.itemName, Toast.LENGTH_LONG).show()
            }
        }
    }

}