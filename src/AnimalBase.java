import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalBase {

    private ArrayList<Animal> animals;

    public AnimalBase() {
        animals = new ArrayList<>();
    }

    public void start() throws FileNotFoundException {
        UserInterface ui = new UserInterface(this);
        ui.start();
    }

    public static void main(String[] args) throws FileNotFoundException {
        AnimalBase app = new AnimalBase();
        app.start();
    }

    public Iterable<Animal> getAllAnimals() {
        return animals;
    }

    public int getAnimalCount() {
        return animals.size();
    }

    public void sortBy(String sortBy, SortDirection sortDirection) {
        // TODO: Implement sorting!
        System.out.println("TODO: Sort the list of animals by: " + sortBy);
    }

    public void createNewAnimal(String name, String description, String type, int age, double weight) {
        Animal animal = new Animal(name,description,type,age,weight);
        animals.add(animal);
    }

    public boolean deleteAnimal(String name) {
        // find animal with this name
        Animal animal = findAnimalByName(name);
        if(animal == null) {
            return false;
        } else {
            animals.remove(animal);
            return true;
        }
    }

    private Animal findAnimalByName(String name) {
        for(Animal animal : animals) {
            if(animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }


    public void loadDatabase() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("animals.csv")); //der bruges to scanner objekter til at læse
        while (fileScanner.hasNextLine()) { //fra filen, en til at læse linjer fra filen og anden til at læse hver linje
            Scanner sc = new Scanner(System.in).useDelimiter(";"); //laver tokens mellem ;, name er token, description er,
            String name = fileScanner.next(); //next() bruges når man kun vil læse til delimiter ; og ikke resten af linjen
            String description = fileScanner.next();
            String type = fileScanner.next();
            //System.out.println(); //scanner bug skal ikke bruges da den kun bruges ved brugerinput
            int age = fileScanner.nextInt();
            double weight = fileScanner.nextDouble();

            Animal animal = new Animal(name,description,type,age,weight);

            sc.close();
        }

        String line = fileScanner.nextLine();
        Scanner lineScanner = new Scanner(line).useDelimiter(";");

    }

    public void saveDatabase() throws FileNotFoundException {
        PrintStream out = new PrintStream("animals.csv");

        for(Animal animal : animals) {
            writeAnimal(out, animal);
        }
    }

    public void writeAnimal (PrintStream out, Animal animal){
        out.print(animal.getName());
        out.print(";");
        out.print(animal.getDesc());
        out.print(";");
        out.print(animal.getType());
        out.print(";");
        out.print(animal.getAge());
        out.print(";");
        out.print(animal.getWeight());
        out.print("\n");


    }

}
