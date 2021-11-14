package ru.geekbrains.spacepictures.view.test.notes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.databinding.FragmentNotesBinding
import ru.geekbrains.spacepictures.util.ViewBindingFragment
import ru.geekbrains.spacepictures.view.MainActivity
import ru.geekbrains.spacepictures.viewmodel.PictureOfTheDayViewModel
import java.text.FieldPosition

class NotesFragment : ViewBindingFragment<FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var adapter: NotesAdapter

    private lateinit var notesLiveData : MutableLiveData<MutableList<NoteData>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesLiveData = (requireActivity() as MainActivity).getViewModel().notesListLiveData
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NotesAdapter(
            onListItemClickListener =
            object : NotesAdapter.OnListItemClickListener {
                override fun onItemClick(data: NoteData, position: Int) {
//                    Toast.makeText(
//                        requireContext(), data.text,
//                        Toast.LENGTH_SHORT
//                    ).show()
                    EditDialogFragment.newInstance(position)
                        .show(requireActivity().supportFragmentManager, "edit")
                }
            },
            data = notesLiveData.value ?: mutableListOf(),
            dragListener =
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

        notesLiveData.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })
    }


}