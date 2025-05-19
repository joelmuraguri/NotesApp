package com.joel.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joel.noteapp.data.dao.NotesDao
import com.joel.noteapp.data.entity.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NotesDao
//    abstract val notesDao : NotesDao

}