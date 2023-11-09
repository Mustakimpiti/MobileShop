package com.example.minor

import android.annotation.SuppressLint
import android.app.Activity
import android.media.Image
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ContentView
import com.example.minor.Category
import com.example.minor.R
import java.text.ParsePosition

class catadp(
    private val context: Activity,
    private val CategoryArray: ArrayList<Category>,
    private val CatName: List<String>
):
    ArrayAdapter<String>(context, R.layout.activity_catlayout,CatName){
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, contentView: View?, parent: ViewGroup):View {
        var inflater = context.layoutInflater
        var rowview = inflater.inflate(R.layout.activity_catlayout, null, false)
        try {

            var lbl = rowview.findViewById<TextView>(R.id.catname)

            var imageview = rowview.findViewById<ImageView>(R.id.catimg)
            lbl?.text =CatName[position]
            imageview.setImageResource(CategoryArray[position].Img)
        }
        catch (exp: Exception)
        {
            Log.i("err",exp.message.toString())
        }

        return rowview
    }
}
