package com.example.learningplat.presentation.ui.courseslist

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learningplat.R
import com.example.learningplat.databinding.CourseItemBinding
import com.example.learningplat.domain.model.Courses


class CoursesAdapter(val listener : (Courses)->(Unit)) : PagingDataAdapter<Courses, RecyclerView.ViewHolder>(
    Diff
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CourseItemBinding.inflate(layoutInflater, parent, false)
        return CourseViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CourseViewHolder) {
            val item = getItem(position)
            item?.let {
                holder.bind(it)
            }
        }
    }


   inner class CourseViewHolder(private val binding: CourseItemBinding, context : Context) :
        RecyclerView.ViewHolder(binding.root) {

       private val resources: Resources = context.resources


       init {
            binding.root.setOnClickListener {
                val coursesClicked = getItem(bindingAdapterPosition)

                coursesClicked?.let {
                    listener(it)
                }
            }
        }

        fun bind(course: Courses) {


            binding.apply {
                courseTextTitle.text = course.courseTitle
                Glide.with(binding.root).load(course.courseImage).into(courseImageCoursePicture)
                courseTextInstructorName.text = resources.getString(R.string.with_instructor,course.instructorName)
            }
        }
    }


    object Diff : DiffUtil.ItemCallback<Courses>() {

        override fun areItemsTheSame(oldItem: Courses, newItem: Courses): Boolean =
            oldItem.courseTitle == newItem.courseTitle

        override fun areContentsTheSame(oldItem: Courses, newItem: Courses): Boolean =
            oldItem == newItem

    }


}