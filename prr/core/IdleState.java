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
        _terminal.setTerminalState(new BusyState(_terminal,_terminal.getState()));
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

    public Communication makeSms(Terminal to, String message) throws DestinationTerminalException { //Mudar as funções
        Communication communication = new TextCommunication(_terminal,to,message);
        to.acceptSms(communication);
        _terminal.addMadeCommunication(communication);
        return communication;
    }

    public void acceptSms(Communication communication) throws DestinationTerminalException {
        _terminal.addReceivedCommunication(communication);
    }

    public Communication makeVoiceCall(Terminal to) throws DestinationTerminalException {
        Communication communication = new VoiceCommunication(_terminal,to);
        to.acceptVoiceCall(communication);
        _terminal.addMadeCommunication(communication);
        _terminal.setOngoingCommunication(communication);
        communication.setOngoing();
        _terminal.setBusy();
        return communication;
    }


    public void acceptVoiceCall(Communication communication) throws DestinationTerminalException {
        _terminal.addReceivedCommunication(communication);
        _terminal.setOngoingCommunication(communication);
        _terminal.setBusy();
    }

    public Communication makeVideoCall(Terminal to) throws DestinationTerminalException {
        Communication communication = new VoiceCommunication(_terminal,to);
        to.acceptVideoCall(communication);
        _terminal.addMadeCommunication(communication);
        _terminal.setOngoingCommunication(communication);
        communication.setOngoing();
        _terminal.setBusy();
        return communication;
    }


    public void acceptVideoCall(Communication communication) throws DestinationTerminalException {
        _terminal.addReceivedCommunication(communication);
        _terminal.setOngoingCommunication(communication);
        _terminal.setBusy();
    }

    public void unBusy() {
        _terminal.setTerminalState(this);
    }

    public boolean canEndCurrentCommunication() {return false;}
    public boolean canStartCommunication() {return true;}

}
