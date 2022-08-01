package com.example.learningplat.presentation.utils.data_store

import kotlinx.serialization.Serializable

@Serializable
data class CoursePriceType(
   val buttonSelectedId:Int=-1,
   val coursePriceTypeSelection: CoursePriceTypeSelection
)

enum class CoursePriceTypeSelection(val priceValue: String) {
    All("all"),
    FREE("price-free"),
    PAID("price-paid")
}