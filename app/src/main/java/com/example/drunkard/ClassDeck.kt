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
        var result = deck[size - 1]
        deck = deck.sliceArray(0 until size - 1)
        size--
        return result
    }

    fun GetFirstCard() : Card {
        var result = deck[0]
        deck = deck.sliceArray(1 until size)
        size--
        return result
    }
}

fun MergeDecks(firstDeck : Deck, secondDeck : Deck) : Deck {
    while (secondDeck.GetDeckSize() > 0)
        firstDeck.AddCard(secondDeck.GetFirstCard())
    return firstDeck
}