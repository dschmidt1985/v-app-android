package app.v.verbundstudium.com.verbundstudiumapp.lessons

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.v.verbundstudium.com.verbundstudiumapp.R
import kotlinx.android.synthetic.main.lessons_list_item.view.*

class LessonsAdapter(private var lessons: Lessons) : RecyclerView.Adapter<LessonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsAdapter.ViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.lessons_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lessons.events.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lessons.events[position])
    }

    fun refershLessons(lessons: Lessons) {
        this.lessons = lessons
        notifyDataSetChanged()
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(lessonEvent: LessonEvent) {
            with(itemView) {
                date.text = lessonEvent.day
                city.text = lessonEvent.getCity()
                description.text = ""
                for (meeting in lessonEvent.meetings) {
                    description.text = "${description.text}${meeting.semester}: ${meeting.name}, Raum ${meeting.room} \n"
                }
            }
        }
    }

}