package prr.core;

import prr.core.exception.InvalidKeyNumberException;
import prr.core.Terminal;

public class BasicTerminal extends Terminal {

    public BasicTerminal(String terminalId, Client owner) throws InvalidKeyNumberException {
        super(terminalId, owner);
    }

    @Override
    public String toString() {
        String message = "BASIC" +"|"+ getId() + "|" + getOwner().getKey() + "|" + getMode() + "|" + Math.round(getPayments())
         + "|" + Math.round(getDebt());
        /* 
        for (String terminalId : super.getFriends().keySet()) {
            message = message + "|" + terminalId;
        }
        */
        return message;
    }
}