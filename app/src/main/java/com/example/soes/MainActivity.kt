package com.example.soes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity :  AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var create_button: Button
    lateinit var listView_home: ListView
    internal var helper_home = Exams_DB_Helper(this)
    lateinit var refreshET: ImageButton


    var list = mutableListOf<exam>() //might change it to question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_Layout)

        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this,drawerLayout, toolbar,0,0)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        create_button = findViewById(R.id.createExam)

        create_button.setOnClickListener {
            var intent = Intent(this, Exam_info::class.java)
            startActivity(intent)
        }
       listView_home = findViewById(R.id.listView_home)
        viewAll()
        val adapter = adapter_exams(this, R.layout.question,list)
        listView_home.adapter = adapter

        refreshET = findViewById(R.id.viewList)
        refreshET.setOnClickListener{
            viewAll()
            adapter.notifyDataSetChanged()
        }

    }
    private fun viewAll(){
        list.clear()
        val res = helper_home.allData
        if (res.count ==0){
            Toast.makeText(this,"no records", Toast.LENGTH_LONG).show()
        }
        while (res.moveToNext()){
            list.add(exam(res.getString(0),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5))
            )

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout->{
               var intent = Intent(this, launchPage::class.java)
                startActivity(intent)
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}