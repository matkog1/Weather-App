package hr.algebra.myandroidapp.api

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AppWorker(
    private val context:Context,
    workerParams: WorkerParameters): Worker(context, workerParams) {
    override fun doWork(): Result {
        MyAppFetcher(context).fetchItems("Zagreb")
        return Result.success()
    }
}