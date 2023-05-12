package com.example.todoappwithroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithroom.R
import com.example.todoappwithroom.model.Task
import com.example.todoappwithroom.view.ToDoListFragment
import com.example.todoappwithroom.view.ToDoListFragmentDirections

class RwAdapter:RecyclerView.Adapter<RwAdapter.MyViewHolder>() {
    var taskList = emptyList<Task>()
    class MyViewHolder (view: View):RecyclerView.ViewHolder(view){
        val taskName =view.findViewById<TextView>(R.id.taskNameRow)
        val taskDesc=view.findViewById<TextView>(R.id.taskDescRow)
        val layoutP = view.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.layoutP)


        fun bind(task: Task){
            taskName.setText(task.taskName)
            taskDesc.setText(task.taskDesc)

            when (task.priority) {
                "Low" -> layoutP.setBackgroundResource(R.color.lowPriority)
                "Medium" -> layoutP.setBackgroundResource(R.color.mediumPriority)
                "High" -> layoutP.setBackgroundResource(R.color.highPriority)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
        return MyViewHolder(view)    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTask=taskList.get(position)
        holder.bind(currentTask)
        holder.itemView.setOnClickListener {
           val action= ToDoListFragmentDirections.actionToDoListFragmentToToDoUpdateFragment(currentTask)
            holder.itemView.findNavController().navigate(action)
        }
    }



    override fun getItemCount(): Int {
           return taskList.size
    }

    fun updateData(list : List<Task>){
        taskList=list
        notifyDataSetChanged()

    }
}