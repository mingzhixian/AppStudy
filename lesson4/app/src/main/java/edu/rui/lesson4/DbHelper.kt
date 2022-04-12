package edu.rui.lesson4

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(

    context: Context?,
    name: String?,
    version: Int
) : SQLiteOpenHelper(context, name, null, version) {


    private val TAG = "@@DBHelper"

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "DB onCreate ... ")
        db.execSQL(
            "CREATE TABLE ToDoDb (\n" +
                    "\t id integer PRIMARY KEY autoincrement,\n" +
                    "\t content text,\n" +
                    "\t status integer not null)"
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "DB on upgrade ,version from $oldVersion to $newVersion")
        if (oldVersion < 2 && newVersion >= 2) {
            // 1 -> 2
            Log.d(TAG, "DB on upgrade exec 1-2 ")
            //db.execSQL("create table User (id integer primary key autoincrement,name text)")

        }
        if (oldVersion < 3 && newVersion >= 3) {
            // 2 -> 3
            Log.d(TAG, "DB on upgrade exec 2-3 ")
            //db.execSQL("create table Note (id integer primary key autoincrement,content text)")
        }

    }
}