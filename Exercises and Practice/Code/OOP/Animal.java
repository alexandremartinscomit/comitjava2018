public abstract class Animal {

    protected int color;
    protected String specie;
    protected int age;
    protected int weight;
    protected Person owner;

    public Animal(){

    }

    public Animal(int age, int color, String specie){
        this.age = age;
        this.color = color;
        this.specie = specie;
    }

    void eat(int food) {
        weight = weight + food;
        System.out.println("eating " + food + "g...done!");
    }

    public abstract void walk();


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getColor() {
        return color;
    }

    public String getSpecie() {
        return specie;
    }
}
