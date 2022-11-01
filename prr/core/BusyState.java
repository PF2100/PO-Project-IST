package prr.core;

import prr.core.Terminal;


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
        return true;
    }
    
    public boolean setOnSilent() {
        if(_terminal.getOngoingCommunication() == null) {
            _terminal.setTerminalState(new SilentState(_terminal));
        }
        return true;
    }


    
    public TextCommunication makeSms(Terminal to, String message) {
        return null;
    }
    public boolean acceptSms(Terminal from) {return true;}
    
    public boolean makeVoiceCall() {return true;}
    public boolean acceptVoiceCall() {return true;}
    public boolean canEndCurrentCommunication() {return true;}
    public boolean canStartCommunication() {return true;}
}
