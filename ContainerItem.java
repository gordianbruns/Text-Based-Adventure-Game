/**
    @author Gordian Bruns
    CSC 232A - Object-Oriented Software Development
    Checkpoint 3 - Adventure Game
    12/04/2020
*/

import java.util.ArrayList;

public class ContainerItem extends Item {

    // member variables
    private ArrayList<Item> items;

    /**
     * The constructor used to create a new ContainerItem from name, type, and description
     * @param name The name of the ContainerItem
     * @param type The type of the ContainerItem
     * @param description The description of the ContainerItem
     */
    public ContainerItem(String name, String type, String description) {
        super(name, type, description);
        this.items = new ArrayList<Item>();
    }

    /**
     * This method adds an item to the ContainerItem
     * @param item The item to add to the ContainerItem
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * This method checks whether the ContainerItem has a specific item
     * @param name The name of the item
     * @return true or false
     */
    public boolean hasItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method removes an item from the ContainerItem
     * @param name The name of the item to remove
     * @return The item we removed, or null if the item is not present
     */
    public Item removeItem(String name) {
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return null;
        }
        Item copy = items.get(index);
        items.remove(index);
        return copy;
    }

    /**
     * This method returns a representation of the ContainerItem
     * @return A representation of the ContainerItem
     */
    @Override
    public String toString() {
        StringBuilder containerItemToString = new StringBuilder();
        containerItemToString.append(super.toString() + "\n");
        for (int i = 0; i < items.size(); i++) {
            containerItemToString.append(" + " + items.get(i).getName());
            if (i != items.size() - 1) {
                containerItemToString.append("\n");
            }
        }
        if (items.size() == 0) {
            containerItemToString.append("No items in here");
        }
        return containerItemToString.toString();
    }
    
}
