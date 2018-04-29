package app.v.verbundstudium.com.verbundstudiumapp.push

import org.joda.time.DateTime

data class EventPushObject(val title: String, val body: String, val location: String?,
                           val start: DateTime?, val end: DateTime?, val fullDay: Boolean)