package prr.app;

import java.util.Comparator;
import java.io.Serializable;

public class IdComparator implements Comparator<String> ,Serializable {
    public int compare(String key1, String key2) {return key1.compareToIgnoreCase(key2);}
}