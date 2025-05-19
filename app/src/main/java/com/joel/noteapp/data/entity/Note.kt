package com.joel.noteapp.data.entity

import androidx.room.Entity

@Entity("notes_table")
data class Note(
    val id : Int,
    val title : String,
    val content : String,
)
