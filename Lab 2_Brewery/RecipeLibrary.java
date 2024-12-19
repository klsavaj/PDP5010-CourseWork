/**
 * Author: Krunal Savaj
 * This code has been developed using the Visual Studio Code editor.
 * Please inform me if any issues arise during code execution.
 */

/**
 * Custom dynamic list implementation
 */
class CustomList<T> {
    private Object[] elements;
    private int size;

    /**
     * Constructor to initialize the custom list with an initial capacity.
     */
    public CustomList() {
        elements = new Object[10]; // initial capacity
        size = 0;
    }

    /**
     * Adds an element to the list, resizing the array if necessary.
     */
    public void add(T element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    /**
     * Resizes the internal array when capacity is reached.
     */
    private void resize() {
        Object[] newElements = new Object[elements.length * 2]; // double the capacity
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * Gets the element at the specified index.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) elements[index];
    }

    /**
     * Returns the current size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Clears the list by setting size to 0.
     */
    public void clear() {
        size = 0; // reset size
    }
}

/**
 * The Ingredient class represents an ingredient used in recipes.
 */
class Ingredient {
    private String name;
    private int quantity;

    public Ingredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}

/**
 * The Recipe class represents a recipe used for creating cheesecakes.
 * It stores the name of the recipe and the ingredients required for it.
 */
class Recipe {
    private String recipeName;
    private CustomList<Ingredient> ingredients; // Changed from ArrayList to CustomList

    /**
     * Constructs a new Recipe with the specified name.
     * 
     * @param name the name of the recipe.
     */
    public Recipe(String name) {
        this.recipeName = name;
        this.ingredients = new CustomList<>(); // Changed from ArrayList to CustomList
    }

    /**
     * Adds an ingredient to the recipe with the specified name and quantity.
     * 
     * @param ingredientName the name of the ingredient.
     * @param quantity       the quantity of the ingredient.
     */
    public void addIngredient(String ingredientName, int quantity) {
        ingredients.add(new Ingredient(ingredientName, quantity));
    }

    /**
     * Gets the name of the recipe.
     * 
     * @return the recipe name.
     */
    public String getName() {
        return recipeName;
    }

    /**
     * Retrieves all the ingredients required for the recipe.
     * 
     * @return a list of ingredients for the recipe.
     */
    public CustomList<Ingredient> getAllIngredients() { // Changed return type to CustomList
        return ingredients;
    }
}

/**
 * The RecipeLibrary class stores a collection of recipes and provides methods
 * to retrieve, add, and display recipes.
 */
class RecipeLibrary {
    private CustomList<Recipe> recipes; // Changed from ArrayList to CustomList

    /**
     * Constructs a new RecipeLibrary and initializes it with default recipes.
     */
    public RecipeLibrary() {
        this.recipes = new CustomList<>(); // Changed from ArrayList to CustomList
        startDefaultRecipes();
    }

    /**
     * Initializes the recipe library with a set of default cheesecake recipes.
     */
    private void startDefaultRecipes() {
        // Define default recipes
        Recipe classicNewYorkCheesecake = new Recipe("Classic New York Cheesecake");
        classicNewYorkCheesecake.addIngredient("Cream_Cheese", 45);
        classicNewYorkCheesecake.addIngredient("Sugar", 20);
        classicNewYorkCheesecake.addIngredient("Eggs", 3);

        Recipe chocolateCheesecake = new Recipe("Chocolate Cheesecake");
        chocolateCheesecake.addIngredient("Cream_Cheese", 65);
        chocolateCheesecake.addIngredient("Sugar", 20);
        chocolateCheesecake.addIngredient("Dark_Chocolate", 34);

        Recipe strawberryCheesecake = new Recipe("Strawberry Cheesecake");
        strawberryCheesecake.addIngredient("Cream_Cheese", 50);
        strawberryCheesecake.addIngredient("Sugar", 15);
        strawberryCheesecake.addIngredient("Fresh_Strawberries", 28);

        Recipe lemonCheesecake = new Recipe("Lemon Cheesecake");
        lemonCheesecake.addIngredient("Cream_Cheese", 70);
        lemonCheesecake.addIngredient("Sugar", 35);
        lemonCheesecake.addIngredient("Lemon_Juice", 25);

        Recipe blueBerryCheeseCake = new Recipe("Blueberry Cheesecake");
        blueBerryCheeseCake.addIngredient("Cream_Cheese", 80);
        blueBerryCheeseCake.addIngredient("Sugar", 40);
        blueBerryCheeseCake.addIngredient("Blueberry_Compote", 50);

        // Add default recipes to the list
        recipes.add(classicNewYorkCheesecake);
        recipes.add(chocolateCheesecake);
        recipes.add(strawberryCheesecake);
        recipes.add(lemonCheesecake);
        recipes.add(blueBerryCheeseCake);
    }

    /**
     * Adds a new recipe to the recipe library.
     * 
     * @param recipe the Recipe object to add.
     */
    public void addAllRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    /**
     * Retrieves a recipe by its name.
     * 
     * @param name the name of the recipe to retrieve.
     * @return the Recipe object if found, or null if not found.
     */
    public Recipe getRecipe(String name) {
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            if (recipe.getName().equalsIgnoreCase(name)) {
                return recipe;
            }
        }
        return null;
    }

    /**
     * Displays all the available recipes along with their ingredients and
     * quantities.
     */
    public void showAllRecipes() {
        System.out.println("Currently below are the available Recipes:");
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            System.out.println(recipe.getName());
            for (int j = 0; j < recipe.getAllIngredients().size(); j++) {
                Ingredient ingredient = recipe.getAllIngredients().get(j);
                System.out.println(" - " + ingredient.getName() + ": " + ingredient.getQuantity());
            }
        }
    }
}
