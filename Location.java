/**
    @author Gordian Bruns
    CSC 232A - Object-Oriented Software Development
    Checkpoint 3 - Adventure Game
    12/04/2020
*/

import java.util.ArrayList;
import java.util.HashMap;

public class Location {

    // member variables
    private String name;
    private String description;
    private ArrayList<Item> items;
    private HashMap<String, Location> connections;

    /**
     * The constructor used to construct a new location from name and description
     * @param name The name of the location
     * @param description A short description of the location
     */
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<Item>();
        this.connections = new HashMap<String, Location>();
    }

    /**
     * This method returns the name of the location
     * @return The name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the location
     * @param name The name of the location
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the description of the location
     * @return The description of the location
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method sets the description ofthe location
     * @param description The description of the location
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method adds an item to the location
     * @param item The item to add to the location
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * This method checks whether the location has the given item
     * @param itemName The name of the item
     * @return true or false
     */
    public boolean hasItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns an item at the location
     * @param itemName The name of the item to return
     * @return The item, or null if the item is not present
     */
    public Item getItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(itemName)) {
                return items.get(i);
            }
        }
        return null;
    }

    /**
     * This method returns an item at the location
     * @param index The index of the item to return
     * @return The item, or null if the item is not present
     */
    public Item getItem(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    /**
     * This method returns the number of items at the location
     * @return The number of items
     */
    public int numItems() {
        return items.size();
    }

    /**
     * This method removes an item from the location
     * @param itemName The name of the item to remove
     * @return The item, or null if the item is not present
     */
    public Item removeItem(String itemName) {
        Item item = getItem(itemName);
        if (item != null) {
            items.remove(item);
        }
        return item;
    }

    /**
     * This method connects two locations with each other
     * @param direction The direction in which the other location is
     * @param location The other location we connect the location to
     */
    public void connect(String direction, Location location) {
        connections.put(direction, location);
    }

    /**
     * This method checks whether we can move into a certain direction
     * @param direction The direction we check
     * @return true or false
     */
    public boolean canMove(String direction) {
        if (connections.containsKey(direction)) {
            return true;
        }
        return false;
    }

    /**
     * This method returns the location if you go into a certain direction
     * @param direction The direction in which the location to return is
     * @return The location, or null if there is no location in this direction
     */
    public Location getLocation(String direction) {
        if (canMove(direction)) {
            return connections.get(direction);
        }
        return null;
    }

    /**
     * This method returns a String representing the location
     * @return A representation of the location
     */
    @Override
    public String toString() {
        StringBuilder locationToString = new StringBuilder();
        locationToString.append(name + " - " + description + "\n" + "This location has the following items:"
        + "\n");
        for (int i = 0; i < numItems(); i++) {
            locationToString.append(" + " + items.get(i).getName());
            if (i != numItems() - 1) {
                locationToString.append("\n");
            }
        }
        if (numItems() == 0) {
            locationToString.append("No items in this location");
        }
        return locationToString.toString();
    }
    
}
