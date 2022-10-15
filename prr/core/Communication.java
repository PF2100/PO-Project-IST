package prr.core;

public abstract class Communication {
    private int _id;
    private boolean _isPaid;
    private double _cost;
    private boolean _isOngoing;

    public Communication() {
        _id = 0; // falta corrigir
        _isPaid = false;

    }

    public int getId() {
        return _id;
    }

    public boolean isPaid() {
        return _isPaid;
    }

    public boolean isOngoing() {
        return _isOngoing;
    }

    public double getCost() {
        return _cost;
    }

    public String toString() {
        // falta implementar
        return "oi";
    }

    protected abstract double computeCost ();
    // falta colocar o tarif plan como argumento ?

    protected abstract int getSize();
}
