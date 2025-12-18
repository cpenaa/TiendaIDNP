package com.example.tiendaidnp.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.tiendaidnp.utils.NotificationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimerService : Service() {
    companion object {
        private val _isServiceRunning = MutableStateFlow(false)
        val isServiceRunning: StateFlow<Boolean> = _isServiceRunning
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "START" -> {
                startTimer()
                _isServiceRunning.value = true
            }
            "STOP" -> {
                stopTimer()
            }
        }
        return START_STICKY
    }

    private fun startTimer() {
        val helper = NotificationHelper(this)
        val notification = helper.getTimerNotification()
        startForeground(1001, notification)
    }

    private fun stopTimer() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        _isServiceRunning.value = false // <--- AVISAMOS QUE TERMINÓ
        android.util.Log.d("TimerService", "❌ El servicio ha sido DESTRUIDO")
    }
}