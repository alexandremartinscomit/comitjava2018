public class Zoo {


    public static void main(String[] args){
        int x = 10;
        Bear bear1 = new Bear(34,10, "Wild");

        Duck alex = new Duck(34,10, "Wild");

        Duck paul = new Duck(34,10, "Wild");

        alex.setFriend(paul);

        //Animal animal = new Animal();

        alex.fly(1000);
        alex.eat(200);

        alex.setAge(35);

//        paul.specie = "Domestic";
//        paul.age = 12;
//        paul.color = 2;
        System.out.println(alex.getAge());
        System.out.println(alex.getColor());
        System.out.println(alex.getSpecie());

        //System.out.println(alex.weight);
        alex.eat(1000);
        alex.eat(1000);
        alex.eat(1000);
        alex.eat(1000);
        alex.eat(1000);
        alex.eat(14500);
        alex.eat(10000);
        //System.out.println(alex.weight);




    }

}
