package com.example.learningplat.ui.courseslist

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
import com.example.learningplat.data.model.courseslist.Courses


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
                courseTextTitle.text = course.title
                Glide.with(binding.root).load(course.image480x270).into(courseImageCoursePicture)
                courseTextInstructorName.text = resources.getString(R.string.with_instructor,course.visibleInstructors?.get(0)?.name)
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