package com.example.tddthreeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager // <-- MISSING
import androidx.recyclerview.widget.RecyclerView // <-- MISSING

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val count = intent.getIntExtra("COUNT", 0)

        // Use the proper type argument for findViewById
        val recycler = findViewById<RecyclerView>(R.id.recyclerView)

        // Resolve layoutManager and LinearLayoutManager
        recycler.layoutManager = LinearLayoutManager(this)

        val app = application as MyApplication

        app.listLoader.loadList(count) { items ->
            // Ensure this runs on the main thread since it updates the UI
            runOnUiThread {
                // Resolve adapter and MyAdapter (assuming MyAdapter is implemented below)
                recycler.adapter = MyAdapter(items) { clickedItem ->

                    // Resolve ThirdActivity and Intent
                    val intent = Intent(this, ThirdActivity::class.java)

                    // Resolve putExtra and .text (assuming ListItem has a 'text' property)
                    intent.putExtra("ITEM_TEXT", clickedItem.text)

                    startActivity(intent)
                }
            }
        }
    }
}