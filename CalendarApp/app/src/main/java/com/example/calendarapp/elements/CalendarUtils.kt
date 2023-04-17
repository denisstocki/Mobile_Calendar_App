package com.example.calendarapp.elements

import android.util.Log
import java.time.*
import java.time.format.DateTimeFormatter

class CalendarUtils {
    companion object {
        var selectedDate: LocalDate = LocalDate.now()

        fun formattedDate(date: LocalDate): String {
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            return date.format(formatter)
        }

        fun formattedTime(time: LocalTime): String {
            val formatter = DateTimeFormatter.ofPattern("hh:mm:ss a")
            return time.format(formatter)
        }

        fun formattedShortTime(time: LocalTime): String {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            return time.format(formatter)
        }

        fun monthYearFromDate(date: LocalDate): String {
            val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
            return date.format(formatter)
        }

        fun monthDayFromDate(date: LocalDate): String {
            val formatter = DateTimeFormatter.ofPattern("MMMM d")
            return date.format(formatter)
        }

        fun daysInMonthArray(): ArrayList<LocalDate> {
            Log.i("Calendarius", selectedDate.toString())

            val daysInMonthArray = ArrayList<LocalDate>()

            val yearMonth = YearMonth.from(selectedDate)
            val daysInMonth = yearMonth.lengthOfMonth()

            val prevMonth = selectedDate.minusMonths(1)
            val nextMonth = selectedDate.plusMonths(1)

            val prevYearMonth = YearMonth.from(prevMonth)
            val prevDaysInMonth = prevYearMonth.lengthOfMonth()

            val firstOfMonth = selectedDate.withDayOfMonth(1)
            val dayOfWeek = firstOfMonth.dayOfWeek.value

            for (i in 1..42) {
                Log.i("Calendarius", "fori")
                if (i <= dayOfWeek)
                    daysInMonthArray.add(
                        LocalDate.of(
                            prevMonth.year,
                            prevMonth.month,
                            prevDaysInMonth + i - dayOfWeek
                        )
                    )
                else if (i > daysInMonth + dayOfWeek)
                    daysInMonthArray.add(
                        LocalDate.of(
                            nextMonth.year,
                            nextMonth.month,
                            i - dayOfWeek - daysInMonth
                        )
                    )
                else
                    daysInMonthArray.add(
                        LocalDate.of(
                            selectedDate.year,
                            selectedDate.month,
                            i - dayOfWeek
                        )
                    )
            }

            return daysInMonthArray
        }

        fun daysInWeekArray(selectedDate: LocalDate): ArrayList<LocalDate> {
            val days = ArrayList<LocalDate>()
            Log.i("daysInWeek", "sundayo")
            var current = sundayForDate(selectedDate)
            val endDate = current.plusWeeks(1)
            Log.i("siema", "m1")

            while (current.isBefore(endDate)) {
                Log.i("siema", "sieam")
                days.add(current)
                current = current.plusDays(1)
            }
            return days
        }

        private fun sundayForDate(current: LocalDate): LocalDate {
            var oneWeekAgo = current.minusWeeks(1)
            var curr : LocalDate = current
            Log.i("current", curr.toString())
            while (curr.isAfter(oneWeekAgo)) {
                if (curr.dayOfWeek == DayOfWeek.SUNDAY)
                    break
                Log.i("current", "loop")

                curr = curr.minusDays(1)
            }
            Log.i("current", curr.toString())

            return curr
        }
    }
}
