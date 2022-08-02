package com.example.learningplat.presentation.ui.courseslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.learningplat.databinding.CoursesLoadingStateLayoutBinding

class CoursesLoadStateAdapter(private val retry :()->Unit):LoadStateAdapter<CoursesLoadStateAdapter.CoursesLoadStateViewHolder>()
{

   inner class CoursesLoadStateViewHolder(private val binding:CoursesLoadingStateLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {
        init {
            binding.retryButton.setOnClickListener {
               retry.invoke()
            }
        }

        fun bind(loadState: LoadState)
        {

            binding.apply {

                if (loadState is LoadState.Error)
                errorText.text =loadState.error.localizedMessage

                loadingImage.isVisible = loadState is LoadState.Loading
                errorText.isVisible = loadState is LoadState.Error
                retryButton.isVisible = loadState is LoadState.Error
            }
        }

    }

    override fun onBindViewHolder(holder: CoursesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CoursesLoadStateViewHolder {
        val binding = CoursesLoadingStateLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CoursesLoadStateViewHolder(binding)
    }

}