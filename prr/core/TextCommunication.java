package prr.core;

import java.io.Serializable;
import prr.core.ClientState;

public class TextCommunication extends Communication implements Serializable {
    private static final long serialVersionUID = 202208091753L;
    private String _message;

    public TextCommunication(Terminal from, Terminal to, String message) {
        super(from, to);
        _message = message;
    }

    public String getMessage() {
        return _message;
    }

    @Override
    protected int getUnits() {
        return _message.length();
    }

    @Override
    public String toString() {
        return "TEXT|" + getId() +"|"+getFrom().getId()+"|" +getTo().getId() + "|"
                + this.getUnits() +"|" + getCost() + "|" + "FINISHED";
    }


}
