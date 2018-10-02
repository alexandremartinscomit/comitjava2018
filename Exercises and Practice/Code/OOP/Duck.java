public class Duck extends FlyAnimal {

    private Duck friend;

    public Duck(int age, int color, String specie) {
        super(age, color, specie);
    }

    public void walk(){
        System.out.println("Walking... done!");
    }

    public void setFriend(Duck friend){
        this.friend = friend;
    }


    void eat(int food) {
        if (weight + food < 20000) {
            weight = weight + food;
            System.out.println("eating " + food + "g...done!");
        } else {
            System.out.println("Duck is full!");
        }
    }

}
