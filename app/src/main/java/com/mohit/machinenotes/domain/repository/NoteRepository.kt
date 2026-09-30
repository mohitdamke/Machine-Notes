package com.mohit.machinenotes.domain.repository

import com.mohit.machinenotes.data.local.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<NoteEntity>>
    fun getNoteById(id: Int): Flow<NoteEntity?>
    fun searchNotes(query: String): Flow<List<NoteEntity>>
    suspend fun insertNote(note: NoteEntity)
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)


}