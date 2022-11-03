package prr.core;

import java.io.Serializable;

public abstract class InteractiveCommunication extends Communication implements Serializable {
    private static final long serialVersionUID = 202208091753L;
    protected int _duration;

    public InteractiveCommunication(Terminal from, Terminal to) {
        super(from, to);
    }

    @Override
    protected int getUnits() {
        return _duration;
    } 

}
