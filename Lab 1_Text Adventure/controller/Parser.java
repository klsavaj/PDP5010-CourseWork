//Note: I have been using VISUAL STUDIO CODE editor for my project!!
/* Name: Krunal Savaj
 * Text adventure game
 */

package controller;

import java.util.ArrayList;
import java.util.List;

/**
 * The Parser class is responsible for splitting user input into individual words.
 * This class manually processes a string to separate it into words for further command processing.
 */
public class Parser {

    /**
     * Method to manually split the input string into words.
     * It processes the input, removes any leading/trailing spaces, and returns an array of words.
     * 
     * @param input The string input provided by the user.
     * @return An array of words from the input.
     */
    public String[] parseInput(String input) {
        // Trim the input to remove leading and trailing spaces
        input = input.trim();

        // Create a dynamic list to store the words
        List<String> words = new ArrayList<>();

        /**
         * StringBuilder to accumulate characters for each word.
         * 
         * Reference:
         * https://www.geeksforgeeks.org/stringbuilder-class-in-java-with-examples/
         * Author: Rishabh Prabhu
         * 
         * Note: This StringBuilder represents the mutable sequence of characters. 
         * For single-threaded operations, it is safe to use. In multi-threading, 
         * StringBuffer is recommended.
         */
        StringBuilder word = new StringBuilder();

        // Loop through each character in the input string
        for (int i = 0; i < input.length(); i++) {
            char curr_Char = input.charAt(i);

            // If the current character is not a space, add it to the current word
            if (curr_Char != ' ') {
                word.append(curr_Char);
            } else {
                // If a space is encountered and the current word has characters, add it to the list
                if (word.length() > 0) {
                    words.add(word.toString());
                    word.setLength(0); // Reset the StringBuilder for the next word
                }
            }
        }

        // Add the last word if it's still in the StringBuilder after the loop
        if (word.length() > 0) {
            words.add(word.toString());
        }

        // Convert the list to an array and return it
        return words.toArray(new String[0]);
    }
}
