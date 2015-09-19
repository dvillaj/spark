val STARTING_FUND = 10
val STAKE = 1   // the amount of the bet
val NUMBER_OF_GAMES = 25

def rollDie: Int = {
    val r = scala.util.Random
    r.nextInt(99) + 1
}

def playGame(stake: Int): (Int) = {
    val faceValue = rollDie
    if (faceValue < 50)
        (2*stake)
    else
        (0)
}

// Function to play the game multiple times
// Returns the final fund amount
def playSession(
   startingFund: Int = STARTING_FUND,
   stake: Int = STAKE,
   numberOfGames: Int = NUMBER_OF_GAMES):
   (Int) = {

    // Initialize values
    var (currentFund, currentStake, currentGame) = (startingFund, 0, 1)

    // Keep playing until number of games is reached or funds run out
    while (currentGame <= numberOfGames && currentFund > 0) {

        // Set the current bet and deduct it from the fund
        currentStake = math.min(stake, currentFund)
        currentFund -= currentStake

        // Play the game
        val (winnings) = playGame(currentStake)

        // Add any winnings
        currentFund += winnings

        // Increment the loop counter
        currentGame += 1
    }
    (currentFund)
}
