package com.example.soes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class Exam_info : AppCompatActivity() {
    internal var helper = Exams_DB_Helper(this)

    val types = arrayOf("Quiz", "First", "Second", "Midterm", "Final")
    lateinit var dateET: ImageButton
    lateinit var time1ET: ImageButton
    lateinit var time2ET: ImageButton
    lateinit var addET: Button
    lateinit var subjectET: EditText
    lateinit var typeText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_info_v2)

        subjectET = findViewById(R.id.subject_name)

        addET = findViewById(R.id.add_your_questions)

        val DateText = findViewById<TextView>(R.id.dateText)
        dateET = findViewById(R.id.Date_btn)

        val timeText1 = findViewById<TextView>(R.id.time_text1)
        time1ET = findViewById(R.id.time_btn1)

        val timeText2 = findViewById<TextView>(R.id.time_text2)
        time2ET = findViewById(R.id.time_btn2)


       typeText = findViewById(R.id.type)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,types)
        val spinner = findViewById<Spinner>(R.id.spinner)

        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(this@Exam_info, types[position], Toast.LENGTH_SHORT).show()
                    typeText.text = types[position] //could be an error
                }
                override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
                    TODO("")
                }

            }

        time1ET.setOnClickListener{
            val c = Calendar.getInstance()
            val t = TimePickerDialog.OnTimeSetListener{
                    view, hourOfDay, minute ->

                c.set(Calendar.HOUR_OF_DAY, hourOfDay)
                c.set(Calendar.MINUTE, minute)

                timeText1.text = SimpleDateFormat("HH:mm").format(c.time)
            }
            TimePickerDialog(this, t,
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), true).show()

        }
        time2ET.setOnClickListener{
            val c = Calendar.getInstance()
            val t = TimePickerDialog.OnTimeSetListener{
                    view, hourOfDay, minute ->

                c.set(Calendar.HOUR_OF_DAY, hourOfDay)
                c.set(Calendar.MINUTE, minute)

                timeText2.text = SimpleDateFormat("HH:mm").format(c.time)
            }
            TimePickerDialog(this, t,
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), true).show()

        }

        dateET.setOnClickListener{

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dataVal = DatePickerDialog(
                this, DatePickerDialog.OnDateSetListener
                { view, year, month, dayOfMonth ->
                    DateText.setText("" + dayOfMonth + "/" + month + "/" + year)
                }, year, month, day)
            dataVal.show()
        }
        addET.setOnClickListener {
            helper.insertData(
                    subjectET.text.toString().trim(),
                    typeText.text.toString().trim(), //could be an error
                    DateText.text.toString().trim(),
                    timeText1.text.toString().trim(),
                    timeText2.text.toString().trim())
            Toast.makeText(this, "Exam info Added to DB", Toast.LENGTH_LONG).show()

            var intent = Intent(this, create_exam::class.java)
            startActivity(intent)

        }

    }
}