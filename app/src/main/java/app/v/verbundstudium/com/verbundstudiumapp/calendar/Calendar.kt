package app.v.verbundstudium.com.verbundstudiumapp.calendar

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

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

    fun startTime(): DateTime {
        val isoString = "${eventStart.substring(0, 4)}-${eventStart.substring(4, 6)}-${eventStart.substring(6, 8)}T${eventStart.substring(9, 11)}:${eventStart.substring(11, 13)}:${eventStart.substring(13, 15)}.000Z"
        return DateTime.parse(isoString, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"))
    }

    fun endTime(): DateTime {
        val isoString = "${eventEnd.substring(0, 4)}-${eventEnd.substring(4, 6)}-${eventEnd.substring(6, 8)}T${eventEnd.substring(9, 11)}:${eventEnd.substring(11, 13)}:${eventEnd.substring(13, 15)}.000Z"
        return DateTime.parse(isoString, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"))
    }
}