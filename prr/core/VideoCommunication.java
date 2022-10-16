package prr.core;

public class VideoCommunication extends InteractiveCommunication {

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
