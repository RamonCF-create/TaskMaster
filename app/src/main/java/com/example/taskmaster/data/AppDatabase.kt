package com.example.taskmaster.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmaster.model.Tarefa

@Database(entities = [Tarefa::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tarefaDao(): TarefaDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "taskmaster_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
