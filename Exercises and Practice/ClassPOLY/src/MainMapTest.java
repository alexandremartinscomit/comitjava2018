import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class MainMapTest {


    public static void main(String[] args){

        TreeMap<Duck, String> mapDuck = new TreeMap<>(new Comparator<Duck>(){

            public int compare(Duck obj1, Duck obj2){
                if(obj1.getAge() > obj2.getAge()){
                    return 1;
                } else if(obj1.getAge() < obj2.getAge()){
                    return -1;
                } else {
                    return 0;
                }
            }
        });


        String text = "Teste alexandre testando auxiliar teacher database";

        TreeMap<Character, Integer> textMap = new TreeMap<>();

        for(int i=0; i< text.length(); i++){
            String currentChar = text.charAt(i)+"";
            Character charTemp = currentChar.toLowerCase().toCharArray()[0];
            if(textMap.containsKey(charTemp)){
                int amount = textMap.get(charTemp);
                textMap.put(charTemp,amount+1);
            } else {
                textMap.put(charTemp,1);
            }
        }


        for(Character temChar: textMap.keySet()){
            System.out.println(temChar+" : "+textMap.get(temChar));
        }














    }
}
