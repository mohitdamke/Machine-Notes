package com.mohit.machinenotes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): Flow<NoteEntity?>

    @Query(
        """
        SELECT * FROM notes 
        WHERE title LIKE '%' || :query || '%'
        OR content LIKE '%' || :query || '%'
        ORDER BY id DESC
    """
    )
    fun searchNotes(query: String): Flow<List<NoteEntity>>

    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)


}