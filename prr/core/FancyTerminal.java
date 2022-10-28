package prr.core;

import prr.core.exception.InvalidKeyNumberException;
import prr.core.Terminal;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String terminalID, Client owner) throws InvalidKeyNumberException {
        super(terminalID, owner);
    }

    public void makeVideoCall(Terminal to) {
        // implementar
    }

    protected void acceptVideoCall(Terminal from) {
        // implementar
        super.setBusy();
    }

    @Override
    public String toString() {
        String message = "FANCY" +"|"+ getId() + "|" + getOwner().getKey() + "|" + getMode()
                + "|" + Math.round(getPayments()) + "|" + Math.round(getDebt());
        /* 
        for (String terminalID : super.getFriends().keySet()) {
            message = message + "|" + terminalID;
        }
        */
        return message;
    }
}