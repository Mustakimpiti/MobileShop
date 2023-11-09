package com.example.minor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ListView

class prdlist : AppCompatActivity() {
    lateinit var list:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prdlist)
        list=findViewById(R.id.ProdList)
        var i: Bundle? = intent.extras
        var Id = i?.get("CategoryId").toString().toInt()
        var SQL = prdlistmst(this)
        var Data = SQL.getProudtbycat(Id)

        var prdname: String = ""

        for (i in 0..Data.size - 1)
        {
            prdname = prdname + Data[i].prdname + ", "
        }

        var ProdutNameArray = prdname.substring(0, prdname.length - 2).split(",")

        var adp = ProductAdp(this, Data, ProdutNameArray)
        list.adapter = adp

        list.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                var I = Intent(this@prdlist, productdetails::class.java)
                I.putExtra("prdname", Data[position].prdname)
                I.putExtra("price", Data[position].price)
                I.putExtra("image", Data[position].img)
                startActivity(I)
            }
        })

}

    }

