package com.example.a60days.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a60days.data.Habit
import com.example.a60days.data.HabitRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val repository: HabitRepository) : ViewModel() {

    val habits: Flow<List<Habit>> = repository.getAllHabits()
}

class HomeViewModelFactory(
    private val repository: HabitRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
