package prr.core;

import prr.core.exception.InvalidKeyNumberException;
import prr.core.Terminal;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String terminalId, Client owner) throws InvalidKeyNumberException {
        super(terminalId, owner);
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
        String message = "FANCY" +"|"+ getId() + "|" + getOwner().getKey() + "|" + getState().toString()
                + "|" + Math.round(getPayments()) + "|" + Math.round(getDebt());
        if( !(getFriends().isEmpty())) {
            message += "|";
            message += String.join(",",getFriends());
        }
        return message;
    }
}