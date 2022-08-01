package com.example.learningplat.presentation.ui.coursedetail.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.learningplat.databinding.FragmentCourseInfoBinding
import com.example.learningplat.presentation.ui.SharedViewModel
import com.example.learningplat.presentation.utils.formatURL

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


        binding.apply {


            sharedViewModel.courseDetail.observe(viewLifecycleOwner){courseItem->
                viewModel.setCourseItem(courseItem)

                courseDetailTextInstructorName.text = courseItem.instructorName
                courseDetailInstructorTextTitle.text = courseItem.instructorTitle
                courseDetailTextPrice.text = courseItem.coursePrice
                courseDetailTextSummary.text = courseItem.courseTitle
                Glide.with(this@CourseInfoFragment).load(courseItem.courseImage)
                    .into(courseDetailImageCoursePicture)
                Glide.with(this@CourseInfoFragment).load(courseItem.instructorPicture)
                    .into(courseDetailImageInstructorPicture)


                courseDetailButtonApply.setOnClickListener {
                    val uri =
                        Uri.parse(courseItem?.courseUrl?.formatURL()) // missing 'http://' will cause crashed
                    uri?.let {
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    }
            }

//            viewModel.courseItem.observe(viewLifecycleOwner){courseItem->
//
//
//                courseDetailTextInstructorName.text = courseItem.instructorName
//                courseDetailInstructorTextTitle.text = courseItem.instructorTitle
//                courseDetailTextPrice.text = courseItem.coursePrice
//                courseDetailTextSummary.text = courseItem.courseTitle
//                Glide.with(this@CourseInfoFragment).load(courseItem.courseImage)
//                    .into(courseDetailImageCoursePicture)
//                Glide.with(this@CourseInfoFragment).load(courseItem.instructorPicture)
//                    .into(courseDetailImageInstructorPicture)
//
//
//                courseDetailButtonApply.setOnClickListener {
//                    val uri =
//                        Uri.parse(courseItem?.courseUrl?.formatURL()) // missing 'http://' will cause crashed
//                    uri?.let {
//                        val intent = Intent(Intent.ACTION_VIEW, uri)
//                        startActivity(intent)
//                    }
//                }
//            }
        }
        }
    }

}