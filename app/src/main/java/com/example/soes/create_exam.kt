package com.example.soes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast

class create_exam : AppCompatActivity() {
    lateinit var add_question_Button: Button
    lateinit var listView: ListView
    internal var helper = Questions_DB_Helper(this)
    lateinit var refreshET: ImageButton
    lateinit var homeET: ImageButton

    var list = mutableListOf<question>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_exam)

        homeET = findViewById(R.id.home_addQ)
        homeET.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        listView = findViewById(R.id.listView1)
        add_question_Button = findViewById(R.id.AddQuestion)
        refreshET = findViewById(R.id.refresh)

        viewAll()

        val adapter = adapter_questions(this, R.layout.question,list)
        listView.adapter = adapter

        refreshET.setOnClickListener{
            viewAll()
            adapter.notifyDataSetChanged()
        }
        add_question_Button.setOnClickListener {
            val intent = Intent(this, add_Question::class.java)
            startActivity(intent)
        }

    }
    private fun viewAll(){
        list.clear()
        val res = helper.allData
        if (res.count ==0){
            Toast.makeText(this,"no records", Toast.LENGTH_LONG).show()
        }
        while (res.moveToNext()){
            list.add(question(res.getString(0),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6))
            )

        }
    }
}