package prr.core;

import prr.core.exception.InvalidKeyNumberException;
import prr.core.Terminal;
import prr.core.exception.*;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String terminalId, Client owner) throws InvalidKeyNumberException {
        super(terminalId, owner);
    }

    public Communication makeVideoCall(Terminal to) throws DestinationTerminalException {
        return _state.makeVideoCall(to);
    }

    public void acceptVideoCall(Communication communication) throws DestinationTerminalException{
        _state.acceptVideoCall(communication);
    }

    @Override
    public String toString() {
        String message = "FANCY" +"|"+ getId() + "|" + getOwner().getKey() + "|" + getState().toString()
                + "|" + Math.round(getPayments()) + "|" + Math.round(getDebts());
        if( !(getFriends().isEmpty())) {
            message += "|";
            message += String.join(",",getFriends());
        }
        return message;
    }
}