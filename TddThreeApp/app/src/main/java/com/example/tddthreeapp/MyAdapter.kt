package com.example.tddthreeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Define a click listener type (ListItem is the required type)
typealias ClickListener = (ListItem) -> Unit

class MyAdapter(
    private val items: List<ListItem>,
    private val clickListener: ClickListener // The callback function
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // Inner class for the ViewHolder
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // You'll need to create a new layout file: item_list.xml
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item.text

        // Attach click listener
        holder.itemView.setOnClickListener {
            clickListener(item) // Pass the clicked ListItem back to the Activity
        }
    }

    override fun getItemCount(): Int = items.size
}