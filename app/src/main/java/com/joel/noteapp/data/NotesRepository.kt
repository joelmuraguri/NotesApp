package com.joel.noteapp.data

import android.content.Context
import androidx.room.Room
import com.joel.noteapp.data.entity.Note
import kotlinx.coroutines.flow.Flow

class NotesRepository(
    context: Context
) {

    val db : NoteDatabase = Room.databaseBuilder(
        context = context,
        klass = NoteDatabase::class.java,
        "notes_db"
    ).build()

    private val noteDao = db.noteDao()

    fun getAllNotes() : Flow<List<Note>> = noteDao.fetchAllNotes()

    suspend fun getNoteById(noteId : Int) : Note = noteDao.fetchNoteBydId(noteId)

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    suspend fun deleteNote(noteId: Int) = noteDao.deleteNote(noteId)

    suspend fun updateNote(noteId: Int) = noteDao.updateNote(noteId)

    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()
}