package com.example.learningplat.ui.courses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.learningplat.CoursesApplication
import com.example.learningplat.databinding.FragmentCoursesListBinding
import com.example.learningplat.ui.adapter.CoursesAdapter


class CoursesListFragment : Fragment() {


    private lateinit var binding: FragmentCoursesListBinding
    private val viewModel: CoursesViewModel by viewModels {
        CoursesViewModel.CoursesViewModelFactory((requireActivity().application as CoursesApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCoursesListBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {
        viewModel.getCourses()

        val adapter = CoursesAdapter { position ->
            val course = viewModel.coursesList.value?.get(position)
            val action = CoursesListFragmentDirections.actionCoursesListFragmentToCourseDetailFragment(
                    course
                )
            findNavController().navigate(action)
        }
        binding.courseRecyclerView.adapter = adapter



        viewModel.coursesList.observe(viewLifecycleOwner) {
            binding.messageToUser.visibility = View.INVISIBLE
            adapter.submitList(it)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.messageToUser.visibility = View.VISIBLE
            binding.messageToUser.text = it
        }
    }


}