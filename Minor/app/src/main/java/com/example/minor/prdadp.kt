package com.example.minor

import android.app.Activity
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ContentView
import com.example.minor.product
import com.example.minor.R
import com.example.minor.prdlist
import java.text.ParsePosition

class ProductAdp(private val context: Activity, private val poductlist: ArrayList<product>, private val Name: List<String>): ArrayAdapter<String>(context, R.layout.activity_prdlist,Name){
    override fun getView(position: Int,contentView: View?,parent: ViewGroup):View
    {
        var inflater = context.layoutInflater
        var rowview = inflater.inflate(R.layout.activity_prdtemp, null, false)
        var lbl = rowview.findViewById<TextView>(R.id.lblmobilename)
        var lblprice=rowview.findViewById<TextView>(R.id.mobileprice)
        var imageview = rowview.findViewById<ImageView>(R.id.ImageMobile)
        lbl?.text = Name[position]
        lblprice?.text= poductlist[position].price.toString()
        imageview.setImageResource(poductlist[position].img)
        return rowview
    }
}
