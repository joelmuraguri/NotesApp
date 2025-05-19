package com.joel.noteapp.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.joel.noteapp.data.entity.Note
import kotlinx.coroutines.flow.Flow

interface NotesDao {

    /*
    * 1. User to fetch all notes
    * 2. User to fetch note by id
    * 3. User to delete a note
    * 4. User to update a note
    * 5. User to delete all notes
    * 6. User to insert a note
    * */
    /*
    * Coroutines - async programming - multithreading */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM notes_table")
    fun fetchAllNotes() : Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE id=:noteId")
    suspend fun fetchNoteBydId(noteId : Int) : Note

    @Delete
    suspend fun deleteNote(noteId: Int)

    @Update
    suspend fun updateNote(noteId: Int)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()
}