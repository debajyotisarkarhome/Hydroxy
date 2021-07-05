package com.example.hydroxy

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.util.*


class appActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        //---------------------- Behold The Main Code ------------------------//
        val sharedPreferences = getSharedPreferences(userdata, Context.MODE_PRIVATE)
        var waterAmt: Int = 0
        var remCounter: Int = 0
        var notificationId: Int = 1
        var weightData = sharedPreferences.getString("weightData", "0")
        var containerData = sharedPreferences.getString("containerData", "Glass")
        if (weightData != null) {
            if (weightData.toInt() in 0..45) {
                waterAmt = 1900
            } else if (weightData.toInt() in 46..50) {
                waterAmt = 2100
            } else if (weightData.toInt() in 51..55) {
                waterAmt = 2300
            } else if (weightData.toInt() in 56..60) {
                waterAmt = 2500
            } else if (weightData.toInt() in 61..65) {
                waterAmt = 2700
            } else if (weightData.toInt() in 66..70) {
                waterAmt = 2900
            } else if (weightData.toInt() in 71..75) {
                waterAmt = 3200
            } else if (weightData.toInt() in 76..80) {
                waterAmt = 3500
            } else if (weightData.toInt() in 81..85) {
                waterAmt = 3700
            } else if (weightData.toInt() in 86..90) {
                waterAmt = 3900
            } else if (weightData.toInt() in 91..95) {
                waterAmt = 4000
            } else {
                waterAmt = 4300
            }
        }

        if (containerData=="Glass"){
            remCounter = waterAmt/250
        } else if(containerData=="Bottle"){
            remCounter = waterAmt/300
        } else if(containerData=="Wine Glass"){
            remCounter = waterAmt/360
        }

        //Log.d("debug", remCounter.toString())
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val alarmManager = (getSystemService(ALARM_SERVICE) as AlarmManager)

        for(each in 1..remCounter){
            val dat = Date()
            val cal_alarm: Calendar = Calendar.getInstance()
            val cal_now: Calendar = Calendar.getInstance()
            cal_now.setTime(dat)
            cal_alarm.setTime(dat)
            cal_alarm.set(Calendar.HOUR_OF_DAY, ((24/remCounter)*each))
            cal_alarm.set(Calendar.MINUTE, 0)
            cal_alarm.set(Calendar.SECOND, 0)
            if(cal_alarm.before(cal_now)){
                cal_alarm.add(Calendar.DATE,1);
            }
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, cal_alarm.timeInMillis, pendingIntent) ;
        }


        //with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            //notify(notificationId, builder.build())
        //}



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.settings) {
            val sharedPreferences = getSharedPreferences(userdata, Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putBoolean("hasEnteredData", false)
                apply()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)

    }






}
