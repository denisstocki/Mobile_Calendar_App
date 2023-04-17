package com.example.calendarapp.elements

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.R
import java.time.LocalDate

class CalendarViewHolder(
    itemView : View,
    private val onItemListener : CalendarAdapter.OnItemListener,
    private val days : ArrayList<LocalDate>
) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    val dayOfMon : TextView = itemView.findViewById(R.id.cellDayText)
    val parentView : View = itemView.findViewById(R.id.parentView)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        onItemListener.onItemClick(adapterPosition, days[adapterPosition])
    }
}