package com.example.learningplat.presentation.utils.data_store

import kotlinx.serialization.Serializable


@Serializable
data class CourseDurationType(
  val buttonSelectedId:Int=-1,
  val courseDurationTypeSelection : CourseDurationTypeSelection
)

enum class CourseDurationTypeSelection(val durationValue:String?){
    ALL(null),
    SHORT("short"),
    MEDIUM("medium"),
    LONG("long"),
    EXTRA_LONG("extraLong")
}