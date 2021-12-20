package com.example.learningplat.ui.courses

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.learningplat.CoursesApplication
import com.example.learningplat.R
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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let {
                    viewModel.query.value=it
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun setUpRecyclerView() {

        val adapter = CoursesAdapter { position ->
            val course = viewModel.getCourses.value?.get(position)
            val action =
                CoursesListFragmentDirections.actionCoursesListFragmentToCourseDetailFragment(
                    course, course?.title ?: "Course"
                )
            findNavController().navigate(action)
        }
        binding.courseRecyclerView.adapter = adapter



        viewModel.getCourses.observe(viewLifecycleOwner) {
            if(binding.messageToUser.isVisible)
            binding.messageToUser.visibility = View.INVISIBLE

            adapter.submitList(it)
        }

        viewModel.getCoursesByPrice.observe(viewLifecycleOwner)
        {
            adapter.submitList(it)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.messageToUser.visibility = View.VISIBLE
            binding.messageToUser.text = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.Free_type -> {
                viewModel.priceType.value=Price.FREE
                true
            }
            R.id.Paid_type -> {
                viewModel.priceType.value=Price.PAID
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }


}