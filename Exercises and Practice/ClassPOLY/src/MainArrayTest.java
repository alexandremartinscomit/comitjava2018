import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainArrayTest {


    public static void main(String[] args){
        int[] x = new int[2];
        x[0] = 1;
        x[1] = 5;

        LinkedList<Integer> numberList = new LinkedList();
        numberList.add(1);
        numberList.add(5);
        numberList.add(20);
        numberList.remove(2);

        for (int i=0; i<100000; i++){
            numberList.add(9);
        }

        for (int i =0;i<2; i++){
            System.out.println(x[i]);
        }


        int result = 0;
        for (int i =0;i<numberList.size(); i++){
            result += (int)numberList.get(i);
            System.out.println(numberList.get(i));
        }

        System.out.println(numberList.size());
        System.out.println("Result is "+result);












    }

}
