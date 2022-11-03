package prr.core;

import java.io.Serializable;
import prr.core.*;

public class VideoCommunication extends InteractiveCommunication implements Serializable {
    private static final long serialVersionUID = 202208091753L;

    public VideoCommunication(Terminal from, Terminal to) {
        super(from, to);
    }

    public void calculateCost() {
        getFrom().getOwner().calculateVideoCost(this);
    }


    @Override
    public String toString() {
        String status;
        if (isOngoing()) {status = "ONGOING";}
        else {status = "FINISHED";}

        return "VIDEO|"+ getId() +"|"+getFrom().getId()+"|" +getTo().getId() + "|"
                + getUnits() +"|" + getCost() + "|" + status;
    }

}
