package prr.core;

import prr.core.exception.DestinationTerminalException;
import prr.core.exception.InvalidKeyNumberException;
import prr.core.Terminal;

public class BasicTerminal extends Terminal {

    public BasicTerminal(String terminalId, Client owner) throws InvalidKeyNumberException {
        super(terminalId, owner);
    }

    @Override
    public String toString(){
        String message = "BASIC" +"|"+ getId() + "|" + getOwner().getKey() + "|" + getState().toString() + "|" + Math.round(getPayments())
         + "|" + Math.round(getDebts());
        if( !(getFriends().isEmpty())) {
            message += "|";
            message += String.join(",",getFriends());
        }
        return message;
    }

    @Override


    public Communication makeVideoCall(Terminal to) throws DestinationTerminalException {
        throw new DestinationTerminalException("ORIGIN");
    }

    public void acceptVideoCall(Communication communication) throws DestinationTerminalException{
        throw new DestinationTerminalException("RECEIVE");
    }
}