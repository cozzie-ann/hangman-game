import java.io.InputStream; // To use different input sources (like keyboard or file)
import java.util.Scanner;   

public class Player {  // This class represents the player in the game
    private int chances = 5;  // Number of chances the player has
    private boolean quit = false;  // Flag to check if the player wants to quit
    
    private final Scanner scanner;  // Scanner to read user input

    // Constructor that takes an InputStream for flexibility
    public Player(InputStream in) {
        this.scanner = new Scanner(in);  // Initialize the scanner with the provided input
    }

    // Default constructor that uses standard input (keyboard)
    public Player() {
        this(System.in);  // Call the other constructor with standard input
    }

    // This method asks for the file name of words and returns it
    public String getWordsFile() {
        String filename = scanner.nextLine();  // Read the input line from the user
        // If the input is blank, return the default file name
        return filename.isBlank() ? "short_words.txt" : filename;
    }

    // This method returns the number of chances the player has
    public int getChances() {
        return chances;  // Return the current chances
    }

    // This method decreases the chances if the player has not run out
    public void lostChance() {
        if (!this.hasNoChances()) {  // Check if the player has chances left
            this.chances--;  // Reduce the chances by one
        }
    }

    // This method checks if the player has no chances left
    public boolean hasNoChances() {
        return this.getChances() == 0;  // Return true if chances are zero
    }

    // This method gets the player's guess and checks if they want to quit
    public String getGuess() {
        String text = scanner.nextLine();  // Read the guess from the user
        // Check if the input is "quit" or "exit" to set the quit flag
        this.quit = text.equalsIgnoreCase("quit") || text.equalsIgnoreCase("exit");
        return text;  // Return the guess
    }

    // This method returns true if the player wants to quit
    public boolean wantsToQuit() {
        return this.quit;  // Return the quit status
    }
}
