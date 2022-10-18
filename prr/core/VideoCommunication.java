package prr.core;

import java.io.Serializable;

public class VideoCommunication extends InteractiveCommunication implements Serializable {
    private static final long serialVersionUID = 202208091753L;

    public VideoCommunication(Terminal from, Terminal to, int duration) {
        super(from, to, duration);
    }

    @Override
    protected double computeCost() {
        return 0;
        //falta implementar
    }

    @Override
    public String toString() {
        String status;
        if (isOngoing()) {status = "ONGOING";}
        else {status = "FINISHED";}

        return "VIDEO|"+ getId() +"|"+getFrom().getId()+"|" +getTo().getId() + "|"
                + this.getSize() +"|" + getCost() + "|" + status;
    }
}
