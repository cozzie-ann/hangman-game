import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class Hangman {
    public static void main(String[] args) throws IOException {
        Player player = new Player();
        Answer wordToGuess = pickWord(player);
        Answer currentAnswer = start(player, wordToGuess);
        String message = run(player, wordToGuess, currentAnswer);
        System.out.println(message);
    }

    private static String run(Player player, Answer wordToGuess, Answer currentAnswer) {
        while (!currentAnswer.equals(wordToGuess)) {
            String guess = player.getGuess();
            if (player.wantsToQuit()) {
                return "Bye!";
            }

            char guessedLetter = guess.charAt(0);
            if (currentAnswer.isGoodGuess(wordToGuess, guessedLetter)) {
                currentAnswer = wordToGuess.getHint(currentAnswer, guessedLetter);
                System.out.println(currentAnswer);
            } else {
                player.lostChance();
                displayHangman(player.getChances()); // Show the hangman at each incorrect guess
                System.out.println("Wrong! Number of guesses left: " + player.getChances());
                if (player.hasNoChances()) {
                    return "Sorry, you are out of guesses. The word was: " + wordToGuess;
                }
            }
        }
        return "That is correct! You escaped the noose .. this time.";
    }

    private static Answer start(Player player, Answer wordToGuess) {
        Answer currentAnswer = wordToGuess.generateRandomHint();
        System.out.println("Guess the word: " + currentAnswer);
        return currentAnswer;
    }

    private static Answer pickWord(Player player) throws IOException {
        Random random = new Random();

        System.out.println("Words file? [leave empty to use short_words.txt]");
        String fileName = player.getWordsFile();

        List<String> words = Files.readAllLines(Path.of(fileName));

        int randomIndex = random.nextInt(words.size());
        String randomWord = words.get(randomIndex).trim();
        return new Answer(randomWord);
    }

    private static void displayHangman(int chances) {
        String[] hangmanStages = {
            "------\n|    |\n|\n|\n|\n|\n|_____\n",                // 6 chances left
            "------\n|    |\n|    O\n|\n|\n|\n|_____\n",            // 5 chances left
            "------\n|    |\n|    O\n|    |\n|\n|\n|_____\n",      // 4 chances left
            "------\n|    |\n|    O\n|   /|\\\n|\n|\n|_____\n",    // 3 chances left
            "------\n|    |\n|    O\n|   /|\\\n|   / \n|\n|_____\n", // 2 chances left
            "------\n|    |\n|    O\n|   /|\\\n|   / \\ \n|\n|_____\n" // 1 chance left
        };

        int index = 6 - chances;
        if (index >= 0 && index < hangmanStages.length) {
            System.out.println(hangmanStages[index]);
        } else {
            System.out.println("Invalid number of chances.");
        }
    }
}
