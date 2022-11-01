package prr.core;

import java.io.Serializable;

public abstract class TerminalState implements Serializable{
    protected Terminal _terminal;
    public TerminalState(Terminal terminal) {
        _terminal = terminal;
    }

    public abstract boolean setOnIdle(); 
    public abstract boolean setBusy();
    public abstract boolean setOnSilent();
    public abstract boolean turnOff();

    public abstract Communication makeSms(Terminal to, String message);
    public abstract boolean acceptSms(Terminal from, String message);
    public abstract boolean makeVoiceCall();
    public abstract boolean acceptVoiceCall();
    public abstract boolean canEndCurrentCommunication();
    public abstract boolean canStartCommunication();
    public abstract String toString();

}
