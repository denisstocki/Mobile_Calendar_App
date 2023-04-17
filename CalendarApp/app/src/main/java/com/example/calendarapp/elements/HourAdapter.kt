package com.example.calendarapp.elements

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.calendarapp.R
import java.time.LocalTime

class HourAdapter(
    context: Context,
    hourEvents: List<HourEvent>
) : ArrayAdapter<HourEvent>(context, 0, hourEvents) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val event = getItem(position)

        var rowView = convertView
        return if (rowView == null) {
            rowView = LayoutInflater.from(context).inflate(R.layout.hour_cell, parent, false)
            event?.let { setHour(rowView, it.time); setEvents(rowView, it.events) }

            rowView
        } else {
            event?.let { setHour(rowView, it.time); setEvents(rowView, it.events) }

            rowView
        }
    }

    private fun setHour(convertView: View, time: LocalTime) {
        val timeTV = convertView.findViewById<TextView>(R.id.timeLabel)
        timeTV.text = CalendarUtils.formattedShortTime(time)
    }

    private fun setEvents(convertView: View, events: ArrayList<Event>) {
        val event1 = convertView.findViewById<TextView>(R.id.event1)
        val event2 = convertView.findViewById<TextView>(R.id.event2)
        val event3 = convertView.findViewById<TextView>(R.id.event3)

        when (events.size) {
            0 -> {
                hideEvent(event1)
                hideEvent(event2)
                hideEvent(event3)
            }
            1 -> {
                setEvent(event1, events[0])
                hideEvent(event2)
                hideEvent(event3)
            }
            2 -> {
                setEvent(event1, events[0])
                setEvent(event2, events[1])
                hideEvent(event3)
            }
            3 -> {
                setEvent(event1, events[0])
                setEvent(event2, events[1])
                setEvent(event3, events[2])
            }
            else -> {
                setEvent(event1, events[0])
                setEvent(event2, events[1])
                event3.visibility = View.VISIBLE
                val eventsNotShown = "${events.size - 2} More Events"
                event3.text = eventsNotShown
            }
        }
    }

    private fun setEvent(textView: TextView, event: Event) {
        textView.text = event.name
        textView.visibility = View.VISIBLE
    }

    private fun hideEvent(tv: TextView) {
        tv.visibility = View.INVISIBLE
    }

}
