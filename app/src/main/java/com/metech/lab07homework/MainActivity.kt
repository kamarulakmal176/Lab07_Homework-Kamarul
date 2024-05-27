package com.metech.lab07homework

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.app.PendingIntent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar
import com.metech.lab07homework.databinding.ActivityMainBinding
import java.nio.file.attribute.AclEntry.Builder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    Nottification permission
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Check and request notification permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestNotificationPermission()
        }

        binding.button.setOnClickListener {
            Snackbar.make(binding.button, "Hi User...",Snackbar.LENGTH_LONG).
                    setAction("OK") {}.setActionTextColor(Color.RED).show()

        }
        binding.button2.setOnClickListener {
            save(binding.button2)

        }
        binding.button3.setOnClickListener {
            sendCourseUpdateNotification()

        }

        binding.button4.setOnClickListener {
            var intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    fun save(view: View) {
        val saveAlert = AlertDialog.Builder(this)
        saveAlert.setTitle("Alert!")
        saveAlert.setMessage("Are you sure want to save the changes?")
        saveAlert.setPositiveButton("OK") { dialogInterface: DialogInterface, _: Int ->
            Snackbar.make(binding.button, "SAVED", Snackbar.LENGTH_LONG).show()
        }
        saveAlert.setNegativeButton("Cancel") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        saveAlert.show()
    }

    private fun sendCourseUpdateNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "channel_01"
            val channelName = "Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Test description"
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
            }

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)

            val resultIntent = Intent(this, ResultActivity::class.java)
            val resultPendingIntent = PendingIntent.getActivity(
                this,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val notification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_lab07_foreground)
                .setContentTitle("Android ATC")
                .setContentText("Check Android ATC New Course!!!")
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .build()

            notificationManager.notify(1, notification)
        } else {
            // Handle notifications for versions below Android O if needed
        }
    }


}