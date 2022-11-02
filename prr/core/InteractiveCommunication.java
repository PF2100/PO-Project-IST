package prr.core;

import java.io.Serializable;

public abstract class InteractiveCommunication extends Communication implements Serializable {
    private static final long serialVersionUID = 202208091753L;
    protected int _duration;

    public InteractiveCommunication(Terminal from, Terminal to, int duration) {
        super(from, to);
        _duration = duration;
    }

    @Override
    protected int getSize() {
        return _duration;
    } 

    @Override
    protected abstract void calculateCost(ClientState state);
}
