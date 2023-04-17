package com.example.calendarapp.elements

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.calendarapp.R

class EventAdapter(
    context: Context,
    events: List<Event>
) : ArrayAdapter<Event>(context, 0, events) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val event = getItem(position)
        var itemView = convertView

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.event_cell, parent, false)
            val eventCellTV: TextView = itemView.findViewById(R.id.eventCellLabel)
            val eventTitle = "${event?.name} ${event?.time?.let { CalendarUtils.formattedTime(it) }}"

            eventCellTV.text = eventTitle

            return itemView
        } else {
            val eventCellTV: TextView = itemView.findViewById(R.id.eventCellLabel)
            val eventTitle = "${event?.name} ${event?.time?.let { CalendarUtils.formattedTime(it) }}"

            eventCellTV.text = eventTitle

            return itemView
        }
    }
}