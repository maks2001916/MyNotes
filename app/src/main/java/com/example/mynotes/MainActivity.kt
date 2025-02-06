package com.example.mynotes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), OnFragmentDataListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction().replace(R.id.containerID, firstFragment).commit()

    }

    override fun onData(data: String?) {
        val bundle = Bundle()
        bundle.putString("note", data)

        val transaction = this.supportFragmentManager.beginTransaction()
        val noteEditFragment = NoteEditFragment()
        noteEditFragment.arguments = bundle

        transaction.replace(R.id.containerID, noteEditFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)


    }
}