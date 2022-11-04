package prr.core;

import java.io.Serializable;

public class VoiceCommunication extends InteractiveCommunication implements Serializable {
    private static final long serialVersionUID = 202208091753L;

    public VoiceCommunication(Terminal from, Terminal to) {
        super(from, to);
        setCommunicationType(CommunicationType.VOICE);
    }


    public void calculateCost() {
        getClientOwner().calculateVoiceCost(this);
        getClientOwner().checkUpdates(getType());
    }


    @Override
    public String toString() {
        String status;
        if (isOngoing()) {status = "ONGOING";}
        else {status = "FINISHED";}

        return getType().name() +"|" + getId() + "|" + getFrom().getId() + "|" + getTo().getId() + "|"
                + getUnits() + "|" + Math.round(getCost()) + "|" + status;
    }

}
