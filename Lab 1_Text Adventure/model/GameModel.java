//Note: I have been using VISUAL STUDIO CODE editor for my project!!
/* Name: Krunal Savaj
 * Text adventure game
 */

package model;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;


/**
 * The GameModel class represents the core game logic of the text adventure game.
 * It manages the current location, budget, and the player's actions such as 
 * interacting with the Uber and ordering food in a restaurant.
 */
public class GameModel {
    private String curr_place; // Stores the player's current location

    private int[] price = { 7, 12, 10 }; // Array storing the prices of the dishes
    private List<String> dishes = Arrays.asList("biryani", "butter_chicken", "paneer_tikka"); // List of available dishes
    private int curr_budget = 50; // The player's current budget
    private int sum = 0; // Total sum of the player's orders
    HashMap<String, Integer> order = new HashMap<String, Integer>(); // Tracks the player's orders and their prices
    private boolean hasEnteredCar = false; // Tracks if the player has entered the Uber car

    /**
     * Constructor that initializes the game model and sets the initial location to "start".
     */
    public GameModel() {
        this.curr_place = "start";
    }

    /**
     * Executes commands based on the player's current location.
     * 
     * @param verb The action verb entered by the player.
     * @param noun The object noun associated with the verb.
     * @return A string message describing the result of the command.
     */
    public String executeComds(String verb, String noun) {
        if ("start".equals(curr_place)) {
            return exeHomeComds(verb, noun);
        } else if ("uber".equals(curr_place)) {
            return exeUberComds(verb, noun);
        } else if ("restaurant".equals(curr_place)) {
            return exeRestaurantComds(verb, noun);
        }
        return "Invalid location.";
    }

    /**
     * Handles player commands while they are at home before taking the Uber.
     * 
     * @param verb The action verb entered by the player.
     * @param noun The object noun associated with the verb.
     * @return A string message describing the result of the command at home.
     */
    private String exeHomeComds(String verb, String noun) {
        if ("take".equals(verb) && "uber_car".equals(noun)) {
            curr_place = "uber";
            System.out.println("You walked to the uber car.\n" +
                 "----------------------------------------------------------------\n\n" +
                 "Things to do while taking Uber: \n" +
                 "Actions: 'enter', 'talk', 'listen' \n" +
                 "Objects: 'car', 'driver', 'music'. \n\n" +
                 "--Type {get_off} {uber} to get off the uber and enter Restaurant. \n");
            return "";

        } else if ("walk".equals(verb) && "floor".equals(noun)) {
            return "You are going towards the outside of the house, Dont forget to take keys with you!!";
        } else if ("pick".equals(verb) && "keys".equals(noun)) {
            return "You have taken the keys with you, have you worn shoes?";
        } else if ("wear".equals(verb) && "shoes".equals(noun)) {
            return "You have worn shoes, now close the door and take the Uber.";
        } else if ("close".equals(verb) && "door".equals(noun)) {
            return "You closed the door and are ready to take Uber.";
        } else {
            return "Invalid command at Home.";
        }
    }

    /**
     * Handles player commands while they are in the Uber car.
     * 
     * @param verb The action verb entered by the player.
     * @param noun The object noun associated with the verb.
     * @return A string message describing the result of the command in the Uber.
     */
    private String exeUberComds(String verb, String noun) {
        if ("get_off".equals(verb) && "uber".equals(noun)) {
            curr_place = "restaurant";
            System.out.println( "----------------------------------------------------------------\n\n" +
                 "You are in the Restaurant. \n" +
                 "Explore Restaurant: \n" +
                 "Actions: 'call', 'bring', 'note', 'order', 'pay'. \n" +
                 "Objects: 'waiter', 'menu', 'biryani', 'butter_chicken','paneer_tikka', 'dishes', 'money'. \n\n" +
                 "You have a 50$ credit limit and you can order following dishes: \n" +
                 "A. Biryani - 7$ \n" +
                 "B. Butter_Chicken - 12$ \n" +
                 "C. Paneer_Tikka - 10$ \n\n" +
                 "You can order the dishes by typing 'note' and the dish name.\n" +
                 "Print bill by typing 'bill_please'.\n" +
                 "Type 'exit' to exit the game. \n");
            return "";

        }
        
        if ("enter".equals(verb) && "car".equals(noun)) {

            if (hasEnteredCar) {
                return "You are already in the car!";
                
            }
            hasEnteredCar = true;
            return "You have entered the Uber car.";

        } else if ("talk".equals(verb) && "driver".equals(noun)) {
            return "You are having a conversation with the Uber driver!";
        } else if ("listen".equals(verb) && "music".equals(noun)) {
            return "Bluetooth is on, now you can listen to your favorite music.";
        } else {
            return "Invalid command on the UBER.";
        }
    }

    /**
     * Handles player commands while they are in the restaurant.
     * 
     * @param verb The action verb entered by the player.
     * @param noun The object noun associated with the verb.
     * @return A string message describing the result of the command in the restaurant.
     */
    private String exeRestaurantComds(String verb, String noun) {
        if ("note".equals(verb) && List.of("biryani", "butter_chicken", "paneer_tikka").contains(noun)) {
            int index = dishes.indexOf(noun);

            if (curr_budget - price[index] < 0) {
                return "You don't have enough balance to buy the " + noun + ".";
            } else {
                curr_budget = curr_budget - price[index];
                sum = sum + price[index];
                if (order.containsKey(noun)) {
                    order.put(noun, order.get(noun) + price[index]);
                } else {
                    order.put(noun, price[index]);
                }
                System.out.println("Current Budget Remaining:  " + curr_budget);
                return "Order for " + noun + " has been noted.";
            }

        } else if ("call".equals(verb) && "waiter".contains(noun)) {
            return "Waiter is at your service!! What can I help you with?";
        } else if ("bring".equals(verb) && "menu".equals(noun)) {
            return "Here is your " + noun + "!!  Please select your dishes!!";
        } else if ("order".equals(verb) && "dishes".contains(noun)) {
            return "You have successfully ordered your meal.";
        } else if ("pay".equals(verb) && "money".equals(noun)) {
            return "You paid for your meal.";
        } else {
            return "Invalid command in the restaurant.";
        }
    }

    /**
     * Retrieves the player's current bill.
     * 
     * @return A HashMap containing the ordered items and their respective prices.
     */
    public HashMap<String, Integer> get_Bill() {
        return order;
    }

    /**
     * Calculates the total value of the player's order.
     * 
     * @return The total sum of all ordered dishes.
     */
    public int totalOrderValue() {
        return sum;
    }
}
