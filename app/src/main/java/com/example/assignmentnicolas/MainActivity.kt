package com.example.assignmentnicolas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private var arrayI : MutableList<ImageView> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrayI.add(findViewById(R.id.diceOne))
        arrayI.add(findViewById(R.id.diceTwo))
        arrayI.add(findViewById(R.id.diceThree))
        arrayI.add(findViewById(R.id.diceFour))
    }
    //----------------------------------------------------------------------------------
    private fun showImageDice(roll :Int,imgV:ImageView){
        val drawableResource =
                when (roll) {
                    1 -> R.drawable.one
                    2 -> R.drawable.two
                    3 -> R.drawable.three
                    4 -> R.drawable.four
                    5 -> R.drawable.five
                    6 -> R.drawable.six
                    else -> R.drawable.dice
                    }

        imgV.setImageResource(drawableResource)
    }
    //----------------------------------------------------------------------------------
    private fun roll(): Int {
        return Random.nextInt(range = 1..6)
    }
    //----------------------------------------------------------------------------------
    private fun convertToInt(s: TextView): Int {
        val newInt: String = s.text.toString()
        return Integer.parseInt(newInt)
    }
    //----------------------------------------------------------------------------------
    private val numbers = arrayOf(0,0,0,0)
    private val results :MutableList<diceHistoryType> = ResultsList.results
    //----------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------
    fun btnRollNext(Sender : View){
        val diceNumInt =convertToInt(findViewById(R.id.diceNumber))

        for(i in 0 until diceNumInt) {

            if (numbers[diceNumInt-1]!=0) {

                for (a in 0..3) {
                   numbers[a]=0
                   showImageDice( numbers[a],arrayI[a])
                }
            }
            if (numbers[i]==0){
                numbers[i]=roll()
                showImageDice(numbers[i],arrayI[i])
                break}
            }
            if (numbers[diceNumInt-1]!=0) {
                savingResults("Roll Next Dice",diceNumInt)
            }
        }

    //----------------------------------------------------------------------------------
    fun btnRollAllDices(Sender : View){
        val diceNumInt =convertToInt(findViewById(R.id.diceNumber))

        for(i in 0..3){
            numbers[i]=0
            showImageDice(numbers[i],arrayI[i])
        }
        for(i in 0 until diceNumInt){
            numbers[i]=roll()
            showImageDice(numbers[i],arrayI[i])
        }
        savingResults("Roll All Dices",diceNumInt)
    }
    //----------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------
    fun btnPlus(Sender: View){
        val diceNum : TextView = findViewById(R.id.diceNumber)
        var oldNumInt =convertToInt(diceNum)
            oldNumInt++
                    val buttonP = findViewById<Button>(R.id.buttonPlus)
                    if(oldNumInt==4) buttonP.isEnabled = false
                    val buttonM = findViewById<Button>(R.id.buttonMinus)
                    if(oldNumInt>1) buttonM.isEnabled = true

                    for(i in 0..3){
                        arrayI[i].isVisible = i<oldNumInt
                    }
        diceNum.text = oldNumInt.toString()

    }
    //----------------------------------------------------------------------------------
    fun btnMinus(Sender: View){
        val diceNum : TextView = findViewById(R.id.diceNumber)
        var oldNumInt =convertToInt(diceNum)
            oldNumInt--
                    val buttonM = findViewById<Button>(R.id.buttonMinus)
                    if(oldNumInt==1) buttonM.isEnabled = false
                    val buttonP = findViewById<Button>(R.id.buttonPlus)
                    if(oldNumInt<4) buttonP.isEnabled = true

                    for(i in 0..3){
                        arrayI[i].isVisible = i<oldNumInt
                    }
        diceNum.text = oldNumInt.toString()
    }
    //----------------------------------------------------------------------------------
    private fun savingResults(type:String,num:Int)
    {
        val temp = mutableListOf<Int>()

        for (n in numbers) {
            temp.add(n)
        }
        results.add(diceHistoryType(temp,type,num))

    }
    //----------------------------------------------------------------------------------
    fun displayHistoryList(sender: View)
    {
        val intent = Intent(this, history::class.java)

        startActivity(intent)

    }


}