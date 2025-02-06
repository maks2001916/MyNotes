package com.example.mynotes

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class FirstFragment : Fragment() {

    //private lateinit var binding: ActivityMainBinding
    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private var notes =  mutableListOf<Note>()
    private lateinit var adapter: CustomAdapter
    private var noteIdCounter = 1

    private lateinit var toolbarTB: Toolbar
    private lateinit var enterTextET: EditText
    private lateinit var saveBTN: Button
    private lateinit var listRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        toolbarTB = view.findViewById(R.id.toolbarTB)
        enterTextET = view.findViewById(R.id.enterTextOfNoteET)
        saveBTN = view.findViewById(R.id.saveBTN)
        listRV = view.findViewById(R.id.listRV)

        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbarTB)


        listRV.layoutManager = LinearLayoutManager(requireContext())
        adapter = CustomAdapter(notes)
        listRV.adapter = adapter
        listRV.setHasFixedSize(true)

        saveBTN.setOnClickListener {
            val text = enterTextET.text.toString().trim()
            if (text.isNotEmpty()) {
                val note = Note(
                    id = noteIdCounter++,
                    text = text,
                    isChecked = false,
                    creationData = Date()
                )
                notes.add(note)
                adapter.notifyItemChanged( notes.lastIndex)
                enterTextET.text.clear()
            }
        }

        adapter.setOnCheckChangedListener(object : CustomAdapter.OnCheckChangedListener {
            override fun onCheckChanged(note: Note, isChecked: Boolean) {
                // Обновляем данные в списке
                notes.find { it.id == note.id }?.isChecked = isChecked
                // Если нужно сохранить изменения в БД или другом хранилище
            }
        })

        adapter.setOnItemClickListener(object : CustomAdapter.OnItemClickListener {
            override fun onItemClick(note: Note, position: Int) {
                onFragmentDataListener.onData(note.text)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu) // Инфлейтим меню
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit -> activity?.finishAffinity() // Обработчик нажатия на пункт меню
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

}
