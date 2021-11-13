package ru.geekbrains.spacepictures.view.test.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.spacepictures.R

class NotesAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<NoteData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_EARTH) {
            EarthViewHolder(
                inflater.inflate(
                    R.layout.item_note_type_earth, parent,
                    false
                ) as View
            )
        } else {
            MarsViewHolder(
                inflater.inflate(
                    R.layout.item_note_type_mars, parent,
                    false
                ) as View
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_EARTH) {
            holder as EarthViewHolder
            holder.bind(data[position])
        } else {
            holder as MarsViewHolder
            holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].text.isNullOrBlank()) TYPE_MARS else
            TYPE_EARTH
    }


    inner class EarthViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = itemView.findViewById(R.id.item_note_title)
        private val textPreview: TextView = itemView.findViewById(R.id.item_note_text_preview)

        fun bind(data: NoteData) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                title.text = data.title
                textPreview.text = data.text
                itemView.setOnClickListener {
                    onListItemClickListener.onItemClick(data) }
            }
        }
    }

    inner class MarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = itemView.findViewById(R.id.item_note_title)
        private val textPreview: TextView = itemView.findViewById(R.id.item_note_text_preview)

        fun bind(data: NoteData) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                title.text = data.title
                textPreview.text = data.text
                title.setOnClickListener {
                    onListItemClickListener.onItemClick(data) }
                textPreview.setOnClickListener {
                    onListItemClickListener.onItemClick(data) }
            }
        }
    }

    interface OnListItemClickListener {
        fun onItemClick(data: NoteData)
    }

    companion object {
        private const val TYPE_EARTH = 0
        private const val TYPE_MARS = 1
    }


}