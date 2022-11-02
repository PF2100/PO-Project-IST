import java.util.*; 

public class ReturnStrings<T> {
    private T obj;
    
    public T getObj(){
        return obj;
    }

    public List<String> getAll(Collection<T> coll){
        List<String> strings = new ArrayList<>();
        for(var obj : coll) {
            strings.add(obj.toString());
        }
        return strings;
      }
}
