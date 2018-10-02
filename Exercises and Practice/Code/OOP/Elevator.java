import java.util.Scanner;

public class Elevator {



    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int temp = scanner.nextInt();


        int amount = 1;

        int weight = temp;


        System.out.println(weight);
        if ((weight <= 600) && (amount <= 8)){
            System.out.println("Good!");
        } else {
            System.out.println("Get out at least one!");
        }



    }

}
