/**
 * Author: Krunal Savaj
 * This code has been developed using the Visual Studio Code editor.
 * Please inform me if any issues arise during code execution.
 */

/**
 * The InventorySystem class manages the ingredients available for cheesecake
 * production.
 * It allows adding, increasing, verifying, and reducing ingredient quantities.
 */
class InventorySystem {
    private Ingredient[] ingredients; // Replaced ArrayList with fixed-size array
    private int ingredientCount; // Tracks the number of ingredients in the array

    /**
     * Initializes the inventory system with a list of default ingredients.
     */
    public InventorySystem() {
        this.ingredients = new Ingredient[10]; // Initial capacity of 10, can be resized
        this.ingredientCount = 0;
        startDefaultIngredients();
    }

    /**
     * Initializes the inventory with a set of default ingredients and their
     * quantities.
     */
    private void startDefaultIngredients() {
        addIngredient("Cream_Cheese", 1500);
        addIngredient("Sugar", 1000);
        addIngredient("Eggs", 150);
        addIngredient("Dark_Chocolate", 700);
        addIngredient("Fresh_Strawberries", 600);
        addIngredient("Lemon_Juice", 500);
        addIngredient("Blueberry_Compote", 800);
    }

    /**
     * Adds a specified quantity of an ingredient to the inventory.
     * If the ingredient already exists, its quantity is increased.
     *
     * @param name     the name of the ingredient.
     * @param quantity the quantity of the ingredient to add.
     */
    public void addIngredient(String name, int quantity) {
        Ingredient findIngredient = findIngredientByName(name);
        if (findIngredient != null) {
            findIngredient.setQuantity(findIngredient.getQuantity() + quantity);
        } else {
            if (ingredientCount == ingredients.length) {
                resizeIngredientsArray();
            }
            ingredients[ingredientCount++] = new Ingredient(name, quantity);
        }
    }

    /**
     * Increases the quantity of an existing ingredient in the inventory.
     * If the ingredient does not exist, it is added to the inventory.
     *
     * @param ingredientName the name of the ingredient.
     * @param addedQuantity  the quantity to be added.
     */
    public void increaseIngredientQuantity(String ingredientName, int addedQuantity) {
        for (int i = 0; i < ingredientCount; i++) {
            if (ingredients[i].getName().equalsIgnoreCase(ingredientName)) {
                ingredients[i].setQuantity(ingredients[i].getQuantity() + addedQuantity);
                return;
            }
        }
        System.out.println("Ingredient not found. Adding it to the inventory.");
        addIngredient(ingredientName, addedQuantity);
    }

    /**
     * Verifies if the inventory has enough of the specified ingredient.
     *
     * @param ingredientName   the name of the ingredient to check.
     * @param requiredQuantity the required quantity of the ingredient.
     * @return true if the inventory has enough quantity, false otherwise.
     */
    public boolean verifyQuantity(String ingredientName, int requiredQuantity) {
        Ingredient findIngredient = findIngredientByName(ingredientName);
        return findIngredient != null && findIngredient.getQuantity() >= requiredQuantity;
    }

    /**
     * Reduces the quantity of a specified ingredient from the inventory.
     *
     * @param ingredientName the name of the ingredient.
     * @param quantity       the quantity to be reduced.
     * @throws IllegalArgumentException if there is not enough quantity or the
     *                                  ingredient is not found.
     */
    public void reduceIngredient(String ingredientName, int quantity) {
        Ingredient findIngredient = findIngredientByName(ingredientName);
        if (findIngredient != null && findIngredient.getQuantity() >= quantity) {
            findIngredient.setQuantity(findIngredient.getQuantity() - quantity);
        } else {
            throw new IllegalArgumentException("Not enough quantity or ingredient not found");
        }
    }

    /**
     * Checks if a specified ingredient exists in the inventory.
     *
     * @param ingredientName the name of the ingredient.
     * @return true if the ingredient exists, false otherwise.
     */
    public boolean ingredientExists(String ingredientName) {
        return findIngredientByName(ingredientName) != null;
    }

    /**
     * Finds an ingredient by its name.
     *
     * @param ingredientName the name of the ingredient to find.
     * @return the Ingredient object if found, null otherwise.
     */
    private Ingredient findIngredientByName(String ingredientName) {
        for (int i = 0; i < ingredientCount; i++) {
            if (ingredients[i].getName().equalsIgnoreCase(ingredientName)) {
                return ingredients[i];
            }
        }
        return null;
    }

    /**
     * Displays the current inventory of ingredients with their quantities.
     */
    public void displayIngredients() {
        System.out.println("Current Inventory:");
        for (int i = 0; i < ingredientCount; i++) {
            System.out.println(ingredients[i].getName() + " -> " + ingredients[i].getQuantity());
        }
    }

    /**
     * Resizes the ingredients array when it is full.
     */
    private void resizeIngredientsArray() {
        Ingredient[] newIngredients = new Ingredient[ingredients.length * 2]; // Double the size
        System.arraycopy(ingredients, 0, newIngredients, 0, ingredients.length);
        ingredients = newIngredients;
    }
}

/**
 * The Ingredient class represents an ingredient in the inventory with its name
 * and quantity.
 */
class Ingredient {
    private String ingredientName;
    private int quantity;

    /**
     * Constructs an ingredient with the specified name and quantity.
     *
     * @param name     the name of the ingredient.
     * @param quantity the quantity of the ingredient.
     */
    public Ingredient(String name, int quantity) {
        this.ingredientName = name;
        this.quantity = quantity;
    }

    /**
     * Gets the name of the ingredient.
     *
     * @return the name of the ingredient.
     */
    public String getName() {
        return ingredientName;
    }

    /**
     * Gets the quantity of the ingredient.
     *
     * @return the quantity of the ingredient.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ingredient.
     *
     * @param quantity the new quantity of the ingredient.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
