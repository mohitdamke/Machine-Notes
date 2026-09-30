package com.mohit.machinenotes.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohit.machinenotes.data.local.NoteDao
import com.mohit.machinenotes.data.local.NoteDatabase
import com.mohit.machinenotes.data.repository.NoteRepositoryImpl
import com.mohit.machinenotes.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context : Context
    ): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notes"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(
        noteDatabase: NoteDatabase
    ): NoteDao{
        return noteDatabase.getNoteDao()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        dao: NoteDao
    ) : NoteRepository {
        return NoteRepositoryImpl(dao)
    }




}