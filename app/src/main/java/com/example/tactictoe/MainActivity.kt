package com.example.tactictoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var pLAYER=true
    private var tURNCOUNT=0

    private var boardStatus =Array(3){ IntArray(3)}
    private lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for(i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()
        resetBtn.setOnClickListener {
            updateDisplay("Player X Turn")
                pLAYER=true
                tURNCOUNT=0
                initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] =-1
            }
        }
        for(i in board) {
            for (button in i) {
                button.isEnabled = true
                button.text = ""
            }
        }
    }
    override fun onClick(view:View){
        when(view.id){
            R.id.button -> {
                updateValue(row=0,col=0,player=pLAYER)
            }
            R.id.button2 -> {
            updateValue(row=0,col=1,player=pLAYER)

            }
            R.id.button3 -> {
            updateValue(row=0,col=2,player=pLAYER)

            }
            R.id.button4 -> {
            updateValue(row=1,col=0,player=pLAYER)
            }
            R.id.button5 -> {
            updateValue(row=1,col=1,player=pLAYER)
            }
            R.id.button6 -> {
            updateValue(row=1,col=2,player=pLAYER)
            }
            R.id.button7 -> {
            updateValue(row=2,col=0,player=pLAYER)
            }
            R.id.button8 -> {
            updateValue(row=2,col=1,player=pLAYER)
            }
            R.id.button9 -> {
            updateValue(row=2,col=2,player=pLAYER)
            }
        }
        tURNCOUNT++
        pLAYER = !pLAYER
        if(pLAYER){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player 0 Turn")
        }
        if(tURNCOUNT==9)
        {
            updateDisplay("Game Draw")
        }
        checkWinner()
    }
    private fun checkWinner(){
        for(i in 0..2)
        {
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
                if(boardStatus[i][0]==1)
                {
                    updateDisplay("Player X Wins")
                    break
                }
                else if(boardStatus[i][0]==0)
                {
                    updateDisplay("Player 0 Wins")
                    break
                }
            }
        }
        for(i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    updateDisplay("Player X Wins")
                    break
                } else if (boardStatus[0][i] == 0) {
                    updateDisplay("Player 0 Wins")
                    break
                }
            }
        }
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                updateDisplay("Player X Wins")
            } else if (boardStatus[0][0] == 0) {
                updateDisplay("Player 0 Wins")
            }
        }
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player X Wins")
            } else if (boardStatus[0][2] == 0) {
                updateDisplay("Player 0 Wins")
            }
        }

    }

    private fun updateDisplay(text: String) {
        displayTv.text=text
        if(text.contains("Player X Wins"))
        {
            disableButton()
        }
        else  if(text.contains("Player 0 Wins"))
        {
            disableButton()
        }

    }
    private fun disableButton()
    {
        for(i in board) {
            for (button in i) {
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if (player) "X" else "0"
        val value = if (player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }
}