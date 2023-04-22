package com.example.captrih

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.captrih.R.id.tvAgeInMinutes
import com.example.captrih.R.id.tvSelectedDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null    //creating private variable for selected date
    private var tvAgeInMinutes : TextView?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//BUTTON
        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)


        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }


    }
    fun clickDatePicker(){                             // on clicking the button

        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { view, selectedYear, selectedMonth, selectedDayOfMonth -> Toast.makeText(
                this,
                "Year was $selectedYear, month was ${selectedMonth + 1}, day was $selectedDayOfMonth",
                Toast.LENGTH_SHORT
            ).show()

            //lambda expression is used  //view, year, month ==parameters      //declaring to the view on line number 16

            val selectedDate = "$selectedDayOfMonth-$selectedMonth-$selectedYear"

            tvSelectedDate?.text = selectedDate

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate =sdf.parse(selectedDate)

            val selectedDateInMinutes=theDate.time/60000       //in order to convert

            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInMinutes=currentDate.time/60000

            val differenceInMinutes= currentDateInMinutes - selectedDateInMinutes

            tvAgeInMinutes?.text =differenceInMinutes.toString()                  //? for type safety
        },year, month, day).show()






    }
}
