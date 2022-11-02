package prr.core;

import prr.core.Terminal;
import prr.core.exception.DestinationTerminalException;


public class SilentState extends TerminalState{

    public SilentState(Terminal terminal){
        super(terminal);
    }

    public boolean setOnIdle() {
        _terminal.setTerminalState(new IdleState(_terminal));
        return true;
    }

    public String toString() {
        return "SILENCE";
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
    
    public TextCommunication makeSms(Terminal to, String message) throws DestinationTerminalException {
        TextCommunication communication = null;
        if (to.acceptSms(_terminal)) {
            communication = new TextCommunication(_terminal,to,message); //Se calhar arranjar um método que trata logo disto
            _terminal.addMadeCommunications(communication);
            to.addReceivedCommunications(communication);
        }
        return communication;
    }

    public boolean acceptSms(Terminal from) throws DestinationTerminalException{return true;}
    public boolean makeVoiceCall() {return true;}
    public boolean acceptVoiceCall() {return false;}
    public boolean canEndCurrentCommunication() {return false;}
    public boolean canStartCommunication() {return true;}
    
}
