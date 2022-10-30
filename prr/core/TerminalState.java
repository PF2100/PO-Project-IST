package prr.core;

import java.io.Serializable;

public abstract class TerminalState implements Serializable{
    protected Terminal _terminal;
    public TerminalState(Terminal terminal) {
        _terminal = terminal;
    }

    public abstract boolean setOnIdle(Terminal terminal); 
    public abstract boolean setBusy(Terminal terminal);
    public abstract boolean setOnSilent(Terminal terminal);
    public abstract boolean turnOff(Terminal terminal);
    public abstract boolean makeSms();
    public abstract boolean acceptSms();
    public abstract boolean makeVoiceCall();
    public abstract boolean acceptVoiceCall();
    public abstract boolean canEndCurrentCommunication();
    public abstract boolean canStartCommunication();
    public abstract String toString();

}
