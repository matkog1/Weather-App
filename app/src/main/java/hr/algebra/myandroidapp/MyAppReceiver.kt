package hr.algebra.myandroidapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.myandroidapp.framework.setBooleanPreference
import hr.algebra.myandroidapp.framework.startActivity

class MyAppReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED)
        context.startActivity<HostActivity>()
    }
}