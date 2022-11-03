package prr.core;

import prr.core.Terminal;
import prr.core.exception.*;

public class OffState extends TerminalState {
    public OffState(Terminal terminal){
        super(terminal);
    }

    public boolean setOnIdle(){
        _terminal.setTerminalState(new IdleState(_terminal));
        return true;
    }

    public String toString() {
        return "OFF";
    }
    

    public boolean setBusy() {
        return false;
    }

    public boolean turnOff() {
        return false;
    }
    
    public boolean setOnSilent() {
        _terminal.setTerminalState(new SilentState(_terminal));
        return true;
    }
    

    public Communication makeSms(Terminal to, String message) throws DestinationTerminalException {
        return null;
    }

    public void acceptSms(Communication communication) throws DestinationTerminalException {
        throw new DestinationTerminalException("OFF");
    }

    public Communication makeVoiceCall(Terminal to) throws DestinationTerminalException{
        return null;
    }

    public void acceptVoiceCall(Communication communication) throws DestinationTerminalException {
        throw new DestinationTerminalException("OFF");
    }

    public Communication makeVideoCall(Terminal to) throws DestinationTerminalException{
        return null;
    }

    public void acceptVideoCall(Communication communication) throws DestinationTerminalException {
        throw new DestinationTerminalException("OFF");
    }

    public void unBusy() {}

    public boolean canEndCurrentCommunication() {return false;}
    public boolean canStartCommunication() {return false;}
}
