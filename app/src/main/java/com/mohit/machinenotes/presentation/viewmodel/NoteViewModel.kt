package com.mohit.machinenotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohit.machinenotes.data.local.NoteEntity
import com.mohit.machinenotes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _notes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notes = _notes.asStateFlow()


    private val _selectedNotes = MutableStateFlow<NoteEntity?>(null)
    val selectedNotes = _selectedNotes.asStateFlow()


    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery


    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()


    private val _isError = MutableStateFlow<String?>(null)
    val isError = _isError.asStateFlow()


    init {
        getAllNotes()
    }

    private fun getAllNotes() {

        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = null

            try {
                repository.getNotes().collect { allNotes ->
                    _notes.value = allNotes
                }

            } catch (e: Exception) {
                _isError.value = e.message.toString() ?: "Something Went Wrong"
            } finally {
                _isLoading.value = false
            }
        }

    }

     fun getNoteById(id: Int) {

        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = null

            try {
                repository.getNoteById(id).collect { allNotes ->
                    _selectedNotes.value = allNotes
                }

            } catch (e: Exception) {
                _isError.value = e.message.toString() ?: "Something Went Wrong"
            } finally {
                _isLoading.value = false
            }
        }

    }

     fun searchNote(query: String) {
        _searchQuery.value = query
        _isLoading.value = true
        _isError.value = null

        viewModelScope.launch {

            try {
                if (query.isBlank()) {
                    repository.getNotes().collect {
                        _notes.value = it
                    }
                } else {
                    repository.searchNotes(query).collect { allNotes ->
                        _notes.value = allNotes
                    }
                }

            } catch (e: Exception) {
                _isError.value = e.message.toString() ?: "Something Went Wrong"
            } finally {
                _isLoading.value = false
            }
        }

    }

     fun insertNote(title: String, content: String) {

        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = null

            try {
                repository.insertNote(
                    NoteEntity(
                        title = title, content = content
                    )
                )
            } catch (e: Exception) {
                _isError.value = e.message.toString() ?: "Something Went Wrong"
            } finally {
                _isLoading.value = false
            }
        }

    }

     fun updateNote(note : NoteEntity) {

        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = null

            try {
                repository.updateNote(note)
            } catch (e: Exception){
                _isError.value = e.message.toString() ?: "Something Went Wrong"
            }
            finally {
                _isLoading.value = false
            }
        }

    }

     fun deleteNote(note : NoteEntity) {

        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = null

            try {
                repository.deleteNote(note)
            } catch (e: Exception){
                _isError.value = e.message.toString() ?: "Something Went Wrong"
            }
            finally {
                _isLoading.value = false
            }
        }

    }


}