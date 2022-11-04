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
        _terminal.setTerminalState(new BusyState(_terminal,_terminal.getState()));
        return true;
    }

    public boolean turnOff() {
        _terminal.setTerminalState(new OffState(_terminal));
        return true;
    }
    
    public boolean setOnSilent() {
        return false;
    }
    
    public Communication makeSms(Terminal to, String message) throws DestinationTerminalException {
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

    public void acceptVoiceCall(Communication communication) throws DestinationTerminalException{
        throw new DestinationTerminalException("SILENCE");
    }

    public Communication makeVideoCall(Terminal to) throws DestinationTerminalException {
        InteractiveCommunication communication = new VideoCommunication(_terminal,to);
        to.acceptVideoCall(communication);
        _terminal.addMadeCommunication(communication);
        _terminal.setOngoingCommunication(communication);
        communication.setOngoing();
        _terminal.setBusy();
        return communication;
    }


    public void acceptVideoCall(Communication communication) throws DestinationTerminalException {
        throw new DestinationTerminalException("SILENCE");
    }

    public void unBusy() {
        _terminal.setTerminalState(this);
    }

    public boolean canEndCurrentCommunication() {return false;}
    public boolean canStartCommunication() {return true;}
    
}
