//Note: I have been using VISUAL STUDIO CODE editor for my project!!
/* Name: Krunal Savaj
 * Text adventure game
 */

package main;

import controller.GameController;
import controller.Parser;
import model.GameModel;
import view.GameView;

/**
 * The MainApp class is the entry point for the Text Adventure Game.
 * It initializes the game components including the view, model, 
 * parser, and controller, and starts the game.
 */
public class MainApp {
    public static void main(String[] args) {
        GameView view = new GameView();  // Initialize the game view
        GameModel model = new GameModel();  // Initialize the game model
        Parser parser = new Parser();  // Initialize the input parser
        GameController controller = new GameController(model, view, parser);  // Initialize the game controller

        /**
         * Calls the startGame method in the controller to begin the text adventure game.
         */
        controller.startGame();
    }
}