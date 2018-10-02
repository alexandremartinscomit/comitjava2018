public abstract class FlyAnimal extends Animal{

    public FlyAnimal(int age, int color, String specie){
        super(age, color, specie);
    }

    public void fly(int limit) {
        System.out.println("Flying over " + limit + " meters");

    }
}
