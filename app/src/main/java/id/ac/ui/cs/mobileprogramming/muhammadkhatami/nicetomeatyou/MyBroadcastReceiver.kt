package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {

    var idCounter: Int = 0

    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context)
        StringBuilder().apply {
            append("Timeout")
            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun showNotification(context: Context) {
        val name = "channel_name"
        val descriptionText = "channel_description"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel("CHANNEL_ID", name, importance)
        mChannel.description = descriptionText
        // Register the channel with the system;  can't change the importance
        // or other notification behaviors after this
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)

        val contentIntent = PendingIntent.getActivity(
            context, 0,
            Intent(context, DetailRecipeActivity::class.java), 0
        )

        val mBuilder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setSmallIcon(R.drawable.mainlogo)
            .setContentTitle("Timeout!")
            .setContentText("Flip your steak")
        mBuilder.setContentIntent(contentIntent)
        mBuilder.setDefaults(RingtoneManager.TYPE_NOTIFICATION)
        mBuilder.setAutoCancel(true)
        mBuilder.setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))

        idCounter += 1
        with(NotificationManagerCompat.from(context)) {
            notify(idCounter, mBuilder.build())
        }
    }
}