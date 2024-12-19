//Note: I have been using VISUAL STUDIO CODE editor for my project!!
/* Name: Krunal Savaj
 * Text adventure game
 */

 package view;
 /**
  * The GameView class is responsible for handling the display
  * of game output to the player. It provides methods to display 
  * instructions and outputs during gameplay.
  */
 public class GameView {
 
     /**
      * Displays a string message to the console output.
      * 
      * @param output The string message to be displayed.
      */
     public void displayOutput(String output) {
         System.out.println(output);
     }
 
     /**
      * Displays the initial game instructions to guide the player
      * on how to interact with the game.
      */
     public void displayStartInstructions() {
         displayOutput("Ready to go restuarant??\n" +
                 "Instructions:\n" +
                 "Type {verb} {noun} to perform an action on an object.\n" +
                 "you are leaving the house and perform \n\n" +
                 "Actions: 'walk', 'pick', 'wear', 'close'. \n" +
                 "Objects: 'floor', 'keys', 'shoes', 'door'. \n\n" +
                 "--Type {take} {uber_car} to go to the uber. \n");
     }
 }
 