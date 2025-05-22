package com.example.encyclopedia.workers
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters


class ReminderWorker(
    context: Context,
    workerParams:WorkerParameters
):Worker(context,workerParams) {
    override fun doWork(): Result {
        sendNotification("Reminder", "It's time to take a quiz!")
        return Result.success()
    }

    private fun sendNotification(title: String, message: String) {
        val channelId= "reminder_channel"
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                "Reminders",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(applicationContext,channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(Notification.DEFAULT_ALL)
            .build()
        Log.d(TAG, "Sending notification with title: $title and message: $message")
        notificationManager.notify(1, notification)
    }


}