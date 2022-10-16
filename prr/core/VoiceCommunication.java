package prr.core;

public class VoiceCommunication extends InteractiveCommunication {

    public VoiceCommunication(int duration) {
        super(duration);
    }

    @Override
    protected double computeCost() {
        return 0;
        //falta implementar
    }
}
