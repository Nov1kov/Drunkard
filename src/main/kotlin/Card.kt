data class Card(val suit: Suit, val value: Value)

enum class Suit {
    Spades,
    Hearts,
    Clubs,
    Diamonds,
}

enum class Value {
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Jack,
    Queen,
    King,
    Ace
}