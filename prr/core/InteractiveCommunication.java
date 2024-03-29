package prr.core;

import java.io.Serializable;

public abstract class InteractiveCommunication extends Communication {
    private static final long serialVersionUID = 202208091753L;
    protected int _duration;

    public InteractiveCommunication(Terminal from, Terminal to) {
        super(from, to);
    }

    public void setDuration(int duration) {
        _duration = duration;
    }
    
    public double endCommunication(int duration){
        setDuration(duration);
        calculateCost();
        stopOngoing();
        return getCost();
    }

    @Override
    public abstract String toString();

    @Override
    protected int getUnits() {
        return _duration;
    } 

}
