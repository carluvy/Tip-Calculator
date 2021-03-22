package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.math.round
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {
    private lateinit var userInput: EditText
    private lateinit var tipPercentage: Slider
    private lateinit var billValue: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userInput = edit_text
        tipPercentage = slider
        billValue = text_view
        //var amount: Double


        userInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//               if (s.isNullOrEmpty()) {
//                   //billValue.visibility = View.GONE
//                   tipPercentage.value = 0F
//               } else {
//                   //billValue.visibility = View.VISIBLE
//
//               }
            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                   billValue.visibility = View.VISIBLE
                    val amount = userInput.text.toString().toDouble()
                    val tipAmount = amount * tipPercentage.value / 100
                    val decimal: BigDecimal = BigDecimal(tipAmount).setScale(2, RoundingMode.HALF_EVEN)

                    //billValue.text = "Bill value: ${userInput.text}, tip percentage: ${tipPercentage.value.toInt()}%"
                    val text = "Tip amount: $decimal"
                    billValue.text = text
                } else {
                    billValue.visibility = View.GONE
                    tipPercentage.value = 0F
                }

            }

        })

        tipPercentage.addOnSliderTouchListener(object: Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                if (!TextUtils.isEmpty(userInput.text)) {
                    billValue.visibility = View.VISIBLE
                    slider.value = 0F


                } else {
                    billValue.visibility = View.GONE
                }
            }

            override fun onStopTrackingTouch(slider: Slider) {


            }


        })
        tipPercentage.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            if (!TextUtils.isEmpty(userInput.text)) {
                val amount = userInput.text.toString().toDouble()
                val tipAmount = amount * tipPercentage.value / 100
                val decimal: BigDecimal = BigDecimal(tipAmount).setScale(2, RoundingMode.HALF_EVEN)

                //billValue.text = "Bill value: ${userInput.text}, tip percentage: ${tipPercentage.value.toInt()}%"
                val text = "Tip amount: $decimal"
                billValue.text = text
            } else {
                billValue.visibility = View.GONE

            }

        })


    }
}