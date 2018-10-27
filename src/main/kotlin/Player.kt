import utils.Stack

class Player(val name: String) {
    val cards: Stack<Card> = Stack<Card>()

    fun canPlay(): Boolean {
        val result = cards.size > 0
        if (!result) {
            println("$this can't play more")
        }
        return result
    }

    fun winStep(wonableCards: List<Card>) {
        println("$this won ${wonableCards.size} cards")
        cards.elements.addAll(wonableCards)
    }

    override fun toString(): String {
        return "player $name"
    }
}