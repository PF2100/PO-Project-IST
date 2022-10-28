package prr.core;

import prr.core.exception.InvalidKeyNumberException;
import prr.core.Terminal;

public class BasicTerminal extends Terminal {

    public BasicTerminal(String terminalID, Client owner) throws InvalidKeyNumberException {
        super(terminalID, owner);
    }

    @Override
    public String toString() {
        String message = "BASIC" +"|"+ getId() + "|" + getOwner().getKey() + "|" + getMode() + "|" + Math.round(getPayments())
         + "|" + Math.round(getDebt());
        /* 
        for (String terminalID : super.getFriends().keySet()) {
            message = message + "|" + terminalID;
        }
        */
        return message;
    }
}