package prr.core;

import prr.core.SilentState;

public class IdleState extends TerminalState{
    public IdleState(Terminal terminal){
        super(terminal);
    }

    public boolean setOnIdle(Terminal terminal){
        return false;
    }

    public boolean setBusy(Terminal terminal) {
        terminal.setTerminalState(new BusyState(terminal));
        return true;
    }

    public String toString(){
        return "IDLE";
    }
    public boolean turnOff(Terminal terminal) {
        terminal.setTerminalState(new OffState(terminal));
        return true;
    }
    
    public boolean setOnSilent(Terminal terminal) {
        terminal.setTerminalState(new SilentState(terminal));
        return true;
    }

    public boolean makeSms() {return true;}
    public boolean acceptSms() {return true;}
    public boolean makeVoiceCall() {return true;}
    public boolean acceptVoiceCall() {return true;}
    public boolean canEndCurrentCommunication() {return true;}
    public boolean canStartCommunication() {return true;}

}
