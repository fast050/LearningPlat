package com.example.learningplat.ui.coursedetail.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.learningplat.R
import com.example.learningplat.data.model.courseslist.Courses
import com.example.learningplat.databinding.FragmentCourseInfoBinding
import com.example.learningplat.ui.SharedViewModel
import com.example.learningplat.utils.formatURL

class CourseInfoFragment : Fragment() {

    private var _binding: FragmentCourseInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CourseInfoViewModel>()
    private val sharedViewModel by activityViewModels<SharedViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCourseInfoBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("TAG", "onViewCreated: CourseInfo had called")

        binding.apply {

            sharedViewModel.courseDetail.observe(viewLifecycleOwner){
                Log.d("TAG", "onViewCreated1: ${it.id}")
                viewModel.setCourseItem(it)
            }

          //  var courseItem : Courses? = null
            viewModel.courseItem.observe(viewLifecycleOwner){courseItem->

                val author = courseItem?.visibleInstructors?.get(0)


                courseDetailTextInstructorName.text = author?.name
                    ?: "Unknown"
                courseDetailInstructorTextTitle.text =
                    author?.jobTitle ?: "Unknown"
                courseDetailTextPrice.text = courseItem?.price
                courseDetailTextSummary.text = courseItem?.title
                Glide.with(this@CourseInfoFragment).load(courseItem?.image480x270)
                    .into(courseDetailImageCoursePicture)
                Glide.with(this@CourseInfoFragment).load(author?.image100x100)
                    .into(courseDetailImageInstructorPicture)


                courseDetailButtonApply.setOnClickListener {
                    val uri =
                        Uri.parse(courseItem?.url?.formatURL()) // missing 'http://' will cause crashed
                    uri?.let {
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    }
                }
            }
        }
    }

}