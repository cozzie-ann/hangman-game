
# Hangman Game

A classic Hangman game implemented in Java. The player attempts to guess a hidden word by suggesting letters within a certain number of guesses. The game includes a visual stickman representation to show the current state of guesses and remaining attempts.

## Features

- **Interactive Gameplay:** Play a traditional Hangman game where you guess letters to reveal a hidden word.
- **Stickman Representation:** Visual feedback showing the stickman's state as the player makes incorrect guesses.
- **File-Based Word Selection:** Load words from a file.

- **User Input Handling:** Accept guesses from the user and handle game state updates accordingly.


### Installation

1. **Clone the Repository**

   ```
   git clone https://github.com/cozzie-ann/hangman-game.git
   ```

2. **Navigate to the Project Directory**

   ```
   cd hangman-game
   ```

3. **Compile the Source Code**

   Compile the Java files into the `bin` directory:

   ```bash
   javac -d bin src/*.java
   ```

4. **Run the Game**

   Run the Hangman game:

   ```
   java -cp bin Hangman
   ```

### Usage

- **Start the Game:** Run the `Hangman` class to start the game. The game will prompt you for input.
- **Guess Letters:** Type your guesses and hit Enter. The stickman will update based on your guesses.
- **Quit the Game:** Type `quit` or `exit` to end the game early.

### Files

- **`src/Answer.java`**: Handles the game's answer logic and hint generation.
- **`src/Hangman.java`**: Main class to run the game.
- **`src/Player.java`**: Manages player input and game state.
- **`short_words.txt`**: Default file containing a list of words for the game.

### Example

```
Words file? [leave empty to use short_words.txt]
feed
Guess the word: _ _ _ _
e
Guess the word: _ e _ _
f
Guess the word: f e _ f
```


