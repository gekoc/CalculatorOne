package com.example.calculatorone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var textViewInput: TextView? = null
    private var lastNumeric : Boolean = true
    private var lastDot :Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewInput = findViewById(R.id.textViewInput)
    }

    fun onDigit(view: View){
        textViewInput?.append((view as Button).text)
        lastNumeric = true

    }

    fun onClear(view: View){
        textViewInput?.text = ""
        lastNumeric = true
        lastDot = false
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            textViewInput?.append(".")
            lastNumeric = false
            lastDot = true
        }

    }

    fun onOperator(view: View){
        textViewInput?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                textViewInput?.append((view as Button).text)
                lastNumeric = false
            }
        }

    }

    fun onEqual(view: View){
        if(lastNumeric){
            var textVievValue = textViewInput?.text.toString()
            var prefix = ""

            try{
                if(textVievValue.startsWith("-")){
                    prefix = "-"
                    textVievValue = textVievValue.substring(1)
                }
                if(textVievValue.contains("-")){
                    val splitValue = textVievValue.split("-")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if(prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    textViewInput?.text = removeZeroAfterDot((firstValue.toDouble() - secondValue.toDouble()).toString())

                }

                if(textVievValue.contains("+")){
                    val splitValue = textVievValue.split("+")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if(prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    textViewInput?.text = removeZeroAfterDot((firstValue.toDouble() + secondValue.toDouble()).toString())

                }

                if(textVievValue.contains("*")){
                    val splitValue = textVievValue.split("*")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if(prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    textViewInput?.text = removeZeroAfterDot((firstValue.toDouble() * secondValue.toDouble()).toString())

                }

                if(textVievValue.contains("/")){
                    val splitValue = textVievValue.split("/")
                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if(prefix.isNotEmpty()){
                        firstValue = prefix + firstValue
                    }

                    textViewInput?.text = removeZeroAfterDot((firstValue.toDouble() / secondValue.toDouble()).toString())

                }


            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String{

        var value = result

        if(result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }

        return value

    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/" )
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}