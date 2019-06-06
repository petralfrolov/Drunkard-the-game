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
        var deck : Array<Card> = emptyArray()
        var used : Array<Boolean> = emptyArray()
        for (i in 6..14) {
            deck = deck.plus(Card("a", i))
            deck = deck.plus(Card("b", i))
            deck = deck.plus(Card("c", i))
            deck = deck.plus(Card("d", i))
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
                yourDeck.AddCard(deck[i])
            }
        }
        while (enemyDeck.GetDeckSize() < 18) {
            var i : Int = abs(Random().nextInt()) % 36
            if (!used[i]) {
                used[i]= true
                enemyDeck.AddCard(deck[i])
            }
        }
    }

    fun StartTurn() {
        yourCard = yourDeck.GetFirstCard()
        enemyCard = enemyDeck.GetFirstCard()
    }

    fun EndTurn() : Boolean {
        if ((yourCard.GetCardValue() >= enemyCard.GetCardValue() && !(yourCard.GetCardValue() == 14 && enemyCard.GetCardValue() == 6)) || (yourCard.GetCardValue() == 6 && enemyCard.GetCardValue() == 14)) {
            yourDeck.AddCard(yourCard)
            yourDeck.AddCard(enemyCard)
        }
        if ((yourCard.GetCardValue() < enemyCard.GetCardValue() && !(yourCard.GetCardValue() == 6 && enemyCard.GetCardValue() == 14)) || (yourCard.GetCardValue() == 14 && enemyCard.GetCardValue() == 6)) {
            enemyDeck.AddCard(yourCard)
            enemyDeck.AddCard(enemyCard)
        }
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