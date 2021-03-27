package com.example.soes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Questions_DB_Helper (context: Context)
    : SQLiteOpenHelper(context,DATABASE_NAME,null,1){

    companion object{
        val DATABASE_NAME = "Questions.db"
        val TABLE_NAME = "Questions_table"
        val COL_1 = "id"
        val COL_2 = "question_text"
        val COL_3 = "option1"
        val COL_4 = "option2"
        val COL_5 = "option3"
        val COL_6 = "option4"
        val COL_7 = "answer"
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "question_text TEXT, option1 TEXT, option2 TEXT, option3 TEXT, option4 TEXT, answer INTEGER)")
    }

    fun insertData(quesion_text:String, option1:String, option2:String,
                   option3:String, option4:String, answer:String){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, quesion_text)
        contentValues.put(COL_3, option1)
        contentValues.put(COL_4, option2)
        contentValues.put(COL_5, option3)
        contentValues.put(COL_6, option4)
        contentValues.put(COL_7, answer)
        db.insert(TABLE_NAME, null,contentValues)
        db.close()
    }
    val allData: Cursor
        get (){
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM $TABLE_NAME",null)
            return res
        }

    fun updateData(id: String, quesion_text:String, option1:String, option2:String,
                   option3:String, option4:String, answer:String):Boolean{
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2,quesion_text)
        contentValues.put(COL_3,option1)
        contentValues.put(COL_4,option2)
        contentValues.put(COL_5,option3)
        contentValues.put(COL_6,option4)
        db.update(TABLE_NAME,contentValues,"id = ?", arrayOf(id))
        return true
    }
    fun deleteData(id: String):Int{
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"id = ?", arrayOf(id))
    }
    /* fun getInfo(id: String, firstname:String, lastname:String, age: String,
                 address:String, major:String):Boolean{
         val db=this.writableDatabase
         return true
     }*/

}