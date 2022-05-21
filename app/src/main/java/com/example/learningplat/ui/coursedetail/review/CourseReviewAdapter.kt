package com.example.learningplat.ui.coursedetail.review

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.learningplat.data.model.coursesreview.CourseReviews
import com.example.learningplat.data.model.coursesreview.toLastTime
import com.example.learningplat.databinding.CourseReviewItemBinding


class CourseReviewAdapter() :
    ListAdapter<CourseReviews, CourseReviewAdapter.CourseReviewViewHolder>(Diff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CourseReviewItemBinding.inflate(layoutInflater, parent, false)
        return CourseReviewViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CourseReviewViewHolder, position: Int) {
            val item = getItem(position)
            holder.bind(item)
        }



    inner class CourseReviewViewHolder(private val binding: CourseReviewItemBinding, context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        private val resources: Resources = context.resources

        fun bind(courseReview: CourseReviews) {
            binding.apply {
                userNameText.text = courseReview.user.display_name
                userCommentText.text =courseReview.content
                userRateRatingBar.rating = courseReview.rating.toFloat()
                userRateText.text = courseReview.rating.toString()
                commentTimeText.text = courseReview.modified.toLastTime()
            }
        }
    }


    object Diff : DiffUtil.ItemCallback<CourseReviews>() {

        override fun areItemsTheSame(oldItem: CourseReviews, newItem: CourseReviews): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CourseReviews, newItem: CourseReviews): Boolean =
            oldItem == newItem

    }


}

