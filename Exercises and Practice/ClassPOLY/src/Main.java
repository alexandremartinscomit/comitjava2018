public class Main {


    public static void main(String[] args){
        Duck obj1 = new Duck("alex");


        Duck obj2 = new Duck("alex");

        Duck obj3 = new Duck("paul");

        Dog obj4 = new Dog();
        Dog obj5 = new Dog();

        Animal[] animals = new Animal[5];
        animals[0] = obj1;
        animals[1] = obj2;
        animals[2] = obj3;
        animals[3] = obj4;
        animals[4] = obj5;

        processAnimalsToEat(animals);
    }

    public static void processAnimalsToEat(Animal[] animals){
        for(Animal tempAnimal:animals){
            tempAnimal.eat(100);
        }

        System.out.println("----");

        for(int i = 0; i<5; i++){
            animals[i].eat(100);
        }
    }
}
