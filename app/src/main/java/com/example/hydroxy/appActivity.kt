package com.example.hydroxy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class appActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        //---------------------- Behold The Main Code ------------------------//
        val sharedPreferences = getSharedPreferences(userdata, Context.MODE_PRIVATE)
        var waterAmt: Int
        var weightData = sharedPreferences.getString("weightData", "0")
        var containerData = sharedPreferences.getString("containerData", "Glass")
        if (weightData != null) {
            if (weightData.toInt() in 9..13) {
                waterAmt = 2500
            } else if (weightData.toInt() in 14..18) {
                waterAmt = 3000
            } else {
                waterAmt = 4000
            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.settings) {
            val sharedPreferences = getSharedPreferences(userdata, Context.MODE_PRIVATE)
            with (sharedPreferences.edit()) {
                putBoolean("hasEnteredData",false)
                apply()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)

    }






}
