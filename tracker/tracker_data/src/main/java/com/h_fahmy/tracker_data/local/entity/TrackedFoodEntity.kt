package com.h_fahmy.tracker_data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackedFoodEntity(
    val name: String,
    val imageUrl: String?,
    val type: Type,
    val amount: Int,
    val calories: Int,
    val carbs: Int,
    val fat: Int,
    val protein: Int,
    @Embedded val date: Date,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    data class Date(
        val day: Int,
        val month: Int,
        val year: Int
    )

    enum class Type {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }
}