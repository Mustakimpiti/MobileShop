package com.example.minor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Address : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        val et1=findViewById<EditText>(R.id.editTextTextPersonName)
        val et2=findViewById<EditText>(R.id.editTextTextPersonName2)
        val et3=findViewById<EditText>(R.id.editTextTextPersonName3)
        val et4=findViewById<EditText>(R.id.editTextNumber)
        val btn=findViewById<Button>(R.id.button)

        btn.setOnClickListener{
            val et11=et1.text.toString().trim()
            val et12=et1.text.toString().trim()
            val et13=et1.text.toString().trim()
            val et14=et1.text.toString().trim()
            if (et11.toString().isEmpty()){
                et1.error="fill the name"
            }
            if(et12.toString().isEmpty()){
                et2.error="fill the pass"

            }
            if(et13.toString().isEmpty()){
                et3.error="fill the email"

            }
            if(et14.toString().isEmpty()){
                et4.error="fill the pass"

            }else{

            Toast.makeText(this,"order placed", Toast.LENGTH_SHORT).show()
            var i = Intent(this, end::class.java)
            startActivity(i)
            }


        }
    }
}