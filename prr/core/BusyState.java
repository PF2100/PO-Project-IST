package prr.core;

import prr.core.Terminal;
import prr.core.exception.DestinationTerminalException;


public class BusyState extends TerminalState {
    public BusyState(Terminal terminal){
        super(terminal);
    }

    public boolean setOnIdle(){
        if(_terminal.getOngoingCommunication() == null) {
            _terminal.setTerminalState(new IdleState(_terminal));
        }
        return true;
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
        throw new DestinationTerminalException("BUSY");
    }

    public Communication makeVideoCall(Terminal to) throws DestinationTerminalException {
       return null;
    }


    public void acceptVideoCall(Communication communication) throws DestinationTerminalException {
        throw new DestinationTerminalException("OFF");
    }

    public void unBusy() {}

    public boolean canEndCurrentCommunication() {
        return _terminal.getOngoingCommunication().getFrom().equals(_terminal);
    }
    public boolean canStartCommunication() {return false;}
}
