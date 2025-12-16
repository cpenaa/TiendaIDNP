package com.example.tiendaidnp.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.tiendaidnp.utils.NotificationHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OfferWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val currentTime = timeFormat.format(Date())

        Log.d(
            "OfferWorker",
            "🔄 ($currentTime) Ejecutando tarea en segundo plano: Buscando ofertas..."
        )

        val notificationHelper = NotificationHelper(applicationContext)
        notificationHelper.sendNotification(
            "¡Oferta Flash!",
            "Revisado a las $currentTime. Descuentos disponibles."
        )

        return Result.success()
    }
}