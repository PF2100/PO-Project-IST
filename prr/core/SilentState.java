package prr.core;

import prr.core.Terminal;


public class SilentState extends TerminalState{

    public SilentState(Terminal terminal){
        super(terminal);
    }

    public boolean setOnIdle() {
        _terminal.setTerminalState(new IdleState(_terminal));
        return true;
    }

    public String toString() {
        return "SILENT";
    }
    
    public boolean setBusy() {
        _terminal.setTerminalState(new BusyState(_terminal));
        return true;
    }

    public boolean turnOff() {
        _terminal.setTerminalState(new OffState(_terminal));
        return true;
    }
    
    public boolean setOnSilent() {
        return false;
    }
    
    public TextCommunication makeSms(Terminal to, String message) {return null;}
    public boolean acceptSms(Terminal from, String message) {return true;}
    public boolean makeVoiceCall() {return true;}
    public boolean acceptVoiceCall() {return true;}
    public boolean canEndCurrentCommunication() {return true;}
    public boolean canStartCommunication() {return true;}
}
