package prr.app;

import java.util.Comparator;
import java.io.Serializable;
import prr.core.Communication;

public class CommunicationComparator implements Comparator<Communication>,Serializable {
    public int compare(Communication that, Communication other) {return that.getId() - other.getId();};
  }