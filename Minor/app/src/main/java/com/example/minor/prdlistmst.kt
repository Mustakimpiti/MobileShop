package com.example.minor

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.minor.R
import java.lang.Exception

class prdlistmst(context: Context) : SQLiteOpenHelper(context, "Data", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("Create Table if not exists productMST(prdid int PRIMARY KEY, catid int, name text, price int, img int)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    fun CreateTable()
    {
        var DB  = this.writableDatabase
        var Query = "Create Table if not exists productMST(prdid int PRIMARY KEY, catid int, name text, price int, img int)"
        DB.execSQL(Query)
    }

    @SuppressLint("Recycle")
    fun getMax(): Int
    {
        var DB = this.writableDatabase
        var Query = "Select Coalesce(max(catid), 0) from productMST"
        var Cursor = DB.rawQuery(Query, null)
        Cursor.moveToFirst()
        var Id = Cursor.getInt(0)
        return  (Id + 1)
    }

    @SuppressLint("Recycle")
    fun InsertProduct()
    {
        CreateTable()
        var DB = this.writableDatabase


        var Query = "Select count(*) from productMST"
        var C: Cursor = DB.rawQuery(Query, null)

        C.moveToFirst()
        var Count = C.getInt(0)

        if (Count == 0)
        {
            var prdList= arrayOf("s21", "s211","ss","sgalexy15","s23",
                "oppo14","oppo1k","oppo21","oppok10","oppoak",
                "i20","iphone11plus","iphone12","iphone11","apple13",
                "vivomax","vivov23","vivox15","vivos10e","vivoy15")
            var prdImgList = arrayOf(
                R.drawable.s21, R.drawable.s211,R.drawable.ss,R.drawable.sgalexy15,R.drawable.s23,
                R.drawable.oppo14,R.drawable.oppo1k,R.drawable.oppo21,R.drawable.oppok10,R.drawable.oppoak,
                R.drawable.i20,R.drawable.iphone11plus,R.drawable.iphone12,R.drawable.iphone11,R.drawable.apple13,
                R.drawable.vivomax,R.drawable.vivov23,R.drawable.vivox15,R.drawable.vivos10e,R.drawable.vivoy15
                )
            var price = arrayOf(10000,20000,12500,14000,31000,
                10000,32000,12500,16700,21000,
                23000,13000,24000,12000,45000,
                20000,13000,14000,15000,16000)
            var catid= arrayOf(1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4)

            Log.i("Array Size", price.size.toString())
            var Id = getMax()
            for (i in 0 .. (price.size - 1))
            {
                Query = "Insert into productMST values (${Id},${catid[i]},'${prdList[i]}','${price[i]}',${prdImgList[i]})"
                DB.execSQL(Query)
                Id++
            }
            Log.i("Status", "Data are Saved")
        }
        else
        {
            Log.i("Status", "Data is Already Exist")
        }
    }

    @SuppressLint("Recycle")
    fun getProudt() : ArrayList<product>
    {
        var DB = this.writableDatabase
        var ProductList = ArrayList<product>()
        try
        {
            var Query = "Select * from productMST"
            var C = DB.rawQuery(Query, null)
            while (C.moveToNext())
            {
                var Model = product()
                Model.prdid= C.getInt(0)
                Model.catid = C.getInt(1)
                Model.prdname = C.getString(2)
                Model.price=C.getFloat(3)
                Model.img=C.getInt(4)
                ProductList.add(Model)
            }
        }
        catch (exp: Exception)
        {
            Log.i("Database Error: ", exp.message.toString())
        }
        return  ProductList
    }
    fun getProudtbycat(catid:Int) : ArrayList<product>
    {
        var DB = this.writableDatabase
        var ProductList = ArrayList<product>()
        try
        {
            var Query = "Select * from productMST"
            if (catid != 0)
            {
                Query = "Select * from productMST where catid='$catid'"
            }

            var C = DB.rawQuery(Query, null)
            while (C.moveToNext())
            {
                var Model = product()
                Model.prdid= C.getInt(0)
                Model.catid = C.getInt(1)
                Model.prdname = C.getString(2)
                Model.price=C.getFloat(3)
                Model.img=C.getInt(4)
                ProductList.add(Model)
            }
        }
        catch (exp: Exception)
        {
            Log.i("Database Error: ", exp.message.toString())
        }
        return  ProductList
    }


}

class product()
{
    var prdid:Int=0
    var catid: Int = 0
    var prdname: String = ""
    var price: Float = 0.0F
    var img:Int=0
}