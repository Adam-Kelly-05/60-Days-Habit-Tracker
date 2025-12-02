package com.example.a60days

import android.app.Application
import com.example.a60days.data.HabitDatabase
import com.example.a60days.data.HabitRepository

class SixtyDaysApplication : Application() {

    val database by lazy { HabitDatabase.getDatabase(this) }

    val habitRepository by lazy { HabitRepository(database.habitDao()) }
}
