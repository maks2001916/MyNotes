package com.example.mynotes

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Note(
    var text: String,
    var complete: Boolean,
    var data: String = ""
    var position: Int
) {
    companion object {
        var number: Int = 0

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addNote() {
        number++
        data = getCurrentDateTime()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        return current.format(formatter)
    }

    fun getNumber(): Int {
        return number
    }

}