package app.v.verbundstudium.com.verbundstudiumapp.lessons

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.v.verbundstudium.com.verbundstudiumapp.R
import app.v.verbundstudium.com.verbundstudiumapp.calendar.Calendar
import kotlinx.android.synthetic.main.lessons_list_item.view.*

class CalendarAdapter(private var calendars: List<Calendar>) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarAdapter.ViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.calendar_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return calendars.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(calendars[position])
    }

    fun refreshCalendars(calendars: List<Calendar>) {
        this.calendars = calendars
        notifyDataSetChanged()
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(calendar: Calendar) {
            with(itemView) {
                date.text = calendar.startTime().toString()
                city.text = calendar.eventTitle
                description.text = calendar.eventDescription
            }
        }
    }

}