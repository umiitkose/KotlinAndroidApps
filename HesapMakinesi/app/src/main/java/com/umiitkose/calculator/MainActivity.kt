package com.umiitkose.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.time.times

class MainActivity : AppCompatActivity() {

    private lateinit var btMore: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btMore = findViewById(R.id.bt_more)
        registerForContextMenu(btMore)

    }

    fun numberClick(view: View){

        if(yeniOperator){
            et_text.setText("")
        }

        yeniOperator=false
        var temp: String = et_text.text.toString()

        when(view.id){
            R.id.bt_zero -> {
                temp += "0"
            }
            R.id.bt_one -> {
                temp += "1"
            }
            R.id.bt_two -> {
                temp += "2"
            }
            R.id.bt_three -> {
                temp += "3"
            }
            R.id.bt_four -> {
                temp += "4"
            }
            R.id.bt_five -> {
                temp += "5"
            }
            R.id.bt_six -> {
                temp += "6"
            }
            R.id.bt_seven -> {
                temp += "7"
            }
            R.id.bt_eight -> {
                temp += "8"
            }
            R.id.bt_nine -> {
                temp += "9"
            }
            R.id.bt_reverse -> {
                temp="-"+temp
            }
        }
        et_text.setText(temp)
    }

    var operator= "*"
    var eskiSayi=""
    var yeniOperator=true

    fun btnOperationClick(view: View){

        when(view.id){
            R.id.bt_divided ->{
                operator="/"
            }

            R.id.bt_multiple ->{
                operator="*"
            }

            R.id.bt_minus ->{
                operator="-"
            }

            R.id.bt_plus ->{
                operator="+"
            }
        }
        eskiSayi=et_text.text.toString()
        yeniOperator=true
    }

    fun btnEqualsClick(view: View){
        var yeniSayi=et_text.text.toString()
        var sonuc: Double? = null

        when(operator){
            "/" -> {
                sonuc=eskiSayi.toDouble()/yeniSayi.toDouble()
            }
            "*" -> {
                sonuc=eskiSayi.toDouble()*yeniSayi.toDouble()
            }
            "-" -> {
                sonuc=eskiSayi.toDouble()-yeniSayi.toDouble()
            }
            "+" -> {
                sonuc=eskiSayi.toDouble()+yeniSayi.toDouble()
            }
        }

        et_text.setText(sonuc.toString())
        yeniOperator=true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_context, menu)
        menu?.setHeaderTitle(R.string.moreoperation)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.factorial ->{ TODO()
            true
            }
            R.id.sqrt -> {TODO()
            true
            }
            R.id.exponentiate -> {TODO()
            true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}
