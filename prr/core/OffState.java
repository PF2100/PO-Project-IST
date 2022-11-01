package prr.core;

import prr.core.Terminal;

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
        return true;
    }

    public boolean turnOff() {
        return false;
    }
    
    public boolean setOnSilent() {
        return true;
    }
    
    

    public TextCommunication makeSms(Terminal to, String message) {return null;}
    public boolean acceptSms(Terminal from) {return true;}
    public boolean makeVoiceCall() {return true;}
    public boolean acceptVoiceCall() {return true;}
    public boolean canEndCurrentCommunication() {return true;}
    public boolean canStartCommunication() {return true;}
}
