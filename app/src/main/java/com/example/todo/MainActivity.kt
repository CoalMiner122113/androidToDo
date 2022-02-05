package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset




class MainActivity : AppCompatActivity() {

    //vars
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                //Remove Item From List
                listOfTasks.removeAt(position)
                //Update adapter
                adapter.notifyDataSetChanged()
                //save changes
                saveItems()
            }

        }

        loadItems()

        //Look up recycler view in layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        //Create adapter to pass in values
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)
        //attach adapter to recycler view to populate itemes
        recyclerView.adapter = adapter
        recyclerView.layoutManager = (LinearLayoutManager(this))

        val inputTextField = findViewById<EditText>(R.id.addTaskField)
        //Setup button/input field for custom items
        //Event : User Clicks "Add"
        findViewById<Button>(R.id.button).setOnClickListener{

            //grab user text from @id/addTaskField
            val userInputTask = inputTextField.text.toString()

            //add string to list of tasks
            listOfTasks.add(userInputTask)

            //Update Adapter
            adapter.notifyItemInserted(listOfTasks.size -1)

            //clear text field
            inputTextField.setText("")

            //save changes
            saveItems()
        }
    }


    //Save data user has inputted via read/write files
    //Method to fetch file
    fun getDataFile() : File {
        //Every line represents a separate task
        return File(filesDir, "data.txt")
    }

    //Method to read file and load items
    fun loadItems() {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException){
            ioException.printStackTrace()
        }

    }
    //Method to write file and save items
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException){
            ioException.printStackTrace()
        }
    }
}