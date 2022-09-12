import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    private final AnimalBase application;

    public UserInterface(AnimalBase application) {
        this.application = application;
    }

    public void start() throws FileNotFoundException {
        System.out.println("Welcome to ANIMALBASE 2022");
        System.out.println("==========================");
        System.out.println("Java edition\n");

        while (true) {
            switch (mainMenu()) {
                case 0 -> exit();
                case 1 -> list();
                case 2 -> filter();
                case 3 -> sort();
                case 4 -> create();
                case 5 -> delete();
                case 6 -> load();
                case 7 -> save();
            }
        }
    }

    public int mainMenu() {
        System.out.println("""
                Main menu
                ---------
                1) List all animals
                2) Filter list of animals
                3) Sort list of animals
                4) Create new animal
                5) Delete animal
                6) Load animals from file
                7) Save animals to file
                                
                0) Exit application
                """);
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        while (choice < 0 || choice > 7) {
            System.out.println("Only values 0-7 allowed");
            choice = input.nextInt();
        }

        return choice;
    }

    private void exit() {
        // TODO: Maybe save before exiting???
        System.out.println("Thank you for using ANIMALBASE 2022");
        System.out.println("Please consider upgrading to Enterprise Edition!");
        System.out.println("Subscribe to our newsletter with all the details you need about creating lists of animals!");
        System.exit(0);
    }

    private void list() {
        System.out.println("List of all the animals");
        System.out.println("-----------------------");
        for (Animal animal : application.getAllAnimals()) {
            System.out.println(animal);
        }
        System.out.println("There are " + application.getAnimalCount() + " animals in the list.");
    }

    private void filter() {
        System.out.println("-- filter not yet implemented ---");
    }

    private void sort() {
        System.out.println("""
                Sort the list of animals by
                n) Name
                t) Type
                a) Age
                w) Weight
                """);
        Scanner input = new Scanner(System.in);
        char sortBy = input.next().trim().toLowerCase().charAt(0);
        while (sortBy != 'n' && sortBy != 't' && sortBy != 'a' && sortBy != 'w') {
            System.out.println("Please type 'n', 't', 'a' or 'w'");
            sortBy = input.next().trim().toLowerCase().charAt(0);
        }

        System.out.println("""
                Set the sort direction:
                a) Ascending (0-9 a-z)
                d) Descending (9-0 z-a)
                t) Toggle (The opposite of what it was last time)
                """);

        char ch = input.next().trim().toLowerCase().charAt(0);
        while (ch != 'a' && ch != 'd' && ch != 't') {
            System.out.println("Please type 'a', 'd' or 't'");
            ch = input.next().trim().toLowerCase().charAt(0);
        }

        SortDirection direction = switch (ch) {
            case 'a' -> SortDirection.ASC;
            case 'd' -> SortDirection.DESC;
            case 't' -> SortDirection.TOGGLE;
            default -> SortDirection.ASC;
        };

        if (sortBy == 'n') {
            application.sortBy("name", direction);
        } else if (sortBy == 't') {
            application.sortBy("type", direction);
        } else if (sortBy == 'a') {
            application.sortBy("age", direction);
        }

        // When sorted, show the list again
        list();
    }

    private void create() {
        System.out.println("Create new animal\n-----------------");
        Scanner input = new Scanner(System.in);
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Description: ");
        String description = input.nextLine();
        System.out.print("Type: ");
        String type = input.nextLine();
        System.out.print("Age: ");
        int age = input.nextInt();
        input.nextLine(); // ScannerBug fix
        System.out.print("Weight (kg): ");
        double weight = input.nextDouble();
        input.nextLine(); // ScannerBug fix

        application.createNewAnimal(name, description, type, age, weight);

        // When created a new animal, show the list again
        list();
    }

    private void delete() {
        System.out.println("Delete animal");
        System.out.println("-------------");
        System.out.println("Please enter the name of the animal to be deleted: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        boolean success = application.deleteAnimal(name);
        if (success) {
            System.out.println("The animal with name '" + name + "' has been deleted");
        } else {
            System.out.println("Animal with name '" + name + "' does not exist, and cannot be deleted");
        }
    }

    private void load() throws FileNotFoundException {
        System.out.println("Loading the database ...");
        application.loadDatabase();
        System.out.println("Done!");
    }

    private void save() throws FileNotFoundException {
        System.out.println("Saving the database ...");
        application.saveDatabase();
        System.out.println("Saving database completed succesfully");
        System.out.println("You can now exit the application");
    }

}
