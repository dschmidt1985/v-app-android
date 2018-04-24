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
import timber.log.Timber




class FirebaseMessagingPushService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        super.onMessageReceived(remoteMessage)
        var title = remoteMessage.notification?.title ?: ""
        var body = remoteMessage.notification?.body ?: ""


        showNotifications(title, body, remoteMessage.data)
    }

    private val CHANNEL_ID = "channelID"

    fun showNotifications(headline: String, message: String, data: Map<String, String>) {

        Timber.d("dschmidt push received title: ${headline}  and body= $message and ${data}" )

        val msg = message
        val title = headline + " -> " + data["location"]

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
        manager.notify(data["startDate"]?.hashCode() ?: 1, notification)
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }
}