package com.example.drunkard

class Card(type : String, value : Int, name : String) {
    private var cardType : String = ""
    private var cardValue : Int = 0
    private var cardName : String = ""
    init {
        cardType = type
        cardValue = value
        cardName = name
    }

    fun GetCardText() : String {
        return cardName + " of " + cardType
    }

    fun GetCardValue() : Int {
        return cardValue
    }

    fun GetCardName() : String {
        return cardName
    }

    fun GetCardType() : String {
        return cardType
    }

}

fun isCardBigger(firstCard : Card, secondCard : Card) : Int {
    if (firstCard.GetCardValue() == 6 && secondCard.GetCardValue() == 14)
        return 1
    if (firstCard.GetCardValue() == 14 && secondCard.GetCardValue() == 6)
        return -1
    return firstCard.GetCardValue() - secondCard.GetCardValue()
}