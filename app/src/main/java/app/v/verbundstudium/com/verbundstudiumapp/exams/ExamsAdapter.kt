package app.v.verbundstudium.com.verbundstudiumapp.lessons

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.v.verbundstudium.com.verbundstudiumapp.R
import app.v.verbundstudium.com.verbundstudiumapp.exams.ExamEvent
import app.v.verbundstudium.com.verbundstudiumapp.exams.Exams
import kotlinx.android.synthetic.main.lessons_list_item.view.*

class ExamsAdapter(private var exams: Exams) : RecyclerView.Adapter<ExamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamsAdapter.ViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.exams_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exams.events.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exams.events[position])
    }

    fun refreshExams(exams: Exams) {
        this.exams = exams
        notifyDataSetChanged()
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(exam: ExamEvent) {
            with(itemView) {
                date.text = exam.day
                city.text = exam.getCity()
                description.text = ""
                for (singleExam in exam.exams) {
                    description.text = "${description.text}${singleExam.name}: ${singleExam.start}, Raum ${singleExam.room} \n"
                }
            }
        }
    }

}