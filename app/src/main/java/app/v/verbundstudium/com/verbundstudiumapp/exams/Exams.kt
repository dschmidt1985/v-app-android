package app.v.verbundstudium.com.verbundstudiumapp.exams

import com.google.gson.annotations.SerializedName



data class Exams(@SerializedName("plan") val plan: String,
                 @SerializedName("events") val events: List<ExamEvent>)

data class ExamEvent(@SerializedName("number") val number: String,
                       @SerializedName("day") val day: String,
                       @SerializedName("ort") private val city: String,
                       @SerializedName("exams") val exams: List<SingleExam>) {

    fun getCity():String {
        if (city == "DO") {
            return "Dortmund"
        } else if (city == "GM") {
            return "Gummersbach"
        } else {
            return city
        }
    }
}

data class SingleExam(@SerializedName("start") val start: String,
                         @SerializedName("name") val name: String,
                         @SerializedName("room") val room: String)