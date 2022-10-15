package prr.core;

public class VideoCommunication extends InteractiveCommunication {

    public VideoCommunication(int duration) {
        super(duration);
    }

    @Override
    protected double computeCost() {
        return 0;
        //falta implementar
    }
}
