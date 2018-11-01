import java.util.Objects;

public class Duck extends Animal {

    private final String name;

    private final int age;

    public Duck(String name, int age){
        this.name = name;
        this.age = age;
    }

    public int getAge(){
        return age;
    }


    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Duck duck = (Duck) o;
        return Objects.equals(name, duck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
