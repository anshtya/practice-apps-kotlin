package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeMinutes = findViewById(R.id.tvAgeMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {_, selectedYear, selectedMonth, selectedDayOfMonth ->
//                Toast.makeText(this, "date selected", Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/${selectedYear}"
                tvSelectedDate?.text = selectedDate

                //Creating object of simple date format
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                //Creating actual date format from sdf
                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/ 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        tvAgeMinutes?.text = differenceInMinutes.toString()
                    }
                }

            }, year, month, day
            )

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()

//        Toast.makeText(this, "btnDatePicker pressed", Toast.LENGTH_LONG).show()

    }
}