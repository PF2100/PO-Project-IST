package prr.core;

import prr.core.Terminal;
import prr.core.exception.DestinationTerminalException;


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
        return false;
    }
    
    public boolean setOnSilent() {
        if(_terminal.getOngoingCommunication() == null) {
            _terminal.setTerminalState(new SilentState(_terminal));
        }
        return true;
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

    public boolean acceptSms(Terminal from) throws DestinationTerminalException {
        return true;
    }
    
    public boolean makeVoiceCall() {return true;}
    public boolean acceptVoiceCall() {return false;}
    public boolean canEndCurrentCommunication() {
        return _terminal.getOngoingCommunication().getFrom().equals(_terminal);
    }
    public boolean canStartCommunication() {return false;}
}
