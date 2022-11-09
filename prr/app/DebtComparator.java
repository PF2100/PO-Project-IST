package prr.app;

import java.util.Comparator;
import java.io.Serializable;
import prr.core.Client;

public class DebtComparator implements Comparator<Client>,Serializable{
    public int compare(Client that, Client other){
      if( that.getDebts() == other.getDebts()) {
        return that.getKey().compareToIgnoreCase(other.getKey());
      }
      else if( that.getDebts() - other.getDebts() > 0) {return -1;}
      else{return 1;}
    }
}