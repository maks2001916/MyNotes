package com.example.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton.OnCheckedChangeListener
import java.text.SimpleDateFormat
import java.util.Locale


class CustomAdapter(private val notes: MutableList<Note>):
    RecyclerView.Adapter<CustomAdapter.UserViewHolder>() {

    interface OnCheckChangedListener {
        fun onCheckChanged(note: Note, isChecked: Boolean)
    }

    private var checkChangedListener: OnCheckChangedListener? = null

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberTV: TextView = itemView.findViewById(R.id.noteNumberTV)
        val textTV: TextView = itemView.findViewById(R.id.noteTextTV)
        val completedCB: CheckBox = itemView.findViewById(R.id.noteCompletedCB)
        val dataTV: TextView = itemView.findViewById(R.id.dateAndTimeTV)
    }

    fun setOnCheckChangedListener(listener: OnCheckChangedListener) {
        this.checkChangedListener = listener
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
    }

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

}