package com.example.learningplat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learningplat.databinding.CourseItemBinding
import com.example.learningplat.model.Courses


class CoursesAdapter(val listener : (Int)->(Unit)) : ListAdapter<Courses, RecyclerView.ViewHolder>(Diff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CourseItemBinding.inflate(layoutInflater, parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CourseViewHolder) {
            val item = getItem(position)
            holder.bind(item)
        }
    }


   inner class CourseViewHolder(private val binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.root.setOnClickListener {
                listener(adapterPosition)
            }
        }

        fun bind(course: Courses) {

            binding.apply {
                courseTextTitle.text = course.title
                Glide.with(binding.root).load(course.image480x270).into(courseImageCoursePicture)
                courseTextInstructorName.text = "with ${course.visibleInstructors?.get(0)?.name}"
            }
        }
    }


    object Diff : DiffUtil.ItemCallback<Courses>() {

        override fun areItemsTheSame(oldItem: Courses, newItem: Courses): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Courses, newItem: Courses): Boolean =
            oldItem == newItem

    }


}