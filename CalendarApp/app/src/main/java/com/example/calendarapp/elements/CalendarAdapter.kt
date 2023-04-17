package com.example.calendarapp.elements

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.R
import java.time.LocalDate

class CalendarAdapter(
    private val days: ArrayList<LocalDate>,
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calender_cell, parent, false)
        val layoutParams = view.layoutParams

        if (days.size > 15)
            layoutParams.height = (parent.height / 6)
        else
            layoutParams.height = parent.height

        return CalendarViewHolder(view, onItemListener, days)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {

        val date = days[position]

        holder.dayOfMon.text = date.dayOfMonth.toString()

        if (date == CalendarUtils.selectedDate)
            holder.parentView.setBackgroundColor(Color.LTGRAY)

        if (date.month == CalendarUtils.selectedDate?.month)
            holder.dayOfMon.setTextColor(Color.BLACK)
        else{
            holder.dayOfMon.setTextColor(Color.LTGRAY)
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, date: LocalDate)
    }
}
