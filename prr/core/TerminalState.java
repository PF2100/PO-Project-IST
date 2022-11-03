package prr.core;

import java.io.Serializable;

import prr.core.exception.DestinationTerminalException;

public abstract class TerminalState implements Serializable{
    protected Terminal _terminal;

    public TerminalState(Terminal terminal) {
        _terminal = terminal;
    }

    public abstract boolean setOnIdle(); 
    public abstract boolean setBusy();
    public abstract boolean setOnSilent();
    public abstract boolean turnOff();

    public abstract Communication makeSms(Terminal to, String message) throws DestinationTerminalException;

    public abstract void acceptSms(Communication communication) throws DestinationTerminalException;

    public abstract Communication makeVoiceCall(Terminal to) throws DestinationTerminalException;
    
    public abstract void acceptVoiceCall(Communication communication) throws DestinationTerminalException;

    public abstract Communication makeVideoCall(Terminal to) throws DestinationTerminalException;

    

    protected abstract void acceptVideoCall(Communication communication) throws DestinationTerminalException;

    public abstract boolean canEndCurrentCommunication();
    public abstract boolean canStartCommunication();
    public abstract String toString();

    public abstract void unBusy();


}
