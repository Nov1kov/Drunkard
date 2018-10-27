var playersCount = 3
val players = mutableListOf<Player>()

fun main(args: Array<String>) {
    if (args.isNotEmpty()){
        playersCount = args[0].toInt()
    }
    setup()
    game()
}

private fun setup() {
    val cards = mutableListOf<Card>()

    for (suit in Suit.values()) {
        for (value in Value.values()) {
            cards.add(Card(suit, value))
        }
    }

    for (i in 1..playersCount) {
        players.add(Player("$i"))
    }

    cards.shuffle()

    var i = 0
    cards.dropLastWhile {
        players[i % playersCount].cards.push(it)
        i += 1
        true
    }
}

fun game() {
    var step = 1
    while (players.size > 1) {

        println("game step $step, players ${players.size}")
        for (player in players) {
            println("$player has ${player.cards.size} cards")
        }

        step()
        checkPlayersLose()
        step += 1
    }
}

fun step() {

    val gameStep = GameStep()

    var disputes = gameStep.getDisputes()
    while (disputes.isNotEmpty() && disputes.all { it.canResolve() }) {
        println("disputes $disputes")
        gameStep.putCardForDisputes(disputes)
        gameStep.putCardForDisputes(disputes)
        disputes = gameStep.getDisputes()
    }

    var maxCard = Card(Suit.Spades, Value.Two)
    val cardsSet = gameStep.getTopCardsMap()
    var winerIndex = 0

    for ((i, card) in cardsSet) if (card.value > maxCard.value) {
        maxCard = card
        winerIndex = i
    }
    players[winerIndex].winStep(gameStep.getAllCards())
}

fun checkPlayersLose() {
    players.removeAll { !it.canPlay() }
}