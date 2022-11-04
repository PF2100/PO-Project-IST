package prr.core;

import java.io.Serializable;
import prr.core.*;

public class TextCommunication extends Communication implements Serializable {
    private static final long serialVersionUID = 202208091753L;
    private String _message;

    public TextCommunication(Terminal from, Terminal to, String message) {
        super(from, to);
        _message = message;
        setCommunicationType(CommunicationType.TEXT);
    }

    public String getMessage() {
        return _message;
    }

    public void calculateCost() {
        getClientOwner().calculateTextCost(this);
        getClientOwner().checkUpdates(getType());
    }

    @Override
    protected int getUnits() {
        return _message.length();
    }

    @Override
    public String toString() {
        return getType().name()+"|" + getId() +"|"+getFrom().getId()+"|" +getTo().getId() + "|"
                + this.getUnits() +"|" + Math.round(getCost()) + "|" + "FINISHED";
    }


}
