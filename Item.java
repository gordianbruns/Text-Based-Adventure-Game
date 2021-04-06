/**
    @author Gordian Bruns
    CSC 232A - Object-Oriented Software Development
    Checkpoint 3 - Adventure Game
    12/04/2020
*/

public class Item {

    // member variables
    private String name;
    private String type;
    private String description;

    /**
     * The constructor used to construct a new item from name, type, and description
     * @param name The name of the item
     * @param type The type of the item
     * @param description A brief description of the item and what it can be used for
     */
    public Item(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    /**
     * This method returns the name of the item
     * @return The name of the item
    */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the item
     * @param name The name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the type of the item
     * @return The type of the item
     */
    public String getType() {
        return type;
    }

    /**
     * This method sets the type of the item
     * @param type The type of the item
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method returns the description of the item
     * @return The description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method sets the description of the item
     * @param description The description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method returns a String representing the item
     * @return representation of the item
     */
    @Override
    public String toString() {
        return name + " [" + type + "]: " + description;
    }
    
}
