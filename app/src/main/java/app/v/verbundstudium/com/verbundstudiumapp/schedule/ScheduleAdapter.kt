package app.v.verbundstudium.com.verbundstudiumapp.schedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.v.verbundstudium.com.verbundstudiumapp.R
import kotlinx.android.synthetic.main.dish_list_item.view.*

class ScheduleAdapter(private var schedule: Schedule) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.dish_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return schedule.events.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(schedule.events[position])
    }

    fun refershSchedule(schedule: Schedule) {
        this.schedule = schedule
        notifyDataSetChanged()
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(scheduleEvent: ScheduleEvent) {
            with(itemView) {
                menu_name.text = scheduleEvent.number
                description.text = scheduleEvent.meetings.toString()
                students_price_value.text = scheduleEvent.getCity()
                guest_price_value.text = scheduleEvent.day
            }
        }
    }

}