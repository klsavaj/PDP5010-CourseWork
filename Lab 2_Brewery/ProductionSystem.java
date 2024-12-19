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
 * Represents a cheesecake used in the production system.
 */
class Cheesecake {
    // Assuming a basic representation for Cheesecake class
}

/**
 * Represents a container for holding cheesecakes.
 */
class Container {
    private boolean isClean;
    private CustomList<Cheesecake> cheesecakes;
    private boolean wasUsedInPrevBatch;

    /**
     * Initializes a new container that is clean and empty.
     */
    public Container() {
        this.isClean = true;
        this.cheesecakes = new CustomList<>();
        this.wasUsedInPrevBatch = false;
    }

    /**
     * Fills the container with a specified number of cheesecakes.
     *
     * @param numberOfCheesecakes the number of cheesecakes to be added.
     * @throws IllegalStateException if the container is not clean or already
     *                               contains cheesecakes.
     */
    public void fillContainerWithCheesecakes(int numberOfCheesecakes) {
        if (!isClean || cheesecakes.size() > 0) {
            throw new IllegalStateException("Container must be clean and empty to be filled.");
        }
        for (int i = 0; i < numberOfCheesecakes; i++) {
            cheesecakes.add(new Cheesecake());
        }
        isClean = false;
    }

    /**
     * Checks if the container is clean.
     *
     * @return true if the container is clean, false otherwise.
     */
    public boolean isClean() {
        return isClean;
    }

    /**
     * Cleans the container by setting it to clean and removing all cheesecakes.
     */
    public void clean() {
        isClean = true;
        cheesecakes.clear();
    }

    /**
     * Returns the number of cheesecakes in the container.
     *
     * @return the number of cheesecakes.
     */
    public int getCheesecakeCount() {
        return cheesecakes.size();
    }

    /**
     * Checks if the container was used in the previous batch.
     *
     * @return true if it was used in the previous batch, false otherwise.
     */
    public boolean wasUsedInPrevBatch() {
        return wasUsedInPrevBatch;
    }

    /**
     * Sets whether the container was used in the previous batch.
     *
     * @param usedInPrevBatch true if the container was used, false otherwise.
     */
    public void setUsedInPrevBatch(boolean usedInPrevBatch) {
        this.wasUsedInPrevBatch = usedInPrevBatch;
    }
}

/**
 * Manages the production system of cheesecakes.
 */
class ProductionSystem {
    private InventorySystem inventory;
    private RecipeLibrary recipeLibrary;
    private CustomList<Container> containers;

    /**
     * Initializes the production system with an inventory system and a recipe
     * library.
     *
     * @param inventory     the inventory system to use.
     * @param recipeLibrary the recipe library containing available recipes.
     */
    public ProductionSystem(InventorySystem inventory, RecipeLibrary recipeLibrary) {
        this.inventory = inventory;
        this.recipeLibrary = recipeLibrary;
        this.containers = new CustomList<>();
        // Initialize containers, assuming each batch requires two containers
    }

    /**
     * Creates a batch of cheesecakes based on the provided recipe and number of
     * batches.
     *
     * @param recipeName      the name of the recipe to use.
     * @param numberOfBatches the number of batches to create.
     */
    public void createBatch(String recipeName, int numberOfBatches) {
        Recipe recipe = recipeLibrary.getRecipe(recipeName);
        if (recipe == null) {
            System.out.println("Recipe is not found.");
            return;
        }

        for (int batch = 0; batch < numberOfBatches; batch++) {
            // Check if we have enough ingredients for the recipe in the inventory
            for (int i = 0; i < recipe.getAllIngredients().size(); i++) {
                Ingredient ingredient = recipe.getAllIngredients().get(i);
                if (!inventory.verifyQuantity(ingredient.getName(), ingredient.getQuantity())) {
                    System.out.println("Not enough ingredients for the asked recipe: " + ingredient.getName());
                    return;
                }
            }

            // Reduce the ingredients from the inventory
            for (int i = 0; i < recipe.getAllIngredients().size(); i++) {
                Ingredient ingredient = recipe.getAllIngredients().get(i);
                inventory.reduceIngredient(ingredient.getName(), ingredient.getQuantity());
            }

            for (batch = 0; batch < numberOfBatches; batch++) {
                for (int i = 0; i < 2; i++) {
                    this.containers.add(new Container()); // Each container can hold 7 cheesecakes
                }
                // Reset the wasUsedInPrevBatch flag at the start of each batch
                for (int i = 0; i < containers.size(); i++) {
                    containers.get(i).setUsedInPrevBatch(false);
                }

                // Fill containers with cheesecakes
                int occupiedContainers = 0;
                for (int i = 0; i < containers.size(); i++) {
                    Container container = containers.get(i);
                    if (container.isClean()) {
                        container.fillContainerWithCheesecakes(7); // Fill the container with 7 cheesecakes
                        container.setUsedInPrevBatch(true);
                        occupiedContainers++;
                    }

                    if (occupiedContainers == 2) { // Assuming each batch requires two containers
                        break;
                    }
                }

                if (occupiedContainers < 2) {
                    Container newContainer = new Container();
                    newContainer.fillContainerWithCheesecakes(7);
                    newContainer.setUsedInPrevBatch(true);
                    containers.add(newContainer);
                    occupiedContainers++;
                }

                // Clean the containers after use
                for (int i = 0; i < containers.size(); i++) {
                    containers.get(i).clean();
                }
            }

            System.out.println(
                    "Congratulations!! " + numberOfBatches + " Batch(es) for " + recipeName + " made successfully.");
        }
    }

    /**
     * Prints the status of containers, indicating which were used in the previous
     * batch.
     */
    public void printContainers() {
        int usedContainersCount = 0;
        int cleanContainersCount = 0;

        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            if (container.wasUsedInPrevBatch()) {
                System.out.println("Container was used in the previous batch and is now clean.");
                usedContainersCount++;
            } else if (container.isClean()) {
                cleanContainersCount++;
            }
        }

        System.out.println(usedContainersCount + " Containers were used in the previous batch and they are now clean.");
        System.out.println(cleanContainersCount + " Containers are clean and were not used in the previous batch.");
    }

    /**
     * Displays the current status of all containers, showing if they are clean or
     * filled with cheesecakes.
     */
    public void displayContainers() {
        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            String status = container.isClean() ? "Clean"
                    : "Filled with " + container.getCheesecakeCount() + " Cheesecakes!!";
            System.out.println("Container " + (i + 1) + ": " + status);
        }
    }
}
