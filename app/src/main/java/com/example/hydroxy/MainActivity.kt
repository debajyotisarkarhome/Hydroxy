package com.example.hydroxy

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar


const val userdata = "userdata"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = "Enter Your Details"


        fun raiseSnackError(errorText: String){
            val layout: ConstraintLayout = findViewById(R.id.superParent)
            val snack: Snackbar= Snackbar.make(layout,errorText, Snackbar.LENGTH_LONG)
            snack.show()
        }

        fun goToAppActivity(){
            val intent = Intent(this, appActivity::class.java)
            startActivity(intent)
        }

        val sharedPreferences = getSharedPreferences(userdata, Context.MODE_PRIVATE)
        val hasEnteredData: Boolean = sharedPreferences.getBoolean("hasEnteredData", false)
        if(hasEnteredData){
            goToAppActivity()
        }
        else{
            val dropDown: Spinner = findViewById(R.id.spinner)
            ArrayAdapter.createFromResource(
                this,
                R.array.containerArray,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                dropDown.adapter = adapter
            }

            val saveButton: Button= findViewById(R.id.button)
            saveButton.setOnClickListener {
                val weightField: TextView = findViewById(R.id.weightField)
                var weightData = weightField.text.toString()

                if(weightData==""){
                    weightData="0"
                }

                val containerData: String=dropDown.selectedItem.toString()
                if(weightData.toInt() in 301..19){
                    raiseSnackError("Enter a valid Weight Data")
                }
                else if(containerData=="Choose your water Container"){
                    raiseSnackError("Select a Container from the Dropdown")
                }
                else{
                    with (sharedPreferences.edit()) {
                        putString("weightData",weightData)
                        putString("containerData",containerData)
                        putBoolean("hasEnteredData",true)
                        apply()
                    }
                    goToAppActivity()
                }


            }
        }
    }
}