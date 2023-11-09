package com.example.minor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.GridView

class home : AppCompatActivity() {
    lateinit var grid:GridView

    override fun onBackPressed() {
        finishAffinity()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        grid=findViewById(R.id.list1)
        PopulateCategory()
    }
    fun PopulateCategory() {

        try {
            var SQL = catlistmst(this)
            var Data = SQL.getCategories()

            var size = Data.size

            var Model = Category()


            var CategoryName: String = ""

            for (i in 0..Data.size - 1)
            {
                CategoryName = CategoryName + Data[i].Name + ", "
            }

            var CategoryArray = CategoryName.substring(0, CategoryName.length - 2).split(",")

            var adp = catadp(this, Data, CategoryArray)
            grid.adapter = adp

            grid.setOnItemClickListener(object: AdapterView.OnItemClickListener{
                override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    var I = Intent(this@home, prdlist::class.java)
                    I.putExtra("CategoryId", Data[position].CategoryId.toString())
                    startActivity(I)
                }
            })
        }
        catch (exp: Exception)
        {
            Log.i("Error: ", exp.message.toString())
        }
    }
}