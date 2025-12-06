package com.example.a60days

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.a60days.data.HabitDatabase
import com.example.a60days.data.HabitRepository
import com.example.a60days.ui.reusableComponents.DailyHabitWorker
import java.util.concurrent.TimeUnit

class SixtyDaysApplication : Application() {

    val database by lazy { HabitDatabase.getDatabase(this) }
    val habitRepository by lazy { HabitRepository(database.habitDao()) }

    override fun onCreate() {
        super.onCreate()

        // Once per day
        val request = PeriodicWorkRequestBuilder<DailyHabitWorker>(
            1, TimeUnit.DAYS
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "daily_habit_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }
}
