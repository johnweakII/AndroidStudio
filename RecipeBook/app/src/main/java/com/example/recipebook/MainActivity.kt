package com.example.recipebook

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RecipeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var etTitle: EditText
    private lateinit var etDesc: EditText
    private lateinit var btnAddSavory: Button
    private lateinit var btnAddSweet: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        etTitle = findViewById(R.id.editTextTitle)
        etDesc = findViewById(R.id.editTextDescription)
        btnAddSavory = findViewById(R.id.buttonAddSavory)
        btnAddSweet = findViewById(R.id.buttonAddSweet)

        adapter = RecipeAdapter(mutableListOf(), onRecipeClick = { recipe ->
            AlertDialog.Builder(this)
                .setTitle(recipe.title)
                .setMessage(recipe.description.ifEmpty { "(no description)" })
                .setPositiveButton("OK", null)
                .show()
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.addRecipe(Recipe("Spicy Chili", "A hearty spicy chili recipe", Flavor.SAVORY))
        adapter.addRecipe(Recipe("Apple Pie", "Sweet apple pie with cinnamon", Flavor.SWEET))

        btnAddSavory.setOnClickListener { addRecipeClicked(Flavor.SAVORY) }
        btnAddSweet.setOnClickListener { addRecipeClicked(Flavor.SWEET) }

        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val item = adapter.getItem(pos)
                if (item is ListItem.RecipeItem) {
                    adapter.removeAt(pos)
                } else {
                    // rebind header (prevent removal)
                    adapter.notifyItemChanged(pos)
                }
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(recyclerView)
    }

    private fun addRecipeClicked(flavor: Flavor) {
        val title = etTitle.text.toString().trim()
        val desc = etDesc.text.toString().trim()

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }

        val recipe = Recipe(title, desc, flavor)
        val insertedPos = adapter.addRecipe(recipe)
        etTitle.text.clear()
        etDesc.text.clear()
        recyclerView.scrollToPosition(insertedPos)
    }
}
