package com.groktor.kings.firebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.groktor.kings.HomeActivity
import com.groktor.kings.R


class KingsFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "KingsMessagingService"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        Log.e(TAG, "From: " + remoteMessage?.from)

        if (remoteMessage?.notification != null && remoteMessage.notification != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.notification?.body)

            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(this, 0,
                    intent, PendingIntent.FLAG_ONE_SHOT)

            val notification = NotificationCompat.Builder(this,"kings_channel_1")
                    .setContentText(remoteMessage.notification?.body)
                    .setSmallIcon(R.drawable.ic_games)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)

            if (!remoteMessage.notification?.title.isNullOrEmpty())
                notification.setContentTitle(remoteMessage.notification?.title)

            val manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(1, notification.build())

            Log.e(TAG, "sent notification")
        }
    }
}