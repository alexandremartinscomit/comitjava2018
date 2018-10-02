
import java.util.Scanner;

public class Review {

    public static void main(String[] args){
        int[] array = new int[5];

        Scanner scan = new Scanner(System.in);

        for(int i=0;i<5; i++){
            array[i] = scan.nextInt();
        }

        int bigger = 0;

        for(int i=0;i<5; i++){
            if(array[i] > bigger){
                bigger = array[i];
            }
        }

        



    }




}
