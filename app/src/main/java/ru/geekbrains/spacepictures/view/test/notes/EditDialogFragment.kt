package ru.geekbrains.spacepictures.view.test.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.spacepictures.R
import ru.geekbrains.spacepictures.view.MainActivity

class EditDialogFragment : DialogFragment() {

    private lateinit var title: String
    private lateinit var text: String

    private var position: Int = 0
    lateinit var data: MutableList<NoteData>

    private lateinit var notesLiveData : MutableLiveData<MutableList<NoteData>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesLiveData = (requireActivity() as MainActivity).getViewModel().notesListLiveData

        position = arguments?.get("position") as Int
        data = notesLiveData.value ?: mutableListOf()

        title = data[position].title
        text = data[position].text.toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleEditText = getView()?.findViewById<EditText>(R.id.titleEditText)
        val textEditText = getView()?.findViewById<EditText>(R.id.textEditText)
        val btnOk = getView()?.findViewById<Button>(R.id.btnOk)

        titleEditText?.setText(title)
        textEditText?.setText(text)

        btnOk?.setOnClickListener {
            data[position].title = titleEditText?.text.toString()
            data[position].text = textEditText?.text.toString()
            notesLiveData.value = data

            dismiss()
        }


    }


    override fun onResume() {
        super.onResume()
        val params = dialog?.window?.attributes
        params?.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as android.view.WindowManager.LayoutParams
    }

    companion object {
        fun newInstance(position: Int): EditDialogFragment {
            val editDialog = EditDialogFragment()
            val args = Bundle()
            args.putInt("position", position)
            editDialog.arguments = args
            return editDialog
        }
    }
}