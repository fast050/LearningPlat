package com.example.learningplat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.learningplat.databinding.LoadingStateLayoutBinding

class CoursesLoadStateAdapter(private val retry :()->Unit):LoadStateAdapter<CoursesLoadStateAdapter.CoursesLoadStateViewHolder>()
{

   inner class CoursesLoadStateViewHolder(private val binding:LoadingStateLayoutBinding):RecyclerView.ViewHolder(binding.root)
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
                errorMsg.text =loadState.error.localizedMessage

                loadingImage.isVisible = loadState is LoadState.Loading
                errorMsg.isVisible = loadState is LoadState.Error
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
        val binding = LoadingStateLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CoursesLoadStateViewHolder(binding)
    }

}