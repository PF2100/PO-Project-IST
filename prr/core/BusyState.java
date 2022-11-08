package prr.core;

import prr.core.exception.DestinationTerminalException;


public class BusyState extends TerminalState {
    public BusyState(Terminal terminal,TerminalState state){
        super(terminal);
        _previous = state;
    }

    public boolean setOnIdle(){
        return false;
    }

    public String toString() {
        return "BUSY";
    }

    public boolean setBusy() {
        return false;
    }

    public boolean turnOff() {
        return false;
    }
    
    public boolean setOnSilent() {
        if(_terminal.getOngoingCommunication() == null) {
            _terminal.setTerminalState(new SilentState(_terminal));
        }
        return true;
    }



    public Communication makeSms(Terminal to, String message) throws DestinationTerminalException {
       return null;
    }

    public void acceptSms(Communication communication) throws DestinationTerminalException {
        _terminal.addReceivedCommunication(communication);
    }
    
    public Communication makeVoiceCall(Terminal to) throws DestinationTerminalException {
        return null; 
    }


    public void acceptVoiceCall(Communication communication) throws DestinationTerminalException {
        _terminal.addNotifiableClient(communication);
        throw new DestinationTerminalException("BUSY");
    }

    public Communication makeVideoCall(Terminal to) throws DestinationTerminalException {
       return null;
    }


    public void acceptVideoCall(Communication communication) throws DestinationTerminalException {
        _terminal.addNotifiableClient(communication);
        throw new DestinationTerminalException("BUSY");
    }

    public void unBusy() {}

    public boolean canEndCurrentCommunication() {
        return _terminal.getOngoingCommunication().getFrom().equals(_terminal);
    }
    public boolean canStartCommunication() {return false;}
}
