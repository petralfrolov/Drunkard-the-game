package com.example.drunkard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.drunkard.R
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
    var game = Drunkgame()
    fun OnClickStartTurn(view: View) {
        /*
        Log.d("Check", game.yourCard.GetCardText())
        Log.d("Check", yourCard.text.toString())
        */



        //Thread.sleep(100)
        if (!game.Turn()) {
            if (game.GetYourDeckSize() <= 0) {
                //здесь действия в случае победы
            }
            else {
                //здесь действия в случае поражения
            }
        }
        yourCard.text = "Your card: " + game.yourCard.GetCardText()
        yourDeck.text = "Your deck: " + game.GetYourDeckSize().toString()
        enemyCard.text = "Enemy's card: " + game.enemyCard.GetCardText()
        enemyDeck.text = "Enemy's deck: " + game.GetEnemyDeckSize().toString()
        /*
        yourCard.text = "Ваша карта: -"
        enemyCard.text = "Карта противника: -"
        yourDeck.text = "Ваша колода: " + game.GetYourDeckSize().toString()
        enemyDeck.text = "Колода противника: " + game.GetEnemyDeckSize().toString()
        */

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        yourDeck.text = "Your deck: " + game.GetYourDeckSize().toString()
        enemyDeck.text = "Enemy's deck: " + game.GetEnemyDeckSize().toString()

        button_start_turn.setOnClickListener(::OnClickStartTurn)
    }

    fun movemenu(view: View){
        val moveIntent = Intent (this, MainActivity::class.java)
        startActivity(moveIntent)
        val toast = Toast.makeText(this,"Game over\ngit gud",Toast.LENGTH_SHORT)
        toast.show()
    }
}
