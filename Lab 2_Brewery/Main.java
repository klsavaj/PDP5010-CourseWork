/**
 * Author: Krunal Savaj
 * This code has been developed using the Visual Studio Code editor.
 * Please inform me if any issues arise during code execution.
 */

import java.util.Scanner;

/**
 * The Main class represents the entry point to the Brewery Control System.
 * It allows the user to interact with the inventory, recipes, and production
 * system to create batches of cheesecakes, add recipes, and manage ingredients.
 */
public class Main {
    /**
     * The main method is the entry point of the application. It initializes the
     * InventorySystem, RecipeLibrary, and ProductionSystem and presents a menu
     * for the user to interact with the system.
     * 
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        InventorySystem inventorySystem = new InventorySystem();
        RecipeLibrary recipeLibrary = new RecipeLibrary();
        ProductionSystem productionSystem = new ProductionSystem(inventorySystem, recipeLibrary);
        Scanner scanner = new Scanner(System.in);
        boolean start = true;

        while (start) {
            System.out.println("\nWelcome to the Brewery Control System!");
            System.out.println("Where you can make a variety of beverages and food items.\n"
                    + "We offer 5 types of cheesecakes by default, but feel free to add your own recipe and brew it.\n"
                    + "Cheesecakes are made in batches, and each recipe corresponds to one batch.\n"
                    + "Each batch contains two containers, with 7 cheesecakes in each container.\n"
                    + "!!!Happy Baking!!!\n");
            System.out.println("1. Display Ingredients");
            System.out.println("2. Display Default Recipes");
            System.out.println("3. Add a New Recipe");
            System.out.println("4. Create a Batch");
            System.out.println("5. Check Number of Clean Containers");
            System.out.println("6. Increase Quantity of Selected Ingredient");
            System.out.println("0. Exit");

            int option = 0;
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid integer option: ");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    // Display the current inventory of ingredients
                    inventorySystem.displayIngredients();
                    break;
                case 2:
                    // Display all the available recipes
                    recipeLibrary.showAllRecipes();
                    break;
                case 3:
                    // Add a new recipe and prompt the user to enter ingredients and their
                    // quantities
                    System.out.print("Enter the name of the new recipe: ");
                    String recipeName = scanner.nextLine();
                    Recipe newRecipe = new Recipe(recipeName);
                    System.out.println("Enter ingredients one by one!! (type 'complete' when finished):");

                    while (true) {
                        System.out.print("Ingredient name (or 'complete'): ");
                        String ingredientName = scanner.nextLine();
                        if ("complete".equalsIgnoreCase(ingredientName)) {
                            break;
                        }
                        System.out.print("Quantity of given ingredient for recipe: ");
                        int quantityForRecipe = 0;
                        while (!scanner.hasNextInt()) {
                            System.out.print("Please enter a valid integer quantity: ");
                            scanner.next();
                        }
                        quantityForRecipe = scanner.nextInt();
                        scanner.nextLine();

                        newRecipe.addIngredient(ingredientName, quantityForRecipe);

                        // Check if the ingredient is already in the inventory
                        if (!inventorySystem.ingredientExists(ingredientName)) {
                            System.out.print("Initial quantity in inventory for " + ingredientName + ": ");
                            int initialQuantity = 0;
                            while (!scanner.hasNextInt()) {
                                System.out.print("Please enter a valid integer quantity: ");
                                scanner.next();
                            }
                            initialQuantity = scanner.nextInt();
                            scanner.nextLine();
                            inventorySystem.addIngredient(ingredientName, initialQuantity);
                        }
                    }

                    recipeLibrary.addAllRecipe(newRecipe);
                    System.out.println("Recipe '" + recipeName + "' added.");
                    break;

                case 4:
                    // Create a batch of cheesecakes based on a selected recipe
                    System.out.print("Enter the name of the recipe for the batch: ");
                    String batchRecipeName = scanner.nextLine();
                    System.out.print("Enter the number of batches you want to make: ");
                    int numberBatches = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.print("Please enter a valid integer number of batches: ");
                        scanner.next(); // Consume the invalid input
                    }
                    numberBatches = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    productionSystem.createBatch(batchRecipeName, numberBatches);
                    break;

                case 5:
                    // Display the number of clean containers available
                    productionSystem.printContainers();
                    break;

                case 6:
                    // Increase the quantity of an existing ingredient in the inventory
                    System.out.print("Enter the name of the ingredient for which you want to increase quantity!: ");
                    String ingredientName = scanner.nextLine();
                    System.out.print("Enter the additional quantity: ");
                    int additionalQuantity = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.print("Please enter a valid integer quantity: ");
                        scanner.next();
                    }
                    additionalQuantity = scanner.nextInt();
                    scanner.nextLine();
                    inventorySystem.increaseIngredientQuantity(ingredientName, additionalQuantity);
                    System.out.println("Updated the quantity of " + ingredientName);
                    break;

                case 0:
                    // Exit the system
                    System.out.println("Exiting the Brewery Control System.");
                    start = false;
                    break;

                default:
                    // Handle invalid menu options
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
