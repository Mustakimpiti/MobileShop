package com.example.minor

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class catlistmst(context: Context) : SQLiteOpenHelper(context, "Data", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("Create Table if not exists catagoryMST(catid int PRIMARY KEY,"+
                "catname text ," +
                "catimg int)")
        Log.i("Status", "Create Table")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    @SuppressLint("Recycle")
    fun getMax(): Int
    {
        var DB = this.writableDatabase
        var Query = "Select Coalesce(max(catid), 0) from catagoryMST"
        var Cursor = DB.rawQuery(Query, null)
        Cursor.moveToFirst()
        var Id = Cursor.getInt(0)
        return  (Id + 1)
    }
    fun createcat(){
        var DB = this.writableDatabase
        DB?.execSQL("Create Table if not exists catagoryMST(catid int PRIMARY KEY,"+
                "catname text ," +
                "catimg int)")
    }

    @SuppressLint("Recycle")
    fun InsertCategory()
    {
        createcat()
        var DB = this.writableDatabase

        DB.execSQL("Create Table if not exists catagoryMST(catid int PRIMARY KEY,"+
                "catname text ," +
                "catimg int)")

        var Query = "Select count(*) from catagoryMST"
        var C: Cursor = DB.rawQuery(Query, null)

        C.moveToFirst()
        var Count = C.getInt(0)

        if (Count == 0)
        {
            var CategoryList= arrayOf("samsung","oppo ","Apple","Vivo")
            var CategoryImgList = arrayOf(
                R.drawable.samsung_logo,
                R.drawable.oppologo,
                R.drawable.applelogo,
                R.drawable.vivologo)

            for (i in 0 .. (CategoryList.size - 1))
            {
                Query = "Insert into catagoryMST values (${getMax()}, '${CategoryList[i]}', ${CategoryImgList[i]})"
                DB.execSQL(Query)
            }
        }
    }

    @SuppressLint("Recycle")
    fun getCategories() : ArrayList<Category>
    {
        var DB = this.writableDatabase
        var CategoryList = ArrayList<Category>()
        try
        {
            var Query = "Select * from catagoryMST"
            var C = DB.rawQuery(Query, null)
            while (C.moveToNext())
            {
                var Model = Category()
                Model.CategoryId = C.getInt(0)
                Model.Name = C.getString(1)
                Model.Img = C.getInt(2)
                CategoryList.add(Model)
            }
        }
        catch (exp: Exception)
        {
            Log.i("Database Error: ", exp.message.toString())
        }
        return  CategoryList
    }
}

class Category()
{
    var CategoryId: Int = 0
    var Name: String = ""
    var Img: Int = 0
}