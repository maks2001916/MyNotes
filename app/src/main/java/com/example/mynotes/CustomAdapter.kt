package com.example.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale


class CustomAdapter(private val notes: MutableList<Note>):
    RecyclerView.Adapter<CustomAdapter.UserViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(note: Note, position: Int)
    }

    interface OnCheckChangedListener {
        fun onCheckChanged(note: Note, isChecked: Boolean)
    }

    private var checkChangedListener: OnCheckChangedListener? = null
    private var itemClickListener: OnItemClickListener? = null

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberTV: TextView = itemView.findViewById(R.id.noteNumberTV)
        val textTV: TextView = itemView.findViewById(R.id.noteTextTV)
        val completedCB: CheckBox = itemView.findViewById(R.id.noteCompletedCB)
        val dataTV: TextView = itemView.findViewById(R.id.dateAndTimeTV)
    }

    fun setOnCheckChangedListener(listener: OnCheckChangedListener) {
        this.checkChangedListener = listener
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val note = notes[position]
        holder.numberTV.text = "${note.id}."
        holder.textTV.text = note.text
        holder.completedCB.isChecked = note.isChecked
        holder.dataTV.text = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            .format(note.creationData)

        holder.completedCB.setOnCheckedChangeListener { _, isChecked ->
            note.isChecked = isChecked
            checkChangedListener?.onCheckChanged(note, isChecked)
        }

        holder.itemView.setOnClickListener {
            val actualPosition = holder.adapterPosition
            if (actualPosition != RecyclerView.NO_POSITION) {
                itemClickListener?.onItemClick(notes[actualPosition], actualPosition)
            }
        }
    }

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

}