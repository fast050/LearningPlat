package com.example.learningplat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learningplat.databinding.CourseItemBinding
import com.example.learningplat.model.Courses

class CoursesAdapter : ListAdapter<Courses, RecyclerView.ViewHolder>(Diff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CourseViewHolder) {
            val item = getItem(position)
            holder.bind(item)
        }
    }


    class CourseViewHolder(private val binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Courses) {

            binding.apply {
                courseTitle.text = course.title
                Glide.with(binding.root).load(course.image480x270).into(courseImage)
                courseInstructorName.text = course.publishedTitle
                coursePriceType.text = if (course.isPaid == true) "Free" else "Paid"
            }

        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CourseItemBinding.inflate(layoutInflater, parent, false)

                return CourseViewHolder(binding)
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