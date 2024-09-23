import java.util.Random; 

public class Answer { // This class represents an answer
    private String value; // A private string to hold the answer

    // Constructor to set the answer's value
    public Answer(String value) {
        this.value = value; // Store the provided answer value
    }

    // This method returns the answer as a string
    @Override
    public String toString() {
        return this.value; // Return the stored answer
    }

    // This method checks if two Answer objects are equal
    @Override
    public boolean equals(Object obj) {
        // Check if the given object is also an Answer
        if (obj instanceof Answer) {
            Answer otherAnswer = (Answer) obj; // Convert the object to Answer
            // Compare the values of both answers
            return this.value.equalsIgnoreCase(otherAnswer.toString());
        }
        return false; // Return false if it's not an Answer
    }

    // This method provides a hint based on the guessed answer and letter
    public Answer getHint(Answer guessedAnswer, char guessedLetter) {
        StringBuilder result = new StringBuilder(); // Prepare to build the hint

        // Go through each character in the actual answer
        for (int i = 0; i < this.value.length(); i++) {
            // If the guessed letter matches a character in the answer
            if (guessedLetter == this.value.charAt(i)) {
                result.append(guessedLetter); // Add the correct guessed letter to the hint
            } else {
                // Otherwise, add the corresponding character from the guessed answer
                result.append(guessedAnswer.toString().charAt(i));
            }
        }
        // Return a new Answer object with the generated hint
        return new Answer(result.toString());
    }

    // This method checks if a specific letter is in the answer
    public boolean hasLetter(char letter) {
        // Check if the letter is found in the answer and return true or false
        return this.value.indexOf(letter) >= 0;
    }

    // This method generates a random hint from the answer
    public Answer generateRandomHint() {
        Random random = new Random(); // Create a Random object
        // Get a random index within the answer's length
        int randomIndex = random.nextInt(this.value.length());

        // Create a string of underscores to represent hidden letters
        String noLetters = "_".repeat(this.value.length());
        // Use the random letter from the answer to create a hint
        return this.getHint(new Answer(noLetters), this.value.charAt(randomIndex));
    }

    // This method checks if a guess is a good one
    public boolean isGoodGuess(Answer wordToGuess, char letter) {
        // Return true if the letter is in the word to guess and not already guessed
        return wordToGuess.hasLetter(letter) && !this.hasLetter(letter);
    }
}
