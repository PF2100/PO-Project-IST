package prr.core;

import prr.core.Terminal;

public class OffState extends TerminalState {
    public OffState(Terminal terminal){
        super(terminal);
    }

    public boolean setOnIdle(Terminal terminal){
        terminal.setTerminalState(new IdleState(terminal));
        return true;
    }

    public String toString() {
        return "OFF";
    }
    

    public boolean setBusy(Terminal terminal) {
        return true;
    }

    public boolean turnOff(Terminal terminal) {
        return false;
    }
    
    public boolean setOnSilent(Terminal terminal) {
        return true;
    }
    
    

    public boolean makeSms() {return true;}
    public boolean acceptSms() {return true;}
    public boolean makeVoiceCall() {return true;}
    public boolean acceptVoiceCall() {return true;}
    public boolean canEndCurrentCommunication() {return true;}
    public boolean canStartCommunication() {return true;}
}
