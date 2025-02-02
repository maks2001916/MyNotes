package com.example.mynotes

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Objects

class FirstFragment : Fragment() {

    private lateinit var notes: MutableList<Note>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var toolbarTB = view.findViewById(R.id.toolbarTB) as Toolbar
        var enterTextET = view.findViewById(R.id.enterTextOfNoteET) as EditText
        var saveBTN = view.findViewById(R.id.saveBTN) as Button
        var listRV = view.findViewById(R.id.listRV) as RecyclerView

        listRV.layoutManager = LinearLayoutManager(this)
        val customListAdapter = CustomAdapter(notes)
        listRV.adapter = customListAdapter
        listRV.setHasFixedSize(true)

        saveBTN.setOnClickListener {
            val text = enterTextET.text.toString()
            val note = Note(text, false)
            note.addNote()
            notes.add(note)
            listRV.adapter.notifyItemChanged(note)
        }
    }

}
