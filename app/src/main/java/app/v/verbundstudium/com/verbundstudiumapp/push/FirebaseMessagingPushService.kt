package app.v.verbundstudium.com.verbundstudiumapp.push

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class FirebaseMessagingPushService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        super.onMessageReceived(remoteMessage)
        var title = remoteMessage.notification?.title ?: ""
        var body = remoteMessage.notification?.body ?: ""
        Timber.d("dschmidt push received title: ${title}  and body= $body and ${remoteMessage.data}" )
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }
}