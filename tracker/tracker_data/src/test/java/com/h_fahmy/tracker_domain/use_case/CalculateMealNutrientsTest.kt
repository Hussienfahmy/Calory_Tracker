package com.h_fahmy.tracker_domain.use_case

import com.h_fahmy.core.domain.model.ActivityLevel
import com.h_fahmy.core.domain.model.Gender
import com.h_fahmy.core.domain.model.GoalType
import com.h_fahmy.core.domain.model.UserInfo
import com.h_fahmy.core.domain.preferences.Preferences
import com.h_fahmy.tracker_domain.model.MealType
import com.h_fahmy.tracker_domain.model.TrackedFood
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random
import com.google.common.truth.Truth.*

@OptIn(ExperimentalCoroutinesApi::class)
class CalculateMealNutrientsTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients
    private lateinit var trackedFood: List<TrackedFood>

    @Before
    fun setup() {
        val preferences = mockk<Preferences>(relaxed = true)
        coEvery { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            weight = 80f,
            height = 180,
            age = 20,
            goalType = GoalType.KeepWeight,
            activityLevel = ActivityLevel.Medium,
            carbRatio = 0.4f,
            fatRatio = 0.3f,
            proteinRatio = 0.3f,
        )

        calculateMealNutrients = CalculateMealNutrients(preferences)

        trackedFood = (1..30).map {
            TrackedFood(
                name = "Food $it",
                calories = Random.nextInt(2000),
                carbs = Random.nextInt(100),
                fat = Random.nextInt(100),
                protein = Random.nextInt(100),
                mealType = listOf(
                    MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER, MealType.SNACK
                ).random(),
                amount = 100,
                date = LocalDate.now(),
                imageUrl = null
            )
        }
    }

    @Test
    fun `Empty List, empty result`() = runTest {
        val result = calculateMealNutrients(emptyList())

        assertThat(result.totalCalories).isEqualTo(0)
        assertThat(result.totalCarbs).isEqualTo(0)
        assertThat(result.totalFat).isEqualTo(0)
        assertThat(result.totalProtein).isEqualTo(0)
        assertThat(result.mealNutrients).isEmpty()
    }

    @Test
    fun `Calories for breakfast properly calculated`() = runTest {
        val result = calculateMealNutrients(trackedFood)

        val expectedBreakFastCalories = trackedFood
            .filter { it.mealType == MealType.BREAKFAST }
            .sumOf { it.calories }

        val breakfastCalories = result.mealNutrients.values
            .filter { it.type == MealType.BREAKFAST }
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedBreakFastCalories)
    }

    @Test
    fun `Carbs for dinner properly calculated`() = runTest {
        val result = calculateMealNutrients(trackedFood)

        val expectedDinnerCarbs = trackedFood
            .filter { it.mealType == MealType.DINNER }
            .sumOf { it.carbs }

        val dinnerCarbs = result.mealNutrients.values
            .filter { it.type == MealType.DINNER }
            .sumOf { it.carbs }

        assertThat(dinnerCarbs).isEqualTo(expectedDinnerCarbs)
    }
}