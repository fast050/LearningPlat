package com.example.learningplat.presentation.ui.courseslist.filter_dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.learningplat.R
import com.example.learningplat.presentation.utils.data_store.*
import com.example.learningplat.databinding.FilterScreenBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FilterDialog : DialogFragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FilterScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FilterDialogViewModel>()

    //for test
    private val TAG = "FilterDialog"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.rounded_corner)
        _binding = FilterScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPriceTypeSelection()
        setLevelSelection()
        initDurationSwitchSelection()
        initCategoryChipsSelection()


        selectCategoryChips()
        selectPriceType()
        setOrderByCoursesSpinner()
        selectLevel()
        selectDurationSwitch()

    }

    private fun selectPriceType() {

        binding.priceTypeSwitch.priceTypeGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->

            if (isChecked) {
                when (checkedId) {
                    binding.priceTypeSwitch.allType.id -> {

                        viewModel.setSetting(
                            priceType = CoursePriceType(
                                buttonSelectedId = binding.priceTypeSwitch.allType.id,
                                coursePriceTypeSelection = CoursePriceTypeSelection.All
                            )
                        )
                    }
                    binding.priceTypeSwitch.freeType.id -> {


                        viewModel.setSetting(
                            priceType = CoursePriceType(
                                buttonSelectedId = binding.priceTypeSwitch.freeType.id,
                                coursePriceTypeSelection = CoursePriceTypeSelection.FREE
                            )
                        )
                    }
                    binding.priceTypeSwitch.paidType.id -> {


                        viewModel.setSetting(
                            priceType = CoursePriceType(
                                buttonSelectedId = binding.priceTypeSwitch.paidType.id,
                                coursePriceTypeSelection = CoursePriceTypeSelection.PAID
                            )
                        )
                    }
                }
            }
        }

    }

    private fun setPriceTypeSelection() {
        binding.priceTypeSwitch.priceTypeGroup.check(
            viewModel.coursesFilterSetting.coursePriceType?.buttonSelectedId ?: -1
        )
    }


    private fun selectLevel() {

        binding.levelSwitch.levelTypeGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    binding.levelSwitch.allSwitch.id -> {

                        viewModel.setSetting(
                            level = CourseLevel(
                                buttonSelectedId = binding.levelSwitch.allSwitch.id,
                                courseLevelSelection = CourseLevelSelection.ALL
                            )
                        )
                    }

                    binding.levelSwitch.beginnerSwitch.id -> {
                        viewModel.setSetting(
                            level = CourseLevel(
                                buttonSelectedId = binding.levelSwitch.beginnerSwitch.id,
                                courseLevelSelection = CourseLevelSelection.BEGINNER
                            )
                        )
                    }

                    binding.levelSwitch.intermediateSwitch.id -> {
                        viewModel.setSetting(
                            level = CourseLevel(
                                buttonSelectedId = binding.levelSwitch.intermediateSwitch.id,
                                courseLevelSelection = CourseLevelSelection.INTERMEDIATE
                            )
                        )
                    }

                    binding.levelSwitch.expertSwitch.id -> {
                        viewModel.setSetting(
                            level = CourseLevel(
                                buttonSelectedId = binding.levelSwitch.expertSwitch.id,
                                courseLevelSelection = CourseLevelSelection.EXPERT
                            )
                        )
                    }

                }
            }
        }


    }

    private fun setLevelSelection() {
        binding.levelSwitch.levelTypeGroup.check(
            viewModel.coursesFilterSetting.courseLevel?.buttonSelectedId ?: -1
        )
    }

    private fun selectDurationSwitch() {

        binding.videoDurationSwitch.videoDurationGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    binding.videoDurationSwitch.allSwitchItem.id -> {

                        viewModel.setSetting(
                            durationType = CourseDurationType(
                                buttonSelectedId = binding.videoDurationSwitch.allSwitchItem.id,
                                courseDurationTypeSelection = CourseDurationTypeSelection.ALL
                            )
                        )
                    }

                    binding.videoDurationSwitch.shortSwitchItem.id -> {
                        viewModel.setSetting(
                            durationType = CourseDurationType(
                                buttonSelectedId = binding.videoDurationSwitch.shortSwitchItem.id,
                                courseDurationTypeSelection = CourseDurationTypeSelection.SHORT
                            )
                        )
                    }

                    binding.videoDurationSwitch.mediumSwitchItem.id -> {
                        viewModel.setSetting(
                            durationType = CourseDurationType(
                                buttonSelectedId = binding.videoDurationSwitch.mediumSwitchItem.id,
                                courseDurationTypeSelection = CourseDurationTypeSelection.MEDIUM
                            )
                        )
                    }

                    binding.videoDurationSwitch.longSwitchItem.id -> {
                        viewModel.setSetting(
                            durationType = CourseDurationType(
                                buttonSelectedId = binding.videoDurationSwitch.longSwitchItem.id,
                                courseDurationTypeSelection = CourseDurationTypeSelection.LONG
                            )
                        )
                    }

                    binding.videoDurationSwitch.extraLongSwitchItem.id -> {
                        viewModel.setSetting(
                            durationType = CourseDurationType(
                                buttonSelectedId = binding.videoDurationSwitch.extraLongSwitchItem.id,
                                courseDurationTypeSelection = CourseDurationTypeSelection.EXTRA_LONG
                            )
                        )
                    }

                }
            }
        }


    }

    private fun initDurationSwitchSelection() {
        binding.videoDurationSwitch.videoDurationGroup.check(
            viewModel.coursesFilterSetting.courseDurationType?.buttonSelectedId ?: -1
        )
    }

    private fun selectCategoryChips() {
        binding.chipGroup.chipGroup.setOnCheckedChangeListener { chipGroup, i ->
            val chip = chipGroup.findViewById<Chip>(i)
            viewModel.setCategory(chip.text, chip.id)
        }
    }

    private fun initCategoryChipsSelection() {
        binding.chipGroup.chipGroup.check(
            viewModel.coursesFilterSetting.coursesCategory?.buttonSelectedId ?: -1
        )
    }

    private fun setOrderByCoursesSpinner() {

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.order_by_array,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                binding.orderSpinner.adapter = adapter
            }

        binding.orderSpinner.setSelection(viewModel.coursesFilterSetting.courseOrderBy?.selectionPosition ?: 0)

        binding.orderSpinner.onItemSelectedListener = this
    }

    /**
     *  Handle the click for the spinner if item got selected
     */
    @SuppressLint("ResourceType")
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        val item = parent.getItemAtPosition(pos) as String

        viewModel.setOrderByPick(item,pos)
    }

    /**
     *  Handle the click for the spinner if nothing selected
     */
    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.85
                ).toInt()
        dialog!!.window?.setLayout(width, height)//, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


}