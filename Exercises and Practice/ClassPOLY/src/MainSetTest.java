import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainSetTest {


    public static void main(String[] args){
//
//        ArrayList<Integer> array = new ArrayList<>();
//        array.add(1);
//        array.add(2);
//        array.add(3);
//        array.add(4);
//        array.add(5);
//        array.add(5);
//        array.add(5);
//        array.add(5);
//
//
//        for (Integer number:array) {
//            System.out.println(number);
//        }
//
//        LinkedHashSet<Integer> set = new LinkedHashSet<>();
//        set.add(5);
//        set.add(1);
//        set.add(12);
//        set.add(2);
//        set.add(7);
//        set.add(3);
//        set.add(4);
//        set.add(11);
//        set.add(15);
//        set.add(25);
//
//        for (Integer number:set) {
//            System.out.println(number);
//        }
//
//
//        HashSet<Animal> animalsHash = new HashSet<>();
//
//        animalsHash.add(new Duck("Alex"));
//        animalsHash.add(new Duck("Alex"));
//        animalsHash.add(new Duck("Mary"));
//
//        for (Animal tempAnimal:animalsHash) {
//            tempAnimal.eat(100);
//        }


        LinkedHashSet<Integer> set2 = new LinkedHashSet<>();


        long ini = System.currentTimeMillis();
        for (int i = 0; i<1000000; i++){
            set2.add(i);
        }
        long end = System.currentTimeMillis();

        System.out.println(end-ini);

        ini = System.currentTimeMillis();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i<1000000; i++){
            linkedList.add(i);
        }

        end = System.currentTimeMillis();
        System.out.println(end-ini);

        ini = System.currentTimeMillis();

        if(set2.contains(999999)){
            System.out.println("Found!");
        }

        end = System.currentTimeMillis();
        System.out.println(end-ini);

        ini = System.currentTimeMillis();

        if(linkedList.contains(999999)){
            System.out.println("Found!");
        }

        end = System.currentTimeMillis();
        System.out.println(end-ini);






    }

}
