package ru.geekbrains.spacepictures.view.test.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotesViewModel(
    val notesListLiveData: MutableLiveData<MutableList<NoteData>> = MutableLiveData(),
) : ViewModel() {

    init {
        val data = mutableListOf(
            NoteData("Earth"),
            NoteData("Earth"),
            NoteData("Mars", ""),
            NoteData("Earth"),
            NoteData("Earth"),
            NoteData("Earth"),
            NoteData("Mars", null)
        )

        notesListLiveData.value = data
    }


}