package com.example.learningplat.presentation.utils.data_store

import android.content.Context
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

val Context.dataStoreProto by dataStore(
    fileName = FilterPreferences.DATA_STORE_FILE_NAME,
    serializer = CoursesFilterSettingSerializer
)

class FilterPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val dataStoreProto = context.dataStoreProto

    suspend fun setFilterSetting(
        category: CourseCategory? = null,
        priceType: CoursePriceType? = null,
        orderBy: CourseOrderBy? = null,
        level: CourseLevel? = null,
        durationType: CourseDurationType? = null
    ) {
        dataStoreProto.updateData {
            it.copy(
                coursesCategory = category,
                coursePriceType = priceType,
                courseLevel = level,
                courseOrderBy = orderBy,
                courseDurationType = durationType
            )
        }
    }

    val filterSetting = dataStoreProto.data.catch { exception ->
        if (exception is IOException)
            emit(CoursesFilterSetting())

        throw exception
    }

    companion object {
        const val DATA_STORE_FILE_NAME = "FilterSettingPreferences"
    }
}