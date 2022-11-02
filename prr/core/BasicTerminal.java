package prr.core;

import prr.core.exception.InvalidKeyNumberException;
import prr.core.Terminal;

public class BasicTerminal extends Terminal {

    public BasicTerminal(String terminalId, Client owner) throws InvalidKeyNumberException {
        super(terminalId, owner);
    }

    @Override
    public String toString(){
        String message = "BASIC" +"|"+ getId() + "|" + getOwner().getKey() + "|" + getState().toString() + "|" + Math.round(getPayments())
         + "|" + Math.round(getDebt());
        if( !(getFriends().isEmpty())) {
            message += "|";
            message += String.join(",",getFriends());
        }
        return message;
    }
}