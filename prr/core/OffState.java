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
    
    

    public TextCommunication makeSms(Terminal to, String message) throws DestinationTerminalException {return null;}

    public boolean acceptSms(Terminal from) throws DestinationTerminalException {
        throw new DestinationTerminalException(_terminal.getId());
    }
    public boolean makeVoiceCall() {return false;}
    public boolean acceptVoiceCall() {return false;}
    public boolean canEndCurrentCommunication() {return false;}
    public boolean canStartCommunication() {return false;}
}
