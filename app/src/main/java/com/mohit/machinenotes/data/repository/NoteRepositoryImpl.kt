package com.mohit.machinenotes.data.repository

import com.mohit.machinenotes.data.local.NoteDao
import com.mohit.machinenotes.data.local.NoteEntity
import com.mohit.machinenotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<NoteEntity>> {
        return dao.getNotes()
    }

    override fun getNoteById(id: Int): Flow<NoteEntity?> {
        return dao.getNoteById(id)
    }

    override fun searchNotes(query: String): Flow<List<NoteEntity>> {
        return dao.searchNotes(query)
    }

    override suspend fun insertNote(note: NoteEntity) {
        return dao.insertNote(note)

    }

    override suspend fun updateNote(note: NoteEntity) {
        return dao.updateNote(note)

    }

    override suspend fun deleteNote(note: NoteEntity) {
        return dao.deleteNote(note)

    }
}