package com.joel.noteapp.ui.screens.notes_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joel.noteapp.data.NotesRepository
import com.joel.noteapp.data.entity.Note
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NoteListViewModel(
    private val repository: NotesRepository
) : ViewModel() {


    private val _uiState = mutableStateOf<NoteListUIState>(NoteListUIState.Loading)
    val uiState: State<NoteListUIState> = _uiState

    init {
        observeNotes()
    }

    fun observeNotes(){
        viewModelScope.launch {
            repository.getAllNotes()
                .onStart {
                    _uiState.value = NoteListUIState.Loading
                }
                .catch { e ->
                    _uiState.value = NoteListUIState.Error(e.message!!)
                }
                .collect{ notes ->
                    _uiState.value = NoteListUIState.Success(notes = notes)
                }
        }
    }
}

sealed class NoteListUIState{
    object Loading : NoteListUIState()
    data class Success(val  notes : List<Note>) : NoteListUIState()
    data class Error (val errorMessage : String) :  NoteListUIState()
}