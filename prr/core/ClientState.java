package prr.core;

import java.io.Serializable;

public abstract class ClientState implements Serializable{
    protected Client _client;

    public ClientState(Client client) {
        _client = client;
    }

    //public abstract void payCommunication() 
    
    public abstract String toString();

    public abstract double calculateTextCost(int length);
    public abstract double calculateVideoCost(int duration);
    public abstract double calculateVoiceCost(int duration);


}

