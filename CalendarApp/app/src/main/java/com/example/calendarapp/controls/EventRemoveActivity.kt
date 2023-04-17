package com.example.calendarapp.controls

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarapp.R
import com.example.calendarapp.elements.CalendarUtils
import com.example.calendarapp.elements.Event
import java.time.LocalTime

class EventRemoveActivity : AppCompatActivity() {

    private lateinit var eventRemoveName: TextView
    private lateinit var eventRemoveDate: TextView
    private lateinit var eventRemoveTime: TextView
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remove_activity)
        initWidgets()
        Log.i("liston", Event.eventsList.toString())
        position = intent.getIntExtra("POSITION", 0)
        eventRemoveName.text = "Nazwa: ${Event.eventsList[position].name}"
        eventRemoveDate.text = "Data: ${Event.eventsList[position].date}"
        eventRemoveTime.text = "Time: ${Event.eventsList[position].time}"
    }

    private fun initWidgets() {
        eventRemoveName = findViewById(R.id.eventRemoveName)
        eventRemoveDate = findViewById(R.id.eventRemoveDate)
        eventRemoveTime = findViewById(R.id.eventRemoveTime)
    }

    fun removeEventAction(view: View) {
        val dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate)
        val event = dailyEvents[position]
        Event.eventsList.remove(event)
        finish()
    }
}