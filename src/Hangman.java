import java.io.IOException; // To handle errors when reading files
import java.nio.file.Files; 
import java.nio.file.Path;  
import java.util.List;      
import java.util.Random;    

public class Hangman {  // This is the main class for the Hangman game
    public static void main(String[] args) throws IOException {
        Player player = new Player();  // Create a new player
        Answer wordToGuess = pickWord(player);  // Choose a random word for the player to guess
        Answer currentAnswer = start(player, wordToGuess);  // Show a hint for the word
        String message = run(player, wordToGuess, currentAnswer);  // Start the game loop and get the final message
        System.out.println(message);  // Print the result of the game
    }

    // This method runs the game
    private static String run(Player player, Answer wordToGuess, Answer currentAnswer) {
        // Keep playing until the player guesses the word
        while (!currentAnswer.equals(wordToGuess)) {
            String guess = player.getGuess();  // Get the player's letter guess
            if (player.wantsToQuit()) {  // Check if the player wants to quit
                return "Bye!";  // End the game if they quit
            }

            char guessedLetter = guess.charAt(0);  // Take the first letter from the guess
            if (currentAnswer.isGoodGuess(wordToGuess, guessedLetter)) {  // Check if the guess is correct
                // Update the hint to show the correct letter
                currentAnswer = wordToGuess.getHint(currentAnswer, guessedLetter);  
                System.out.println(currentAnswer);  // Show the updated hint
            } else {
                player.lostChance();  // Reduce the player's chances for a wrong guess
                displayHangman(player.getChances());  // Show the hangman based on remaining chances
                System.out.println("Wrong! Number of guesses left: " + player.getChances());  // Tell the player how many chances are left
                if (player.hasNoChances()) {  // If the player has no chances left
                    return "Sorry, you are out of guesses. The word was: " + wordToGuess;  // End the game and show the word
                }
            }
        }
        return "That is correct! You escaped the noose .. this time.";  // Player guessed the word correctly
    }

    // This method starts the game by giving a random hint
    private static Answer start(Player player, Answer wordToGuess) {
        Answer currentAnswer = wordToGuess.generateRandomHint();  // Create a hint for the word
        System.out.println("Guess the word: " + currentAnswer);  // Show the hint to the player
        return currentAnswer;  // Return the current hint
    }

    // This method picks a random word from a file
    private static Answer pickWord(Player player) throws IOException {
        Random random = new Random();  // Create a random number generator

        System.out.println("Words file? [leave empty to use short_words.txt]");  // Ask for a words file
        String fileName = player.getWordsFile();  // Get the file name from the player

        List<String> words = Files.readAllLines(Path.of(fileName));  // Read all words from the file

        int randomIndex = random.nextInt(words.size());  // Pick a random index
        String randomWord = words.get(randomIndex).trim();  // Get the random word from the list
        return new Answer(randomWord);  // Return the chosen word as an Answer object
    }

    // This method shows the Hangman drawing based on remaining chances
    private static void displayHangman(int chances) {
        String[] hangmanStages = {
            // Hangman stages for each number of chances left
            "------\n|    |\n|\n|\n|\n|\n|_____\n",                // 6 chances left
            "------\n|    |\n|    O\n|\n|\n|\n|_____\n",            // 5 chances left
            "------\n|    |\n|    O\n|    |\n|\n|\n|_____\n",      // 4 chances left
            "------\n|    |\n|    O\n|   /|\\\n|\n|\n|_____\n",    // 3 chances left
            "------\n|    |\n|    O\n|   /|\\\n|   / \n|\n|_____\n", // 2 chances left
            "------\n|    |\n|    O\n|   /|\\\n|   / \\ \n|\n|_____\n" // 1 chance left
        };

        int index = 6 - chances;  // Determine which hangman stage to show
        if (index >= 0 && index < hangmanStages.length) {  // Check if the index is valid
            System.out.println(hangmanStages[index]);  // Show the current hangman stage
        } else {
            System.out.println("Invalid number of chances.");  // Error message for invalid chances
        }
    }
}
