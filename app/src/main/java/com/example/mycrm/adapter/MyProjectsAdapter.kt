package com.example.mycrm.adapter

import android.annotation.SuppressLint
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
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.mycrm.ProjectsActivity
import com.example.mycrm.TasksActivity
import com.example.mycrm.databinding.ItemLayoutBinding

class MyProjectsAdapter (var context: Context) : ListAdapter<Project, MyProjectsAdapter.Holder>(Comparator()) {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemLayoutBinding.bind(view)
        var panel = binding.lay
        var ctitle = binding.ttitle
        var cid = binding.twid
        var cdescr = binding.descr
        var cstatus = binding.status

        fun bind(project: Project) = with(binding) {
            ttitle.text = project.title
            descr.text= project.descr
            status.text = project.status
            cid.text = project.id.toString()
        }
    }

    class Comparator : DiffUtil.ItemCallback<Project>(){
        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem.equals(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
        holder.panel.setOnClickListener{
            val int = Intent(context, TasksActivity::class.java)
            int.putExtra("itemId", holder.cid.text)
            int.putExtra("itemTitle", holder.ctitle.text)
            int.putExtra("itemDescr", holder.cdescr.text)
            int.putExtra("itemStatus", holder.cstatus.text)
            context.startActivity(int)
        }
    }
}