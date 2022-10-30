package prr.core;

import prr.core.Terminal;


public class SilentState extends TerminalState{

    public SilentState(Terminal terminal){
        super(terminal);
    }

    public boolean setOnIdle(Terminal terminal) {
        terminal.setTerminalState(new IdleState(terminal));
        return true;
    }

    public String toString() {
        return "SILENT";
    }
    
    public boolean setBusy(Terminal terminal) {
        terminal.setTerminalState(new BusyState(terminal));
        return true;
    }

    public boolean turnOff(Terminal terminal) {
        terminal.setTerminalState(new OffState(terminal));
        return true;
    }
    
    public boolean setOnSilent(Terminal terminal) {
        return false;
    }
    
    public boolean makeSms() {return true;}
    public boolean acceptSms() {return true;}
    public boolean makeVoiceCall() {return true;}
    public boolean acceptVoiceCall() {return true;}
    public boolean canEndCurrentCommunication() {return true;}
    public boolean canStartCommunication() {return true;}
}
