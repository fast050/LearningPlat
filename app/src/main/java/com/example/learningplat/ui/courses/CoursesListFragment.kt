package com.example.learningplat.ui.courses

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import com.example.learningplat.CoursesApplication
import com.example.learningplat.R
import com.example.learningplat.databinding.FragmentCoursesListBinding
import com.example.learningplat.ui.adapter.CoursesAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


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
                    viewModel.query.value = it
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun setUpRecyclerView() {

        val adapter = CoursesAdapter { courseClicked ->
            val action =
                CoursesListFragmentDirections.actionCoursesListFragmentToCourseDetailFragment(
                    courseClicked, courseClicked.title ?: "Course"
                )
            findNavController().navigate(action)

        }
        binding.courseRecyclerView.adapter = adapter


        viewModel.pagingCourses.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }



        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.messageToUser.text = it
        }


        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {

                ConnectionState.LOADING -> {
                    binding.messageToUser.visibility = View.GONE
                    binding.courseRecyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE

                }

                ConnectionState.SUCCEED -> {
                    binding.messageToUser.visibility = View.GONE
                    binding.courseRecyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE

                }

                ConnectionState.FAILED -> {
                    binding.messageToUser.visibility = View.VISIBLE
                    binding.courseRecyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.Free_type -> {
                viewModel.priceType.value = Price.FREE
                true
            }
            R.id.Paid_type -> {
                viewModel.priceType.value = Price.PAID
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }


}