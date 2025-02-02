package com.example.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val notes: MutableList<Note>):
    RecyclerView.Adapter<CustomAdapter.UserViewHolder>() {

    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(note: Note, position: Int)
    }
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberTV = itemView.findViewById(R.id.noteNumberTV) as TextView
        val textTV = itemView.findViewById(R.id.noteTextTV) as TextView
        var completedCB = itemView.findViewById(R.id.noteCompletedCB) as CheckBox
        val dataTV = itemView.findViewById(R.id.dateAndTimeTV) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val note = notes[position]
        holder.numberTV.text = note.data
        holder.textTV.text = note.text
        holder.completedCB.setChecked(note.complete)
        holder.dataTV.text = note.data
        holder.itemView.setOnClickListener {
            onClickListener?.onClick(note, position)

        }
    }

    fun setOnClickListener(onClothingClickListener: OnClickListener) {
        this.onClickListener = onClothingClickListener
    }

}