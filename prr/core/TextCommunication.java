package prr.core;

public class TextCommunication extends Communication {
    private String _message;

    public TextCommunication(String message) {
        super();
        _message = message;
    }

    public String getMessage() {
        return _message;
    }

    @Override
    protected double computeCost() {
        // falta implementar e ver aquilo do tarif plan
        return 0;
    }

    @Override
    protected int getSize() {
        return _message.length();
    }
}
