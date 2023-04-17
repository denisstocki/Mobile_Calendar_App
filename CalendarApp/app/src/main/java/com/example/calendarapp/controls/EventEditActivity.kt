package com.example.calendarapp.controls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.calendarapp.R
import com.example.calendarapp.elements.CalendarUtils
import com.example.calendarapp.elements.Event
import java.time.LocalTime

class EventEditActivity : AppCompatActivity() {

    private lateinit var eventNameET: EditText
    private lateinit var eventDateTV: TextView
    private lateinit var eventTimeTV: TextView

    private lateinit var time: LocalTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_edit)
        initWidgets()
        time = LocalTime.now()
        eventDateTV.text = "Date: ${CalendarUtils.formattedDate(CalendarUtils.selectedDate)}"
        eventTimeTV.text = "Time: ${CalendarUtils.formattedTime(time)}"
    }

    private fun initWidgets() {
        eventNameET = findViewById(R.id.eventNameEdit)
        eventDateTV = findViewById(R.id.eventDateLabel)
        eventTimeTV = findViewById(R.id.eventTimeLabel)
    }

    fun saveEventAction(view: View) {
        val eventName = eventNameET.text.toString()
        val newEvent = Event(eventName, CalendarUtils.selectedDate, time)
        Event.eventsList.add(newEvent)
        finish()
    }
}