package com.example.minor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class productdetails : AppCompatActivity() {
    lateinit var img : ImageView
    lateinit var ptdname : TextView
    lateinit var pprice: TextView
    lateinit var btnminus : TextView
    lateinit var btnplus: TextView
    lateinit var qlty: TextView
    lateinit var btnadd: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetails)
        img=findViewById(R.id.img)
        ptdname=findViewById(R.id.txtprdname)
        pprice=findViewById(R.id.txtprdprice)
        btnminus=findViewById(R.id.btnMinus)
        btnplus=findViewById(R.id.btnPlus)
        qlty=findViewById(R.id.txtQty)
        btnadd=findViewById(R.id.btnAddcart)
        showdetails()

    }
    fun showdetails(){
        var intent: Bundle? = intent.extras


        var ImgSrc: Int = intent?.get("image").toString().toInt()
        img.setImageResource(ImgSrc)



        ptdname.text = intent?.get("prdname").toString()
        pprice.text = intent?.get("price").toString()

    }
    fun Plus(view: View)
    {
        try {
            qlty = findViewById(R.id.txtQty)
            var Qty = qlty.text.toString().toInt()
            Qty++
            qlty.setText(Qty.toString())
        }
        catch(exp: Exception)
        {
            Log.i("Error: ", exp.message.toString())
        }
    }

    fun Minus(view: View)
    {
        try {
            qlty = findViewById(R.id.txtQty)
            var Qty = qlty.text.toString().toInt()
            if (Qty > 1)
            {
                Qty--
            }
            qlty.setText(Qty.toString())
        }
        catch(exp: Exception)
        {
            Log.i("Error: ", exp.message.toString())
        }
    }
    fun placeholder(view: View)
    {

        var i = Intent(this, Address::class.java)
        startActivity(i)



    }
    fun gohome(view: View)
    {
        var i = Intent(this, home::class.java)
        startActivity(i)
    }

}