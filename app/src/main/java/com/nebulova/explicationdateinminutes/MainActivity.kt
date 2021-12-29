package com.nebulova.explicationdateinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDataPicker: Button = findViewById(R.id.btnDatePicker)
        btnDataPicker.setOnClickListener {
            clickDataPicker()
        }
    }
    private fun clickDataPicker(){

        val myCalendar = Calendar.getInstance()
        val year = 1870
        val month = 0
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val tvSelectedDate:TextView = findViewById(R.id.tvSelectedDate)
        val tvSelectedDateInMinutes:TextView= findViewById(R.id.tvSelectedDateInMinutes)


        val dpd = DatePickerDialog(this,{_, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            println(theDate)

            theDate?.let {
                val selectedDateInMinutes = theDate.time/60000
                println(selectedDateInMinutes)
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                println(currentDate)

                val differenceInMinutes = currentDate.time/60000 - selectedDateInMinutes

                tvSelectedDateInMinutes.text = differenceInMinutes.toString()



            }

        }, year, month, day)

        dpd.show()
    }
}