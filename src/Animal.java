public class Animal {
    private String name;
    private String desc;
    private String type;
    private int age;
    private double weight;

    public Animal(String name, String desc, String type, int age, double weight) {
        this.name = name;
        this.desc = desc;
        this.type = type;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
    public int getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + " the " + desc + " " + type + ", age " + age + ", weight " + weight +"kg";
    }



}
