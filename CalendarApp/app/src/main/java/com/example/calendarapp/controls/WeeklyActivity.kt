package com.example.calendarapp.controls

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.R
import com.example.calendarapp.elements.*
import com.example.calendarapp.elements.CalendarUtils.Companion.daysInWeekArray
import com.example.calendarapp.elements.CalendarUtils.Companion.monthYearFromDate
import java.time.LocalDate

class WeeklyActivity : AppCompatActivity(), CalendarAdapter.OnItemListener{

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var eventListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("weeklyActivity", "starting...")
        setContentView(R.layout.weekly_activity)
        Log.i("weeklyActivity", "content set...")
        initWidgets()
        Log.i("weeklyActivity", "widgets inition...")
        setWeekView()
        Log.i("weeklyActivity", "week view setting up...")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("events", Event.eventsList)
        outState.putSerializable("date", CalendarUtils.selectedDate)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Event.eventsList = savedInstanceState.getParcelableArrayList<Event>("events") as ArrayList<Event>
        CalendarUtils.selectedDate = savedInstanceState.getSerializable("date") as LocalDate
        setWeekView()
    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.monYearLabel)
        eventListView = findViewById(R.id.eventListView)
    }

    private fun setWeekView() {
        monthYearText.text = monthYearFromDate(CalendarUtils.selectedDate)
        val days = daysInWeekArray(CalendarUtils.selectedDate)

        val calendarAdapter = CalendarAdapter(days, this)
        val layoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter
        setEventAdapter()
    }

    fun prevWeekAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1)
        setWeekView()
    }

    fun nextWeekAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1)
        setWeekView()
    }

    override fun onItemClick(position: Int, date: LocalDate) {
        CalendarUtils.selectedDate = date
        setWeekView()
    }

    override fun onResume() {
        super.onResume()
        setEventAdapter()
    }

    private fun setEventAdapter() {
        val dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate)
        val eventAdapter = EventAdapter(applicationContext, dailyEvents)
        eventListView.adapter = eventAdapter
        eventListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, EventRemoveActivity::class.java)
            intent.putExtra("POSITION", position)
            startActivity(intent)
        }
    }

    fun newEventAction(view: View) {
        startActivity(Intent(this, EventEditActivity::class.java))
    }

    fun dailyAction(view: View) {
        startActivity(Intent(this, DailyActivity::class.java))
    }
}