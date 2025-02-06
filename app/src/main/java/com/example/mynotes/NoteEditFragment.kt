package com.example.mynotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar

class NoteEditFragment : Fragment() {

    private lateinit var toolbarTB: Toolbar
    private lateinit var enterTextET: EditText
    private lateinit var editBTN: Button
    private var note: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_edit, container, false)

        note = arguments?.getString("note")
        toolbarTB = view.findViewById(R.id.toolbarEditTB)
        enterTextET = view.findViewById(R.id.editNoteET)
        editBTN = view.findViewById(R.id.editBTN)
        enterTextET.setText(note)
        editBTN.setOnClickListener {
            val value = enterTextET.text
        }

        return view
    }

}