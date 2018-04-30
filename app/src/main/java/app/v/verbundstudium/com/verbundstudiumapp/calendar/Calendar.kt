package app.v.verbundstudium.com.verbundstudiumapp.calendar

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class Calendar(@SerializedName("_id") val id: String,
                    @SerializedName("calendar_id") val calendarId: Int,
                    @SerializedName("event_id") val eventId: Int,
                    @SerializedName("calendar_title") val calendarTitle: String,
                    @SerializedName("event_title") val eventTitle: String,
                    @SerializedName("event_description") val eventDescription: String,
                    @SerializedName("event_location") val eventLocation: String,
                    @SerializedName("event_start") val eventStart: String,
                    @SerializedName("event_end") val eventEnd: String,
                    @SerializedName("event_full_day") val fullDay: Boolean) {

    private val dateTimePattern = "yyyyMMddTHHmmssZ"

    fun startTime(): DateTime {
        return DateTime(eventStart.substring(0, 4).toInt(), eventStart.substring(4, 6).toInt(),
                eventStart.substring(6, 8).toInt(), eventStart.substring(9, 11).toInt(),
                eventStart.substring(11, 13).toInt(), eventStart.substring(13, 15).toInt())
    }

    fun endTime(): DateTime {
        return DateTime(eventEnd.substring(0, 4).toInt(), eventEnd.substring(4, 6).toInt(),
                eventEnd.substring(6, 8).toInt(), eventEnd.substring(9, 11).toInt(),
                eventEnd.substring(11, 13).toInt(), eventEnd.substring(13, 15).toInt())
    }
}