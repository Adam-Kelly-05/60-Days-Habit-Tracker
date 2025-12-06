package com.example.a60days.ui.reusableComponents

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.a60days.SixtyDaysApplication
import com.example.a60days.data.Habit
import kotlinx.coroutines.flow.firstOrNull

class DailyHabitWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    private val habitRepository =
        (appContext.applicationContext as SixtyDaysApplication).habitRepository

    override suspend fun doWork(): Result {
        return try {
            val habits: List<Habit> =
                habitRepository.getAllHabits().firstOrNull() ?: emptyList()

            habits.forEach { habit ->
                val safeTotal = habit.totalDays.coerceAtLeast(1)
                val newCompleted = (habit.completedDays + 1).coerceIn(0, safeTotal)

                val updatedHabit = habit.copy(
                    totalDays = safeTotal,
                    completedDays = newCompleted
                )
                habitRepository.updateHabit(updatedHabit)
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}
