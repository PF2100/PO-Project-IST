package prr.core;

import prr.core.TextCommunication;
import prr.core.VoiceCommunication;
import prr.core.VideoCommunication;
import prr.core.SilentState;
import prr.core.Terminal;
import prr.core.exception.*;

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

    public TextCommunication makeSms(Terminal to, String message) throws DestinationTerminalException { //Mudar as funções
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
    public boolean acceptVoiceCall() {return true;}
    public boolean canEndCurrentCommunication() {return false;}

    public boolean canStartCommunication() {return true;}

}
