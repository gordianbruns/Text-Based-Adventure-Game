/**
    @author Gordian Bruns
    CSC 232A - Object-Oriented Software Development
    Checkpoint 3 - Adventure Game
    12/04/2020
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;

public class Driver {

    public final static HashSet<String> DIRECTIONS = new HashSet<>(Arrays.asList("north", "east", "south", "west"));
    private static Location currLocation;
    private static ContainerItem myInventory = new ContainerItem("Inventory", "Container", "Your personal backpack to store items");
    public static void main(String[] args) {
        
        createWorld();

        System.out.println("Welcome to Gordian's spooky mansion!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();

            // split up command and parameters
            String[] split_command = command.split(" ");

            switch(split_command[0]) {
                // quits the game
                case "quit":
                    System.out.println("Thanks for playing!");
                    scanner.close();
                    System.exit(0);
                    break;
                // prints the inventory
                case "inventory":
                    System.out.println(myInventory);
                    break;
                // add items to the inventory
                case "take":
                    Item itemToTake;
                    if (split_command.length == 1) {
                        System.out.println("Please specify the item you want to take!");
                        break;
                    } else if (split_command.length == 2) {
                        itemToTake = currLocation.getItem(split_command[1]);
                        if (itemToTake instanceof ContainerItem) {
                            System.out.println("You cannot take a container item.");
                            break;
                        }
                        // if the item is not present
                        if (itemToTake == null) {
                            System.out.println("The item is not present at the current location.");
                            break;
                        }
                        currLocation.removeItem(itemToTake.getName());
                    } else if (split_command.length >= 3 && !split_command[2].equalsIgnoreCase("from")) {
                        System.out.println("If you take an item from a container, please use the format \"take [item] from [container]\", otherwise, use \"take [item]\".");
                        break;
                    } else if (split_command.length == 3 && split_command[2].equalsIgnoreCase("from")) {
                        System.out.println("Please specify the item you want to take from the container item!");
                        break;
                    } else {
                        Item temp = currLocation.getItem(split_command[3]);
                        // if user uses an item instead of a container item
                        if (!(temp instanceof ContainerItem) && temp != null) {
                            System.out.println(temp.getName() + " is not a container item.");
                            break;
                        }
                        ContainerItem container = (ContainerItem) temp;
                        // if container is not present
                        if (container == null) {
                            System.out.println("The container item is not present at the current location.");
                            break;
                        }
                        itemToTake = container.removeItem(split_command[1]);
                        // if the item is not in the container item
                        if (itemToTake == null) {
                            System.out.println("The item is not present in " + container.getName() + ".");
                            break;
                        }
                        container.removeItem(split_command[1]);
                    }
                    myInventory.addItem(itemToTake);
                    System.out.println("The item has been added to your inventory.");
                    break;
                // put items into a container item
                case "put":
                    if (split_command.length <= 3 || !split_command[2].equals("in")) {
                        System.out.println("Please use the format \"put [item] in [container]\".");
                        break;
                    }
                    Item temp = currLocation.getItem(split_command[3]);
                    // if user uses an item instead of a container item
                    if (!(temp instanceof ContainerItem) && temp != null) {
                        System.out.println(temp.getName() + " is not a container item.");
                        break;
                    }
                    ContainerItem container = (ContainerItem) temp;
                    // if container is not present
                    if (container == null) {
                        System.out.println("The container is not present at the current location.");
                        break;
                    }
                    Item itemToPut = myInventory.removeItem(split_command[1]);
                    // if the item is not present in the inventory
                    if (itemToPut == null) {
                        System.out.println("This item is not present in your inventory.");
                        break;
                    }
                    container.addItem(itemToPut);
                    System.out.println("The item has been placed in the container.");
                    break;
                // drop items from the inventory
                case "drop":
                    if (split_command.length == 1) {
                        System.out.println("Please specify the item you want to drop!");
                        break;
                    }
                    Item itemToDrop = myInventory.removeItem(split_command[1]);
                    // if the item is not present in the inventory
                    if (itemToDrop == null) {
                        System.out.println("This item is not present in your inventory.");
                        break;
                    }
                    currLocation.addItem(itemToDrop);
                    System.out.println("The item has been dropped.");
                    break;
                // prints the location with a description and the present items
                case "look":
                    System.out.println(currLocation);
                    break;
                // prints information about a given item
                case "examine":
                    // if no item is given
                    if (split_command.length == 1) {
                        System.out.println("Please specify the item you want to examine!");
                        break;
                    }
                    Item itemToExamine = currLocation.getItem(split_command[1]);
                    // if the item is not present
                    if (itemToExamine == null) {
                        System.out.println("The item is not present at the current location.");
                        break;
                    }
                    System.out.println(itemToExamine);
                    break;
                // moves the character to to a given direction
                case "go":
                    // if no direction is given
                    if (split_command.length == 1) {
                        System.out.println("Please specify the direction you want to go to!");
                        break;
                    }
                    // accept n, e, s, and w as directions
                    String direction = split_command[1];
                    if (direction.equals("n")) {
                        direction = "north";
                    } else if (direction.equals("e")) {
                        direction = "east";
                    } else if (direction.equals("s")) {
                        direction = "south";
                    } else if (direction.equals("w")) {
                        direction = "west";
                    }
                    Location newLocation = currLocation.getLocation(direction.toLowerCase());
                    // key not in HashMap -> not possible to go into this direction or the direction was misspelled
                    if (newLocation == null && DIRECTIONS.contains(direction.toLowerCase())) {
                        System.out.println("You cannot go into this direction! Please try a different direction!");
                        break;
                    } else if (newLocation == null && DIRECTIONS.contains(direction.toLowerCase()) == false) {
                        System.out.println("Not a valid direction! Try north (n), east (e), south (s), or west (w).");
                        break;
                    }
                    currLocation = newLocation;
                    System.out.println("Your character has moved to a new location.");
                    break;
                // list all commands
                case "help":
                    System.out.println("List of commands:");
                    System.out.println(" + look - Look at the current location and what items you can find here");
                    System.out.println(" + examine [item/container] - Take a closer look at a specific item or container");
                    System.out.println(" + go [direction] - go into a specified direction (possible directions: north (n), east (e), south (s), and west (w))");
                    System.out.println(" + inventory - displays what items you currently have in your inventory");
                    System.out.println(" + take [item] - put an item into your inventory");
                    System.out.println(" + take [item] from [container] - put an item from a container into your inventory");
                    System.out.println(" + put [item] in [container] - put an item from your inventory into a container");
                    System.out.println(" + drop [item] - drop an item from your inventory");
                    System.out.println(" + quit - quits the game");
                    break;
                // if the command is none of the above cases
                default :
                    System.out.println("Unknown command. Try help to see a list of all commands.");
            }
        }
    }

    /*
     * This method creates the world
     */
    public static void createWorld() {
        Location kitchen = new Location("Kitchen", "A dark kitchen whose lights are flickering");
        Location hallway = new Location("Hallway", "An eerie hallway littered with abandoned spider webs");
        Location bedroom = new Location("Bedroom", "A shady bedroom with a mattress on the floor that was ripped open");
        Location livingRoom = new Location("Living Room", "A small living room with blood on the walls");

        bedroom.connect("south", hallway);
        hallway.connect("north", bedroom);
        hallway.connect("east", kitchen);
        kitchen.connect("west", hallway);
        hallway.connect("south", livingRoom);
        livingRoom.connect("north", hallway);

        Item knife = new Item("Knife", "Weapon", "A sharp and long knife that might bail you out of some situation");
        kitchen.addItem(knife);
        Item turkey = new Item("Turkey", "Food", "A delicious turkey, which you should try whenever your health is low");
        kitchen.addItem(turkey);
        Item wrench = new Item("Wrench", "Tool", "A useful tool, which might be useful to find hidden rooms");
        livingRoom.addItem(wrench);
        Item flashlight = new Item("Flashlight", "Tool", "This flashlight might help you when it is too dark to see");
        bedroom.addItem(flashlight);
        Item apple = new Item("Apple", "Food", "A tasty looking apple, which you should try whenever your health is low");
        kitchen.addItem(apple);
        Item doll = new Item("Doll", "Other", "A dirty baby doll with only one eye");
        hallway.addItem(doll);

        ContainerItem chest = new ContainerItem("Chest", "Container", "An old, rusty chest");
        Item coin = new Item("Coin", "Tool", "A golden coin, which looks valuable");
        chest.addItem(coin);
        livingRoom.addItem(chest);
        
        ContainerItem vault = new ContainerItem("Vault", "Container", "A shiny vault, which might have something valuable inside");
        Item ring = new Item("Ring", "Tool", "A silver ring with a diamond");
        vault.addItem(ring);
        Item bracelet = new Item("Bracelet", "Tool", "A sparkling bracelet");
        vault.addItem(bracelet);
        bedroom.addItem(vault);

        ContainerItem backpack = new ContainerItem("Backpack", "Container", "A modern looking backpack");
        Item compass = new Item("Compass", "Tool", "An old compass that might be helpful in some situation");
        backpack.addItem(compass);
        hallway.addItem(backpack);

        currLocation = bedroom;

    }
}
