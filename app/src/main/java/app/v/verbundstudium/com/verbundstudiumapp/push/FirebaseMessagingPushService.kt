package app.v.verbundstudium.com.verbundstudiumapp.push

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import app.v.verbundstudium.com.verbundstudiumapp.MainActivity
import app.v.verbundstudium.com.verbundstudiumapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import timber.log.Timber


class FirebaseMessagingPushService : FirebaseMessagingService() {
    val dateTimePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        super.onMessageReceived(remoteMessage)
        val title = remoteMessage.notification?.title ?: ""
        val body = remoteMessage.notification?.body ?: ""

        var start: DateTime? = null
        var end: DateTime? = null
        var fullDay = false

        remoteMessage.data["startDate"]?.let { Timber.d("$it")
            start = DateTime.parse(it, DateTimeFormat.forPattern(dateTimePattern)) }
        remoteMessage.data["endDate"]?.let { end = DateTime.parse(it, DateTimeFormat.forPattern(dateTimePattern)) }
        remoteMessage.data["fullDay"]?.let { fullDay = it.toBoolean() }

        val event = EventPushObject(title, body, remoteMessage.data["location"],
                start, end, fullDay)





        showNotifications(event)
    }

    private val CHANNEL_ID = "channelID"

    fun showNotifications(event: EventPushObject) {

        Timber.d("push received ${event}")

        val title = event.title + " -> " + event.location

        var msg: String
        if (event.fullDay) {
            msg = event.start?.toString("dd.MM.yyyy") ?: ""
            if (msg.isNotEmpty()) {
                msg += " "
            }
            msg += event.body
        } else {
            msg = event.start?.toString("HH:mm - dd.MM.yyyy") ?: ""
            if (msg.isNotEmpty()) {
                msg += " - "
            }
            event.end?.let {
                msg += it.toString("HH:mm - dd.MM.yyyy")
                msg += " "

            }
            msg += event.body
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "Calendar Channel"
            val description = "All Events from Ilias Calendar"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(
                    Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val i = Intent(this, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 1,
                i, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification: Notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notification = NotificationCompat.Builder(this)
                    .setContentText(msg)
                    .setContentTitle(title)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setChannelId(CHANNEL_ID)
                    .build()

        } else {
            notification = NotificationCompat.Builder(this)
                    .setContentText(msg)
                    .setContentTitle(title)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
                    .build()

        }
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(event.start?.hashCode() ?: 1, notification)
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }
}