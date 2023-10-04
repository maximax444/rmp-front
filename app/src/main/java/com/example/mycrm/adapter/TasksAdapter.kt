package com.example.mycrm.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycrm.Project
import com.example.mycrm.R
import com.example.mycrm.Task
import com.example.mycrm.TasksActivity
import com.example.mycrm.databinding.ActivityTasksBinding

class TasksAdapter (var context: Context) : ListAdapter<Task, TasksAdapter.Holder>(Comparator()) {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ActivityTasksBinding.bind(view)
        var panel = binding.tasw
        var ctitle = binding.textView
        var cdescr = binding.textView2
        var cstatus = binding.textView3

        fun bind(task: Task) = with(binding) {
            textView.text = task.title
            textView2.text= task.descr
            textView3.text = task.status

        }
    }

    class Comparator : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.equals(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_in_list, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
//        holder.panel.setOnClickListener{
//            val int = Intent(context, TasksActivity::class.java)
//            int.putExtra("itemTitle", holder.ctitle.text)
//            int.putExtra("itemDescr", holder.cdescr.text)
//            int.putExtra("itemStatus", holder.cstatus.text)
//            context.startActivity(int)
//        }
    }
}