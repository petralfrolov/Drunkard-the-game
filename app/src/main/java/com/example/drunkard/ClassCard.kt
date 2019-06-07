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