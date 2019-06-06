package com.example.drunkard

class Drunkgame() {
    private var yourDeck : Deck = Deck()
    private var enemyDeck : Deck = Deck()
    var yourCard = Card("", 0)
    var enemyCard = Card("", 0)
    init {
        for (i in 6..14) {
            yourDeck.AddCard(Card("a", i))
            yourDeck.AddCard(Card("b", 20 - i))
        }
        for (i in 6..14) {
            enemyDeck.AddCard(Card("c", 20 - i))
            enemyDeck.AddCard(Card("d", i))
        }
    }

    fun StartTurn() {
        yourCard = yourDeck.GetFirstCard()
        enemyCard = enemyDeck.GetFirstCard()
    }

    fun EndTurn() : Boolean {
        if (yourCard.GetCardValue() >= enemyCard.GetCardValue()) {
            yourDeck.AddCard(yourCard)
            yourDeck.AddCard(enemyCard)
        }
        if (yourCard.GetCardValue() < enemyCard.GetCardValue()) {
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