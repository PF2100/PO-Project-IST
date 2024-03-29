package prr.core;

import java.io.Serializable;

    enum CommunicationType{
        TEXT,
        VOICE,
        VIDEO;
    }

public abstract class Communication implements Serializable {
    private static final long serialVersionUID = 202208091753L;
    private int _id ;
    private boolean _isPaid;
    protected double _cost;
    private boolean _isOngoing;
    private double _discount = 1;
    private CommunicationType _type;

    private Terminal _from;

    private Terminal _to;

    public Communication(Terminal from, Terminal to) {
        _from = from;
        _to = to;
        _isPaid = false;
        if ( from.isFriend(to) ) {
            _discount = 0.5;
        }
    }

    public int getId() {return _id;}

    public double getDiscount() {
        return _discount;
    }

    public Terminal getFrom() {return _from;}

    public Terminal getTo() {return _to;}

    public boolean isPaid() {return _isPaid;}

    public void setId(int id) {_id = id;}

    public boolean isOngoing() {return _isOngoing;}

    public void setOngoing() {_isOngoing = true;}

    public void stopOngoing() { _isOngoing = false;}

    public double getCost() {return _cost;}

    public Client getClientOwner() {return getFrom().getOwner();}

    public abstract void calculateCost();

    public abstract String toString();

    public void setCommunicationType(CommunicationType type){
        _type = type;
    }

    public CommunicationType getType(){
        return _type;
    }

    protected void setCost(double cost) {
        _cost = cost;
    }

    protected abstract int getUnits();


    @Override
    public boolean equals(Object other) {
        if(other instanceof Terminal ){
            return this.getId() == ((Communication)other).getId() ;
        }
        return false;
    }

    void payCommunication() {
        _isPaid = true;
        getClientOwner().checkUpdates(getType());
    }

}
