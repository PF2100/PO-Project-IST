package prr.core;

import java.io.Serializable;

public abstract class ClientState implements Serializable{
    protected Client _client;

    public ClientState(Client client) {
        _client = client;
    }

    //public abstract void payCommunication() 
    
    public abstract String toString();

    public abstract void calculateTextCost(Communication communication);
    public abstract void calculateVideoCost(Communication communication);
    public abstract void calculateVoiceCost(Communication communication);


}

