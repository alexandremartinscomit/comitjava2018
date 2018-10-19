import java.util.HashMap;
import java.util.LinkedHashMap;

public class MainMapTest {


    public static void main(String[] args){

        String text = "Teste alexandre testando auxiliar teacher database";

        LinkedHashMap<Character, Integer> textMap = new LinkedHashMap<>();

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
