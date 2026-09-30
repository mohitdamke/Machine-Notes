package com.mohit.machinenotes.navigation

object Routes {

    const val NoteList = "note_list"
    const val AddNote = "add_note"
    const val EditNote = "edit_note/{id}"
    fun editNote(id : Int) = "edit_note/$id"

}