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
    private lateinit var saveBTN: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            return inflater.inflate(R.layout.fragment_note_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarTB = view.findViewById(R.id.toolbarEditTB)
        enterTextET = view.findViewById(R.id.editNoteET)
        saveBTN = view.findViewById(R.id.saveBTN)

    }

}