package com.example.mynotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var toolbarTB = view.findViewById(R.id.toolbarTB) as Toolbar
        var enterTextET = view.findViewById(R.id.enterTextOfNoteET) as EditText
        var saveBTN = view.findViewById(R.id.saveBTN) as Button
        var listRV = view.findViewById(R.id.listRV) as RecyclerView

    }

}
