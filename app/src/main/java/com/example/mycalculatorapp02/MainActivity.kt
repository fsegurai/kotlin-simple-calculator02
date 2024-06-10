package com.example.mycalculatorapp02

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycalculatorapp02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    private var canAddOperation = false;
    private var canAddDecimal = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * This function is called when a number button is clicked.
     * @param view - The view that was clicked.
     */
    fun numberAction(view: View) {
        if (view is Button) {
            if (view.text == ".") {
                if (canAddDecimal) {
                    binding.workingsTV.append(view.text);
                }
                canAddDecimal = false;
            } else {
                binding.workingsTV.append(view.text);
            }

            canAddOperation = true;
        }
    }

    /**
     * This function is called when an operator button is clicked.
     * @param view - The view that was clicked.
     */
    fun operatorAction(view: View) {
        if (view is Button && canAddOperation) {
            binding.workingsTV.append(view.text);
            canAddOperation = false;
            canAddDecimal = true;
        }
    }

    /**
     * This function is called when the all clear button is clicked.
     * @param view - The view that was clicked.
     */
    fun allClearAction(view: View) {
        binding.workingsTV.text = "";
        binding.resultsTV.text = "";
    }

    /**
     * This function is called when the clear button is clicked.
     * @param view - The view that was clicked.
     */
    fun backSpaceAction(view: View) {
        val length = binding.workingsTV.text.length;
        if (length > 0) {
            binding.workingsTV.text = binding.workingsTV.text.subSequence(0, length - 1);
        }
    }

    /**
     * This function is called when the equals button is clicked.
     * @param view - The view that was clicked.
     */
    fun equalsAction(view: View) {
        binding.resultsTV.text = calculateResults()
    }

    /**
     * This function calculates the result of the expression in the workings text view.
     */
    private fun calculateResults(): String {
        val digitsOperators = digitsOperators()
        if (digitsOperators.isEmpty()) return ""

        val timesDivision = timesDivisionCalculate(digitsOperators)
        if (timesDivision.isEmpty()) return ""

        val result = addSubtractCalculate(timesDivision)
        return result.toString()
    }

    /**
     * This function calculates the addition and subtraction of the expression.
     * It takes a list of numbers and operators and returns the result.
     * @param passedList - The list of numbers and operators.
     */
    private fun addSubtractCalculate(passedList: MutableList<Any>): Float {
        var result = passedList[0] as Float

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (operator == '+')
                    result += nextDigit
                if (operator == '-')
                    result -= nextDigit
            }
        }

        return result
    }

    /**
     * This function calculates the multiplication, division, and percentage of the expression.
     * It takes a list of numbers and operators and returns the result.
     * @param passedList - The list of numbers and operators.
     */
    private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains('x') || list.contains('/') || list.contains('%')) {
            list = calcTimesDiv(list)
        }
        return list
    }

    /**
     * This function calculates the multiplication, division, and percentage of the expression.
     * It takes a list of numbers and operators and returns the result.
     * @param passedList - The list of numbers and operators.
     */
    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float
                when (operator) {
                    'x' -> {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }

                    '/' -> {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }

                    '%' -> {
                        newList.add(prevDigit * nextDigit / 100)
                        restartIndex = i + 1
                    }

                    else -> {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if (i > restartIndex)
                newList.add(passedList[i])
        }

        return newList
    }

    /**
     * This function converts the workings text view into a list of numbers and operators.
     * It returns the list.
     */
    private fun digitsOperators(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for (character in binding.workingsTV.text) {
            if (character.isDigit() || character == '.')
                currentDigit += character
            else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(character)
            }
        }

        if (currentDigit != "")
            list.add(currentDigit.toFloat())

        return list
    }
}
