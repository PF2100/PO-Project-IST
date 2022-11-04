package prr.core;

import java.io.Serializable;
import prr.core.*;

public abstract class InteractiveCommunication extends Communication implements Serializable {
    private static final long serialVersionUID = 202208091753L;
    protected int _duration;

    public InteractiveCommunication(Terminal from, Terminal to) {
        super(from, to);
    }

    public void setDuration(int duration) {
        _duration = duration;
    }
    
    @Override
    public abstract String toString();

    @Override
    protected int getUnits() {
        return _duration;
    } 

}
