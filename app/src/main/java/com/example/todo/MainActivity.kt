package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val listOfTasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfTasks.add("laundry")
        listOfTasks.add("Check 1")

        //Look up recycler view in layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        //Create adapter to pass in values
        val adapter = TaskItemAdapter(listOfTasks)
        //attach adapter to recycler view to populate itemes
        recyclerView.adapter = adapter
        recyclerView.layoutManager = (LinearLayoutManager(this))


        //Event : User Clicks "Add"
/*        findViewById<Button>(R.id.button).setOnClickListener{
            //Code executed when "Add" clicked
            Log.i("Kole","User clicked Add")
        }*/


    }
}