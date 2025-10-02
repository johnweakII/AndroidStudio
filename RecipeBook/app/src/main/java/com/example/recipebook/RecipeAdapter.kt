package com.example.recipebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val items: MutableList<ListItem> = mutableListOf(),
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_RECIPE = 1
    }

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is ListItem.Header -> TYPE_HEADER
            is ListItem.RecipeItem -> TYPE_RECIPE
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_flavor, parent, false)
            HeaderViewHolder(v)
        } else {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
            RecipeViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ListItem.Header -> (holder as HeaderViewHolder).bind(item)
            is ListItem.RecipeItem -> (holder as RecipeViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int = items.size




    fun addRecipe(recipe: Recipe): Int {
        val headerIndex = items.indexOfFirst {
            it is ListItem.Header && (it as ListItem.Header).flavor == recipe.flavor
        }

        return if (headerIndex == -1) {

            val insertHeaderIndex = when (recipe.flavor) {
                Flavor.SAVORY -> 0
                Flavor.SWEET -> {
                    val savoryIndex = items.indexOfFirst {
                        it is ListItem.Header && (it as ListItem.Header).flavor == Flavor.SAVORY
                    }
                    if (savoryIndex == -1) {
                        items.size
                    } else {
                        var idx = savoryIndex + 1
                        while (idx < items.size && items[idx] is ListItem.RecipeItem) idx++
                        idx
                    }
                }
            }

            items.add(insertHeaderIndex, ListItem.Header(recipe.flavor))
            notifyItemInserted(insertHeaderIndex)

            val recipePos = insertHeaderIndex + 1
            items.add(recipePos, ListItem.RecipeItem(recipe))
            notifyItemInserted(recipePos)
            recipePos
        } else {

            var insertPos = headerIndex + 1
            while (insertPos < items.size && items[insertPos] is ListItem.RecipeItem) insertPos++
            items.add(insertPos, ListItem.RecipeItem(recipe))
            notifyItemInserted(insertPos)
            insertPos
        }
    }

    fun removeAt(position: Int) {
        if (position < 0 || position >= items.size) return
        items.removeAt(position)
        notifyItemRemoved(position)

        // If previous item is a header and it has no recipes after it, remove header too
        val prevIndex = position - 1
        if (prevIndex >= 0 && prevIndex < items.size && items[prevIndex] is ListItem.Header) {
            if (prevIndex == items.size - 1 || items[prevIndex + 1] is ListItem.Header) {
                items.removeAt(prevIndex)
                notifyItemRemoved(prevIndex)
            }
        }
    }

    fun getItem(position: Int): ListItem? = items.getOrNull(position)



    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textFlavor: TextView = itemView.findViewById(R.id.textFlavorTitle)
        fun bind(header: ListItem.Header) {
            textFlavor.text = when (header.flavor) {
                Flavor.SAVORY -> "Savory"
                Flavor.SWEET -> "Sweet"
            }
        }
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById(R.id.textRecipeTitle)
        fun bind(recipeItem: ListItem.RecipeItem) {
            textTitle.text = recipeItem.recipe.title
            itemView.setOnClickListener {
                onRecipeClick.invoke(recipeItem.recipe)
            }
        }
    }
}
