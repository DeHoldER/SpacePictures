package ru.geekbrains.spacepictures.view.test.notes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import ru.geekbrains.spacepictures.databinding.FragmentNotesBinding
import ru.geekbrains.spacepictures.util.ViewBindingFragment

class NotesFragment : ViewBindingFragment<FragmentNotesBinding>(FragmentNotesBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteData = arrayListOf(
            NoteData("Earth"),
            NoteData("Earth"),
            NoteData("Mars", "Марс марс марс"),
            NoteData("Earth"),
            NoteData("Earth"),
            NoteData("Earth"),
            NoteData("Mars", null)
        )
        binding.recyclerView.adapter = NotesAdapter(
            object : NotesAdapter.OnListItemClickListener {
                override fun onItemClick(NoteData: NoteData) {
                    Toast.makeText(
                        requireContext(), NoteData.text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            noteData
        )
    }


}