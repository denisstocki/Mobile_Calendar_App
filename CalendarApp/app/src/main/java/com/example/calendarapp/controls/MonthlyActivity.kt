package com.example.calendarapp.controls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.R
import com.example.calendarapp.elements.CalendarAdapter
import com.example.calendarapp.elements.CalendarUtils
import com.example.calendarapp.elements.CalendarUtils.Companion.daysInMonthArray
import com.example.calendarapp.elements.CalendarUtils.Companion.monthYearFromDate
import com.example.calendarapp.elements.Event
import java.time.LocalDate

class MonthlyActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monthly_activity)
        initWidgets()
        CalendarUtils.selectedDate = LocalDate.now()
        Log.i("start", "siema")
        setMonthView()
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
        setMonthView()
    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.monYearLabel)
    }

    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(CalendarUtils.selectedDate)

        val daysInMonth = daysInMonthArray()
        val calendarAdapter = CalendarAdapter(daysInMonth, this)
        val layoutManager = GridLayoutManager(applicationContext, 7)

        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter
    }

    fun prevMonAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1)
        setMonthView()
    }

    fun nextMonAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1)
        setMonthView()
    }

    override fun onItemClick(position: Int, date: LocalDate) {
        CalendarUtils.selectedDate = date
        setMonthView()
    }

    fun weeklyAction(view: View) {
        Log.i("monthlyActivity", "startingWeeklyActivity...")
        startActivity(Intent(this, WeeklyActivity::class.java))
        Log.i("monthlyActivity", "weeklyActivity started...")
    }
}