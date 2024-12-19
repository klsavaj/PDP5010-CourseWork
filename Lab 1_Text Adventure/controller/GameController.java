//Note: I have been using VISUAL STUDIO CODE editor for my project!!
/* Name: Krunal Savaj
 * Text adventure game
 */

package controller;

import model.GameModel;
import view.GameView;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The GameController class serves as the controller in the MVC architecture. 
 * It coordinates interactions between the GameModel (business logic) and GameView (user interface).
 */
public class GameController {
    private GameModel model; // The model object that handles game logic
    private GameView view;   // The view object responsible for displaying outputs
    private Parser parser;   // The parser object for processing user input

    /**
     * Constructor to initialize the GameController.
     * 
     * @param model The game model that contains the game logic
     * @param view The game view that handles user interface and output
     * @param parser The parser used to interpret the user's input commands
     */
    public GameController(GameModel model, GameView view, Parser parser) {
        this.model = model;
        this.view = view;
        this.parser = new Parser(); // Initialize the parser
    }

    /**
     * Processes the user's input command. This method ensures that the input is in the 
     * "verb noun" format and executes the corresponding command in the model.
     * 
     * @param userInput The input string entered by the user
     */
    public void processInput(String userInput) {
        String[] words = parser.parseInput(userInput.toLowerCase());

        // Strictly enforce "verb noun" format
        if (words.length != 2) {
            view.displayOutput("Invalid command format! Please use: {verb} {noun}");
        } else {
            String verb = words[0];  // This will store the verb
            String noun = words[1];  // This will store the noun
            String output = model.executeComds(verb, noun);
            view.displayOutput(output);
        }
    }

    /**
     * Processes the bill by retrieving the items and their prices from the model, 
     * then displaying the total bill to the user.
     */
    public void processBill() {
        HashMap<String, Integer> bill = model.get_Bill();
        Integer total = model.totalOrderValue();
        view.displayOutput("----------------------------------------------------");
        view.displayOutput("Your bill for today is: ");
        for (String dish : bill.keySet()) {
            view.displayOutput(dish + " : " + bill.get(dish));
        }
        view.displayOutput("Total Amount: " + total + " USD. ");
        view.displayOutput("Thank you for visiting us!! See you soon!");
    }

    /**
     * Starts the game, continuously accepting and processing user input until the game 
     * is either exited or the user requests to process the bill.
     */
    public void startGame() {
        view.displayStartInstructions();  // Display initial game instructions
        boolean isRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (isRunning) {
                String userInput = scanner.nextLine();

                // Check if the user wants to exit or process the bill
                if ("exit".equalsIgnoreCase(userInput)) {
                    isRunning = false;
                    view.displayOutput("Thanks for playing! Goodbye.");
                } else if ("bill_please".equalsIgnoreCase(userInput)) {
                    processBill();
                    isRunning = false;
                } else {
                    processInput(userInput);  // Handle valid commands
                }
            }
        }
    }
}
