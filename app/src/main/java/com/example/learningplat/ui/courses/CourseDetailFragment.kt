package com.example.learningplat.ui.courses

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.learningplat.databinding.FragmentCourseDetailBinding
import com.example.learningplat.utils.formatURL


class CourseDetailFragment : Fragment() {

    private var _binding: FragmentCourseDetailBinding?=null
    private val binding get() = _binding!!

    private val args: CourseDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCourseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            val courseItem = args.courseItem
            val author = args.courseItem?.visibleInstructors?.get(0)


            courseDetailTextInstructorName.text = author?.name
                ?: "Unknown"
            courseDetailInstructorTextTitle.text =
                author?.jobTitle ?: "Unknown"
            courseDetailTextPrice.text = courseItem?.price
            courseDetailTextSummary.text = courseItem?.title
            Glide.with(this@CourseDetailFragment).load(courseItem?.image480x270)
                .into(courseDetailImageCoursePicture)
            Glide.with(this@CourseDetailFragment).load(author?.image100x100)
                .into(courseDetailImageInstructorPicture)


            courseDetailButtonApply.setOnClickListener {
                val uri = Uri.parse(courseItem?.url?.formatURL()) // missing 'http://' will cause crashed
                uri?.let {
                    val intent =Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}