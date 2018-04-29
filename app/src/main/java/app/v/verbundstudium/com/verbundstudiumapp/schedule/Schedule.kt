package app.v.verbundstudium.com.verbundstudiumapp.schedule

import com.google.gson.annotations.SerializedName

data class Schedule(@SerializedName("plan") val plan: String,
                    @SerializedName("events") val events: List<ScheduleEvent>)

data class ScheduleEvent(@SerializedName("number") val number: String,
                         @SerializedName("day") val day: String,
                         @SerializedName("ort") private val city: String,
                         @SerializedName("meetings") val meetings: Map<String, String>) {

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
