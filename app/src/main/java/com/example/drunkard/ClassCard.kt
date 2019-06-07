package com.example.drunkard

class Card(type : String, value : Int) {
    private var cardType : String = ""
    private var cardValue : Int = 0
    init {
        cardType = type
        cardValue = value
    }

    fun GetCardText() : String {
        return cardValue.toString() + " of " + cardType
    }

    fun GetCardValue() : Int {
        return cardValue
    }

    fun GetCardType() : String {
        return cardType
    }
}

fun isCardBigger(firstCard : Card, secondCard : Card) : Int {
    if (firstCard.GetCardValue() == secondCard.GetCardValue())
        return 0
    if (firstCard.GetCardValue() == 6 && secondCard.GetCardValue() == 14)
        return 1
    if (firstCard.GetCardValue() == 14 && secondCard.GetCardValue() == 6)
        return -1
    return firstCard.GetCardValue() - secondCard.GetCardValue()
}