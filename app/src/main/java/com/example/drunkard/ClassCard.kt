package com.example.drunkard

class Card(type : String, value : Int) {
    private var cardType : String = ""
    private var cardValue : Int = 0
    init {
        cardType = type
        cardValue = value
    }

    fun GetCardText() : String {
        return cardType + cardValue.toString()
    }

    fun GetCardValue() : Int {
        return cardValue
    }

    fun GetCardType() : String {
        return cardType
    }
}