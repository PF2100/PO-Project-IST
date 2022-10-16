package prr.core;

public abstract class InteractiveCommunication extends Communication {

    private int _duration;

    public InteractiveCommunication(int duration) {
        super();
        _duration = duration;
    }

    @Override
    protected int getSize() {
        return _duration;
    }
}
