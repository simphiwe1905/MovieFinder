package com.example.moviefinder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // When in background, Android handles the notification automatically.
        // This code handles it when the app is open (foreground).
        val title = remoteMessage.notification?.title ?: "MovieFinder"
        val body = remoteMessage.notification?.body ?: "Test Notification"

        showNotification(title, body)
    }

    private fun showNotification(title: String, body: String) {
        val channelId = "movie_updates"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Movie Updates",
                NotificationManager.IMPORTANCE_HIGH // Changed to HIGH so you see it pop up
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
}