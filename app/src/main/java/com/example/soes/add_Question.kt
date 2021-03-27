package com.example.soes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class add_Question : AppCompatActivity() {
    internal var helper = Questions_DB_Helper(this)

    lateinit var backET: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__question)

        lateinit var question_textET: EditText
        lateinit var option1ET: EditText
        lateinit var option2ET: EditText
        lateinit var option3ET: EditText
        lateinit var option4ET: EditText
        lateinit var answerET: RadioGroup
        lateinit var insertToQuestion_DB: Button

        question_textET = findViewById<EditText>(R.id.TextOfQuestion)
        option1ET = findViewById<EditText>(R.id.option1)
        option2ET = findViewById<EditText>(R.id.option2)
        option3ET = findViewById<EditText>(R.id.option3)
        option4ET = findViewById<EditText>(R.id.option4)
        //answerET = findViewById<RadioGroup>(R.id.correct_answer)
        insertToQuestion_DB = findViewById<Button>(R.id.AddQuestionToDBButton)
        backET = findViewById(R.id.back)

        lateinit var answer_choice: RadioButton


        backET.setOnClickListener {
            val intent = Intent(this, create_exam::class.java)
            startActivity(intent)
        }
        insertToQuestion_DB.setOnClickListener{
            answer_choice=findViewById<RadioButton>(findViewById<RadioGroup>(R.id.correct_answer).checkedRadioButtonId)

            helper.insertData(
                    question_textET.text.toString().trim(),
                    option1ET.text.toString().trim(),
                    option2ET.text.toString().trim(),
                    option3ET.text.toString().trim(),
                    option4ET.text.toString().trim(),
                    answer_choice.text.toString().trim())
            Toast.makeText(this, "Question Added", Toast.LENGTH_LONG).show()
        }


    }

}