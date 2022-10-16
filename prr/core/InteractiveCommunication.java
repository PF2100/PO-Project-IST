package prr.core;

public abstract class InteractiveCommunication extends Communication {
    private int _duration;

    public InteractiveCommunication(Terminal from, Terminal to, int duration) {
        super(from, to);
        _duration = duration;
    }

    @Override
    protected int getSize() {
        return _duration;
    }
}
