package com.example.a60days.ui.edithabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a60days.data.HabitRepository

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
