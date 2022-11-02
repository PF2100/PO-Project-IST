package prr.core;

import java.io.Serializable;

public abstract class Communication implements Serializable {
    private static final long serialVersionUID = 202208091753L;
    private int _id ;
    private boolean _isPaid;
    protected double _cost;
    private boolean _isOngoing;

    private Terminal _from;

    private Terminal _to;

    public Communication(Terminal from, Terminal to) {
        _from = from;
        _to = to;
        _isPaid = false;
    }

    public int getId() {return _id;}

    public Terminal getFrom() {return _from;}

    public Terminal getTo() {return _to;}

    public boolean isPaid() {return _isPaid;}

    public void setId(int id) {_id = id;}

    public boolean isOngoing() {return _isOngoing;}

    public double getCost() {return _cost;}

    public abstract String toString();

    protected void setCost(double cost) {
        _cost = cost;
    }

    protected abstract void calculateCost(ClientState state);
    // falta colocar o tarif plan como argumento ?

    protected abstract int getSize();

    @Override
    public boolean equals(Object other) {
        if(other instanceof Terminal ){
            return this.getId() == ((Communication)other).getId() ;
        }
        return false;
    }
    
    void setPaid() {
        _isPaid = true;
    }

}
