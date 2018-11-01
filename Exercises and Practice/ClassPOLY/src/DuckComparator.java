import java.util.Comparator;

public class DuckComparator implements Comparator<Duck> {

    public int compare(Duck obj1, Duck obj2){
        if(obj1.getAge() > obj2.getAge()){
            return 1;
        } else if(obj1.getAge() < obj2.getAge()){
            return -1;
        } else {
            return 0;
        }
    }


}
