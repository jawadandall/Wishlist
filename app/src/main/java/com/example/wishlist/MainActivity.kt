package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var items: MutableList<Item>   //Variable to store items as a list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.addItemBtn)  //Look up button in main activity
        items = mutableListOf() //Initializes items as empty mutable list of Items(type specified previously)

        val recyclerView = findViewById<RecyclerView>(R.id.wishlistRv)  //Look up recycler in main activity
        val adapter = ItemAdapter(items)    //Create adapter passing in list of items
        recyclerView.adapter = adapter  //Attach adapter to the recycler view
        recyclerView.layoutManager = LinearLayoutManager(this)  //Specify recycler viw layout type

        button.setOnClickListener{  //On button click..
            val itemName = findViewById<EditText>(R.id.itemNameEditText).text.toString()    //Store string from edit text
            val itemPrice = findViewById<EditText>(R.id.itemPriceEditText).text.toString()  //Store string from edit text
            val itemUrl = findViewById<EditText>(R.id.itemUrlEditText).text.toString()      //Store string from edit text

            val item = Item(itemName,itemPrice,itemUrl) //Create new item object with user input as paramaeters
            items.add(item)     //Add created item to list of items
            adapter.notifyDataSetChanged()  //Notify adapter list of items has changed




        }
    }
}