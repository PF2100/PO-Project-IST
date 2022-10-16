package prr.core;

public abstract class Communication {
    private int _id;
    private boolean _isPaid;
    private double _cost;
    private boolean _isOngoing;
    private static int _nComms;

    private Terminal _from;

    private Terminal _to;

    public Communication(Terminal from, Terminal to) {
        _id = _nComms;
        _from = from;
        _to = to;
        _nComms += 1;
        _isPaid = false;
    }

    public int getId() {
        return _id;
    }

    public Terminal getFrom() {return _from;}

    public Terminal getTo() {return _to;}

    public boolean isPaid() {
        return _isPaid;
    }

    public boolean isOngoing() {
        return _isOngoing;
    }

    public double getCost() {
        return _cost;
    }

    public abstract String toString();

    protected abstract double computeCost ();
    // falta colocar o tarif plan como argumento ?

    protected abstract int getSize();
}
