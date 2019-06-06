package com.example.drunkard

class Deck() {
    private var deck : Array<Card> = emptyArray()
    private var size : Int = 0

    fun GetDeckSize() : Int {
        return size
    }

    fun AddCard(card : Card) {
        deck = deck.plus(card)
        size++
    }

    fun GetLastCard() : Card {
        var newDeck : Array<Card> = emptyArray()
        for (i in 0 until size - 1) {
            newDeck = newDeck.plus(deck[i])
        }
        var result = deck[size - 1]
        deck = newDeck
        size--
        return result
    }

    fun GetFirstCard() : Card {
        var newDeck : Array<Card> = emptyArray()
        for (i in 1 until size) {
            newDeck = newDeck.plus(deck[i])
        }
        var result = deck[0]
        deck = newDeck
        size--
        return result
    }
}