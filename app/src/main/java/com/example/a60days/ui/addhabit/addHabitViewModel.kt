package com.example.a60days.ui.addhabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a60days.data.Habit
import com.example.a60days.data.HabitRepository
import kotlinx.coroutines.launch

class AddHabitViewModel(private val repo: HabitRepository) : ViewModel() {

    fun saveHabit(name: String, description: String, photoUri: String?, totalDays: Int, onSaved: () -> Unit) {
        viewModelScope.launch {
            val habit = Habit(
                id = 0,
                name = name,
                description = description,
                photoUri = photoUri,
                totalDays = totalDays,
                completedDays = 0
            )
            repo.addHabit(habit)
            onSaved()
        }
    }
}
