import utils.Stack

class GameStep() {

    private val cardsSet = mutableListOf<GamePlayerStack>()

    init {
        for (player in players) {
            val stack = GamePlayerStack(player)
            stack.pushSelfCard()
            cardsSet.add(stack)
        }
    }

    fun getTopCardsMap(): Map<Int, Card> {
        val map = mutableMapOf<Int, Card>()
        for ((i, stack) in cardsSet.withIndex()) {
            map.put(i, stack.peek())
        }
        return map
    }

    fun getTopCards(): List<Card> {
        val list = mutableListOf<Card>()
        for (stack in cardsSet) {
            list.add(stack.peek())
        }
        return list
    }

    fun getAllCards(): List<Card> {
        val list = mutableListOf<Card>()
        for (stack in cardsSet) {
            list.addAll(stack.elements)
        }
        return list
    }

    fun getDisputes(): List<Dispute> {
        val topCards = getTopCards()
        val groups = topCards.groupBy { it.value }.filter { it.value.size > 1 }

        val disputes = mutableListOf<Dispute>()
        for (v in groups.values) {
            val dispute = Dispute()
            for ((playerIndex, card) in topCards.withIndex()) {
                if (card.value == v[0].value) {
                    dispute.add(playerIndex, card)
                }
            }
            disputes.add(dispute)
        }
        return disputes
    }

    fun putCardForDisputes(disputes: List<Dispute>) {
        for (dispute in disputes) {
            for (playerIndex in dispute.cards.keys) {
                if (players[playerIndex].canPlay()) {
                    cardsSet[playerIndex].pushSelfCard()
                }
            }
        }
    }

    class GamePlayerStack(val player: Player) : Stack<Card>() {
        fun pushSelfCard() {
            val card = player.cards.pop()
            println("$player put $card")
            push(card)
        }
    }

    class Dispute {
        val cards = mutableMapOf<Int, Card>()

        fun add(index: Int, card: Card) {
            cards.put(index, card)
        }

        fun canResolve(): Boolean {
            for (playerIndex in cards.keys) {
                if (players[playerIndex].canPlay()) {
                    return true
                }
            }
            println("dispute can't resolved")
            return false
        }

        override fun toString(): String {
            return cards.toString()
        }
    }
}