package app.v.verbundstudium.com.verbundstudiumapp.lessons

import com.google.gson.annotations.SerializedName



data class Lessons(@SerializedName("plan") val plan: String,
                 @SerializedName("events") val events: List<LessonEvent>)

data class LessonEvent(@SerializedName("number") val number: String,
                       @SerializedName("day") val day: String,
                       @SerializedName("ort") private val city: String,
                       @SerializedName("meetings") val meetings: List<LessonMeeting>) {

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

data class LessonMeeting(@SerializedName("semester") val semester: String,
                         @SerializedName("name") val name: String,
                         @SerializedName("room") val room: String)