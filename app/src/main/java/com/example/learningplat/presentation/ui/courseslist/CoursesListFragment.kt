package com.example.learningplat.presentation.ui.courseslist

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.learningplat.R
import com.example.learningplat.databinding.FragmentCoursesListBinding
import com.example.learningplat.presentation.ui.SharedViewModel
import com.example.learningplat.presentation.ui.courseslist.filter_dialog.FilterDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoursesListFragment : Fragment() {

    private var _binding: FragmentCoursesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CoursesViewModel>()
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    private val TAG = "CoursesListFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCoursesListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.setting.observe(viewLifecycleOwner){
            Log.d(TAG, "onViewCreated: $it")
        }

        setUpRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        //search courses list
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

            sharedViewModel.setCourseDetail(courseClicked)

            val action =
                CoursesListFragmentDirections.actionCoursesListFragmentToCourseDetailFragment(
                    courseClicked.courseTitle,courseClicked)
            findNavController().navigate(action)

        }



        binding.courseRecyclerView.adapter = adapter.withLoadStateFooter(
            CoursesLoadStateAdapter{adapter.retry()}
        )

        viewModel.pagingCourses.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->

            binding.messageToUser.isVisible = loadState.source.refresh is LoadState.Error
            binding.courseRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading


        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //filter courses list by (free or paid) price
        return when (item.itemId) {
            R.id.Flitter -> {
                FilterDialog().show(childFragmentManager, "FilterFragment")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}