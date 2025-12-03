package com.example.a60days.ui.addhabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.a60days.data.Habit
import com.example.a60days.data.HabitRepository
import kotlinx.coroutines.launch

class AddHabitViewModel(private val repo: HabitRepository) : ViewModel() {

    var name = ""
    var description = ""
    var totalDays = "60"

    fun saveHabit(onSaved: () -> Unit) {
        viewModelScope.launch {
            repo.addHabit(
                Habit(
                    name = name,
                    description = description,
                    totalDays = totalDays.toIntOrNull() ?: 60,
                    completedDays = 0
                )
            )
            onSaved()
        }
    }
}

class AddHabitViewModelFactory(
    private val repo: HabitRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddHabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddHabitViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
