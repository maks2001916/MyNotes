package com.example.mynotes

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class Note(
    var id: Int,
    var text: String,
    var isChecked: Boolean,
    var creationData: Date
)