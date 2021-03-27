package com.example.soes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Exams_DB_Helper (context: Context)
    : SQLiteOpenHelper(context,DATABASE_NAME,null,1){

    companion object{
        val DATABASE_NAME = "Exams.db"
        val TABLE_NAME = "Exams_table"
        val COL_1 = "id"
        val COL_2 = "Subject_name"
        val COL_3 = "Type1"
        val COL_4 = "Date1"
        val COL_5 = "From1"
        val COL_6 = "To1"
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Subject_name TEXT, Type1 TEXT, Date1 TEXT, From1 TEXT, To1 TEXT)")
    }

    fun insertData(Subject_name:String, Type:String, Date:String,
                   From:String, To:String){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, Subject_name)
        contentValues.put(COL_3, Type)
        contentValues.put(COL_4, Date)
        contentValues.put(COL_5, From)
        contentValues.put(COL_6, To)
        db.insert(TABLE_NAME, null,contentValues)
        db.close()
    }
    val allData: Cursor
        get (){
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM $TABLE_NAME",null)
            return res
        }

    fun updateData(id: String, Subject_name:String, Type:String, Date:String,
                   From:String, To:String):Boolean{
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2,Subject_name)
        contentValues.put(COL_3,Type)
        contentValues.put(COL_4,Date)
        contentValues.put(COL_5, From)
        contentValues.put(COL_6,To)
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