package com.example.todo
import android.service.autofill.TextValueSanitizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//Bride for user input to recycler view
class TaskItemAdapter(val listOfItems: List<String>, val longClickListener: OnLongClickListener) : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    interface OnLongClickListener{
        fun onItemLongClicked(position: Int)
    }
    //Provides direct ref to each view within a data item
    //Caches the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //Stores refs to elements in our layout view
        val textView: TextView

        init{
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener{
               longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }

    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    //involves populating data into the item through the holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get Data model based on position
        val item: String = listOfItems.get(position)
        //Set item views based on yout views and data model
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }


}