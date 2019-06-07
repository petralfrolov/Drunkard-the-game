package com.example.drunkard

import android.util.Log
import java.lang.Math.abs
import java.util.*

class Drunkgame() {
    private var yourDeck : Deck = Deck()
    private var enemyDeck : Deck = Deck()
    var yourCard = Card("", 0)
    var enemyCard = Card("", 0)
    init {
        var Deck : Array<Card> = emptyArray()
        var used : Array<Boolean> = emptyArray()
        for (i in 6..14) {
            Deck = Deck.plus(Card("Spades", i))
            Deck = Deck.plus(Card("Clubs", i))
            Deck = Deck.plus(Card("Hearts", i))
            Deck = Deck.plus(Card("Diamonds", i))
            used = used.plus(false)
            used = used.plus(false)
            used = used.plus(false)
            used = used.plus(false)
        }
        while (yourDeck.GetDeckSize() < 18) {
            var i : Int = abs(Random().nextInt()) % 36
            Log.d("Check", i.toString())
            if (!used[i]) {
                used[i]= true
                yourDeck.AddCard(Deck[i])
            }
        }
        while (enemyDeck.GetDeckSize() < 18) {
            var i : Int = abs(Random().nextInt()) % 36
            if (!used[i]) {
                used[i]= true
                enemyDeck.AddCard(Deck[i])
            }
        }
    }

    fun StartTurn() {
        yourCard = yourDeck.GetFirstCard()
        enemyCard = enemyDeck.GetFirstCard()
    }

    fun EndTurn() : Boolean {
        if (isCardBigger(yourCard, enemyCard) > 0) {
            yourDeck.AddCard(yourCard)
            yourDeck.AddCard(enemyCard)
        }
        if (isCardBigger(yourCard, enemyCard) < 0) {
            enemyDeck.AddCard(yourCard)
            enemyDeck.AddCard(enemyCard)
        }
        if (isCardBigger(yourCard, enemyCard) == 0)
            //kappa
        if (yourDeck.GetDeckSize() <= 0 || enemyDeck.GetDeckSize() <= 0) {
            return false
        }
        return true
    }

    fun GetYourDeckSize() : Int {
        return yourDeck.GetDeckSize()
    }

    fun GetEnemyDeckSize() : Int {
        return enemyDeck.GetDeckSize()
    }
}