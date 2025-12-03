package com.example.a60days.ui.edithabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.a60days.data.Habit
import com.example.a60days.data.HabitRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class EditHabitViewModel(
    private val repo: HabitRepository,
    private val habitId: Int
) : ViewModel() {

    private val habitFlow = repo.getHabit(habitId).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        null
    )

    val habit: StateFlow<Habit?> = habitFlow

    fun updateHabit(
        name: String,
        description: String,
        totalDays: Int,
        completedDays: Int
    ) {
        viewModelScope.launch {
            val current = habitFlow.value ?: return@launch
            val updated = current.copy(
                name = name,
                description = description,
                totalDays = totalDays,
                completedDays = completedDays
            )
            repo.updateHabit(updated)
        }
    }

}

class EditHabitViewModelFactory(
    private val repo: HabitRepository,
    private val habitId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditHabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditHabitViewModel(repo, habitId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
