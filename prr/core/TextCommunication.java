package prr.core;

public class TextCommunication extends Communication {
    private String _message;

    public TextCommunication(Terminal from, Terminal to, String message) {
        super(from, to);
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

    @Override
    public String toString() {
        return "TEXT|"+ getId() +"|"+getFrom().getId()+"|" +getTo().getId() + "|"
                + this.getSize() +"|" + getCost() + "|" + "FINISHED";
    }
}
