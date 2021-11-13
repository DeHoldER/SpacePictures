package ru.geekbrains.spacepictures.view.test.notes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.spacepictures.databinding.FragmentNotesBinding
import ru.geekbrains.spacepictures.util.ViewBindingFragment

class NotesFragment : ViewBindingFragment<FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    lateinit var itemTouchHelper: ItemTouchHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteData = arrayListOf(
            NoteData("Earth"),
            NoteData("Earth"),
            NoteData("Mars", ""),
            NoteData("Earth"),
            NoteData("Earth"),
            NoteData("Earth"),
            NoteData("Mars", null)
        )

        val adapter = NotesAdapter(
            object : NotesAdapter.OnListItemClickListener {
                override fun onItemClick(NoteData: NoteData) {
                    Toast.makeText(
                        requireContext(), NoteData.text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            noteData,
            object : NotesAdapter.OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }
            }
        )

        binding.recyclerView.adapter = adapter
        binding.recyclerFragmentFAB.setOnClickListener { adapter.appendItem() }

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }


}