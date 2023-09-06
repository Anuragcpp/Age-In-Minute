package `as`.example.dobcalculator.ageInMinute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null ;
    private var timeMinute : TextView? = null ;
    private  var tvTimeInHour : TextView ? = null ;
    private  var tvTimeInYear : TextView ? = null ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate = findViewById(R.id.date)
        timeMinute = findViewById(R.id.timeInMinute)
        tvTimeInHour = findViewById(R.id.timeInHours)
        tvTimeInYear = findViewById(R.id.timeInYear)
        val btnDatePikker : Button = findViewById(R.id.btnDatePikker)
        btnDatePikker.setOnClickListener {
             clickDatePikker()
        }
    }

    private  fun clickDatePikker (){

        val myCalender  = Calendar.getInstance();
        var year : Int = myCalender.get(Calendar.YEAR)
        var month : Int = myCalender.get(Calendar.MONTH)
        var days : Int = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDaysOfMonth ->

                val selectedDate = "$selectedDaysOfMonth/${selectedMonth +1}/$selectedYear"

                tvSelectedDate?.text = selectedDate ;

                val sdf = SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate);

                theDate?.let {
                    val selectedDateInMinute = theDate.time / 60000 ;

//                    val selectedDateInHours = theDate.time / 3600000 ;

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentTimeInMinute = currentDate.time /60000;

                        val difference = currentTimeInMinute - selectedDateInMinute ;

                        val differenceInHours = difference/60 ;

                        val remainingMonths = (differenceInHours%(24*365))/(24*30)

                        val differenceInYears = differenceInHours/(24*365);

                        timeMinute?.text = difference.toString();
                        tvTimeInHour?.text = (differenceInHours).toString();
                        tvTimeInYear?.text = " ${differenceInYears.toString()}  and ${remainingMonths.toString()} months"

                    }



                }







            },
            year,
            month,
            days

        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}