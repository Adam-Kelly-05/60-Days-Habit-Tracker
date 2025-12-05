package com.example.a60days.ui.edithabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a60days.data.Habit
import com.example.a60days.data.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditHabitViewModel(
    private val repository: HabitRepository,
    private val habitId: Int
) : ViewModel() {

    private val _habit = MutableStateFlow<Habit?>(null)
    val habit: StateFlow<Habit?> = _habit

    init {
        viewModelScope.launch {
            repository.getHabit(habitId).collect { loadedHabit ->
                _habit.value = loadedHabit
            }
        }
    }

    fun updateHabit(
        name: String,
        description: String,
        totalDays: Int,
        completedDays: Int,
        photoUri: String?
    ) {
        val current = _habit.value ?: return

        val updated = current.copy(
            name = name,
            description = description,
            totalDays = totalDays,
            completedDays = completedDays,
            photoUri = photoUri
        ) // Get the old habit, add to new changes values, then update with the same ID

        viewModelScope.launch {
            repository.updateHabit(updated)
        }
    }

    fun deleteHabit(onDeleted: () -> Unit) {
        viewModelScope.launch {
            habit.value?.let {
                repository.deleteHabit(it)
                onDeleted()
            }
        }
    }

}
