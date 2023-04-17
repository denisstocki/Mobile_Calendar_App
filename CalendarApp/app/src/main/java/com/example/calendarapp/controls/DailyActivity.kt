package com.example.calendarapp.controls

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarapp.R
import com.example.calendarapp.elements.*
import com.example.calendarapp.elements.CalendarUtils.Companion.selectedDate
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*
import kotlin.collections.ArrayList

class DailyActivity : AppCompatActivity() {

    private lateinit var monthDayText: TextView
    private lateinit var dayOfWeekTV: TextView
    private lateinit var hourListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily_activity)
        initWidgets()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("events", Event.eventsList)
        outState.putSerializable("date", selectedDate)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Event.eventsList = savedInstanceState.getParcelableArrayList<Event>("events") as ArrayList<Event>
        selectedDate = savedInstanceState.getSerializable("date") as LocalDate
        setDayView()
    }

    private fun initWidgets() {
        monthDayText = findViewById(R.id.monthDayLabel)
        dayOfWeekTV = findViewById(R.id.dayOfWeekLabel)
        hourListView = findViewById(R.id.hourListView)
    }

    override fun onResume() {
        super.onResume()
        setDayView()
    }

    private fun setDayView() {
        monthDayText.text = CalendarUtils.monthDayFromDate(selectedDate)
        val dayOfWeek = selectedDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        dayOfWeekTV.text = dayOfWeek
        setHourAdapter()
    }

    private fun setHourAdapter() {
        val hourAdapter = HourAdapter(applicationContext, hourEventList())
        hourListView.adapter = hourAdapter
    }

    private fun hourEventList(): ArrayList<HourEvent> {
        val list = ArrayList<HourEvent>()

        for (hour in 0 until 24) {
            val time = LocalTime.of(hour, 0)
            val events = Event.eventsForDateAndTime(selectedDate, time)
            val hourEvent = HourEvent(time, events)
            list.add(hourEvent)
        }

        return list
    }

    fun prevDayAction(view: View) {
        selectedDate = selectedDate.minusDays(1)
        setDayView()
    }

    fun nextDayAction(view: View) {
        selectedDate = selectedDate.plusDays(1)
        setDayView()
    }

    fun newEventAction(view: View) {
        startActivity(Intent(this, EventEditActivity::class.java))
    }
}