package prr.core;

import prr.core.TextCommunication;
import prr.core.VoiceCommunication;
import prr.core.VideoCommunication;
import prr.core.SilentState;
import prr.core.TextCommunication;

public class IdleState extends TerminalState{
    public IdleState(Terminal terminal){
        super(terminal);
    }

    public boolean setOnIdle(){
        return false;
    }

    public boolean setBusy() {
        _terminal.setTerminalState(new BusyState(_terminal));
        return true;
    }

    public String toString(){
        return "IDLE";
    }
    public boolean turnOff() {
        _terminal.setTerminalState(new OffState(_terminal));
        return true;
    }
    
    public boolean setOnSilent() {
        _terminal.setTerminalState(new SilentState(_terminal));
        return true;
    }

    public TextCommunication makeSms(Terminal to, String message) {
        TextCommunication comms = null;
        if (to.acceptSms(_terminal,message)) {
            comms = new TextCommunication(_terminal,to,message);
            _terminal.addMadeCommunications(comms);
        }
        return comms;
    }

    public boolean acceptSms(Terminal from, String message) {
        _terminal.addReceivedCommunications(new TextCommunication(from,_terminal,message));
        return true;
    }

    public boolean makeVoiceCall() {return true;}
    public boolean acceptVoiceCall() {return true;}
    public boolean canEndCurrentCommunication() {return true;}
    public boolean canStartCommunication() {return true;}

}
