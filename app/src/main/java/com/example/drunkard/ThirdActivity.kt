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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        var game = Drunkgame()
        yourDeck.text = "Ваша колода: " + game.GetYourDeckSize().toString()
        enemyDeck.text = "Колода противника: " + game.GetEnemyDeckSize().toString()
        fun OnClickStartTurn(view: View) {
            game.StartTurn()
            yourCard.text = "Ваша карта: - " + game.yourCard.GetCardText()
            Log.d("Check", game.yourCard.GetCardText())
            Log.d("Check", yourCard.text.toString())
            enemyCard.text = "Карта противника: - " + game.enemyCard.GetCardText()
            yourDeck.text = "Ваша колода: " + game.GetYourDeckSize().toString()
            enemyDeck.text = "Колода противника: " + game.GetEnemyDeckSize().toString()


            Thread.sleep(500)
            game.EndTurn()
            yourCard.text = "Ваша карта: -"
            enemyCard.text = "Карта противника: -"
            yourDeck.text = "Ваша колода: " + game.GetYourDeckSize().toString()
            enemyDeck.text = "Колода противника: " + game.GetEnemyDeckSize().toString()

        }
        button_start_turn.setOnClickListener(::OnClickStartTurn)
    }
    fun movemenu(view: View){
        val moveIntent = Intent (this, MainActivity::class.java)
        startActivity(moveIntent)
        val toast = Toast.makeText(this,"Игра окончена",Toast.LENGTH_SHORT)
        toast.show()
    }
}