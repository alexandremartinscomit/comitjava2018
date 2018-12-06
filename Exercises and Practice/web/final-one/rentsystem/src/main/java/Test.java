import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class Test {



    public static void main(String[] args){

        Integer type = 10;
//
//        if(type!=null) {
//            type.toString();
//        }

       // Optional<Integer> opt = Optional.ofNullable(type);
//
//        if(opt.isPresent()){
//            opt.get().toString();
//        }


//        Consumer<Integer> consumer = o -> o.toString();

       // opt.ifPresent(o -> System.out.print(o.toString()));

        List<String> testCol = new ArrayList<>();
        testCol.add("alex");
        testCol.add("paul");
        testCol.add("carlos");

        boolean isAlex = false;

        for (String tempStr: testCol){
            if(tempStr.equals("alex")) {
                isAlex = true;
                break;
            }
        }

        Integer[] ints = new Integer[]{1,2,3,4};


        

        System.out.println(isAlex);

        System.out.println(
                testCol.stream().anyMatch(s -> s.equals("alex"))
        );

        System.out.println("-------");

        Consumer<String> test = s -> System.out.println(s);

        testCol.stream()
                .filter(t -> t.startsWith("a"))
                .forEach(System.out::println);








    }




}
