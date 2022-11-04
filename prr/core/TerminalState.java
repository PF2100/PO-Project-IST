package prr.core;

import java.io.Serializable;

import prr.core.exception.DestinationTerminalException;

public abstract class TerminalState implements Serializable{
    protected Terminal _terminal;
    protected TerminalState _previous;

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

    

    public abstract void acceptVideoCall(Communication communication) throws DestinationTerminalException;

    public void makeInteractiveCommunication(Communication communication){
        _terminal.addMadeCommunication(communication);
        _terminal.setOngoingCommunication(communication);
        communication.setOngoing();
        _terminal.setBusy();
    }
    
    public void receiveInteractiveCommunication(Communication communication){
        _terminal.addReceivedCommunication(communication);
        _terminal.setOngoingCommunication(communication);
        _terminal.setBusy();
    }




    public abstract boolean canEndCurrentCommunication();
    public abstract boolean canStartCommunication();
    public abstract String toString();

    public void returnState() {
        _previous.unBusy();
    }
    public abstract void unBusy();


}
