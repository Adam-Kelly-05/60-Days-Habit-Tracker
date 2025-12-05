package com.example.a60days.data

import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao) {

    fun getAllHabits(): Flow<List<Habit>> = habitDao.getAllHabits() // For home screen

    fun getHabit(id: Int): Flow<Habit> = habitDao.getHabit(id) // For edit habit screen

    suspend fun addHabit(habit: Habit) {
        habitDao.insert(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habitDao.update(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        habitDao.delete(habit)
    }
}
