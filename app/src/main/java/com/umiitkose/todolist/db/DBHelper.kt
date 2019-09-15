package com.umiitkose.todolist.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object{


        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "todolist.db"


    val TABLE_NAME = "table_task"
    val KEY_ID= "_id"
    val KEY_NAME= "name"
    val KEY_DATE= "date"


    private var mInstane: DBHelper? = null


    @Synchronized fun getInstance(context: Context): DBHelper{
        if(mInstane==null){
            mInstane = DBHelper(context.applicationContext)
        }
        return mInstane as DBHelper
    }

    }


    override fun onCreate(db: SQLiteDatabase?) = createTable(db)

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    private fun createTable(db : SQLiteDatabase?){

        val CREATE_TASK_TABLE = "CREATE TABLE $TABLE_NAME($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,$KEY_NAME text, $KEY_DATE text) "
        db?.execSQL(CREATE_TASK_TABLE)
    }

}