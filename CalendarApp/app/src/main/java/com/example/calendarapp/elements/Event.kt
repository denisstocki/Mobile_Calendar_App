package com.example.calendarapp.elements

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate
import java.time.LocalTime

class Event(
    val name: String,
    val date: LocalDate,
    val time: LocalTime
) : Parcelable{
    companion object {
        var eventsList = ArrayList<Event>()

        fun eventsForDate(date: LocalDate): ArrayList<Event> {
            val events = ArrayList<Event>()

            for (event in eventsList) {
                if (event.date == date)
                    events.add(event)
            }

            return events
        }

        fun eventsForDateAndTime(date: LocalDate, time: LocalTime): ArrayList<Event> {
            val events = ArrayList<Event>()

            for (event in eventsList) {
                if (event.date == date && event.time.hour == time.hour)
                    events.add(event)
            }

            return events
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(name)
        parcel.writeSerializable(date)
        parcel.writeSerializable(time)
    }
}
