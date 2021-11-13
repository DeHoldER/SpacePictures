package ru.geekbrains.spacepictures.view.test.notes

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.spacepictures.R

class NotesAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<NoteData>,
    private val dragListener: OnStartDragListener
) : RecyclerView.Adapter<NotesAdapter.BaseViewHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_EARTH -> EarthViewHolder(
                inflater.inflate(
                    R.layout.layout_item_note_type_earth, parent,
                    false
                ) as View
            )
            TYPE_MARS ->
                MarsViewHolder(
                    inflater.inflate(
                        R.layout.layout_item_note_type_mars,
                        parent, false
                    ) as View
                )
            else -> HeaderViewHolder(
                inflater.inflate(
                    R.layout.layout_item_header, parent,
                    false
                ) as View
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    abstract class BaseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {
        abstract fun bind(data: NoteData)

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.WHITE)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> TYPE_HEADER
            data[position].text.isNullOrBlank() -> TYPE_MARS
            else -> TYPE_EARTH
        }

    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: NoteData) {
            itemView.setOnClickListener {
                onListItemClickListener.onItemClick(data)
            }
        }
    }


    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        private val title: TextView = itemView.findViewById(R.id.item_note_title)
        private val textPreview: TextView = itemView.findViewById(R.id.item_note_text_preview)
        private val dragHandleImageView: ImageView = itemView.findViewById(R.id.dragHandleImageView)

        @SuppressLint("ClickableViewAccessibility")
        override fun bind(data: NoteData) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                title.text = data.title
                textPreview.text = data.text
                itemView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }

            dragHandleImageView.setOnTouchListener { _, event ->
                if (MotionEventCompat.getActionMasked(event) ==
                    MotionEvent.ACTION_DOWN
                ) {
                    dragListener.onStartDrag(this)
                }
                false
            }
        }


    }

    private fun generateItem() = NoteData("Mars", "")
    fun appendItem() {
        data.add(generateItem())
        notifyItemInserted(itemCount - 1)

    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view) {
        private val title: TextView = itemView.findViewById(R.id.item_note_title)
        private val textPreview: TextView = itemView.findViewById(R.id.item_note_text_preview)
        private val dragHandleImageView: ImageView = itemView.findViewById(R.id.dragHandleImageView)

        @SuppressLint("ClickableViewAccessibility")
        override fun bind(data: NoteData) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                title.text = data.title
                textPreview.text = data.text
                title.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
                textPreview.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }

            dragHandleImageView.setOnTouchListener { _, event ->
                if (MotionEventCompat.getActionMasked(event) ==
                    MotionEvent.ACTION_DOWN
                ) {
                    dragListener.onStartDrag(this)
                }
                false
            }
        }

    }

    interface OnListItemClickListener {
        fun onItemClick(data: NoteData)
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_EARTH = 1
        private const val TYPE_MARS = 2
    }

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(
                if (toPosition > fromPosition) toPosition - 1 else
                    toPosition, this
            )
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }


}