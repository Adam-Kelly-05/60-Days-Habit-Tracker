package com.example.a60days.ui.addhabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a60days.data.HabitRepository

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
