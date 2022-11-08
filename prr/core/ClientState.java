package prr.core;

import java.io.Serializable;

public abstract class ClientState implements Serializable{
    protected Client _client;
    protected int _consecutiveCommunications;

    public ClientState(Client client) {
        _client = client;
    }

    public void checkUpdates(CommunicationType type){
        CommunicationType previous = _client.getPreviousType();
        if(previous != null && type.equals(_client.getPreviousType())){
            _consecutiveCommunications +=1;
        }
        else{
            _consecutiveCommunications = 1;
            _client.setPreviousType(type);
        }
        upgradeClient(type);
        downgradeClient(type);
    }
    

    public abstract void upgradeClient(CommunicationType type);
    public abstract void downgradeClient(CommunicationType type);
    
    public abstract String toString();

    public abstract void calculateTextCost(Communication communication);
    public abstract void calculateVideoCost(Communication communication);
    public abstract void calculateVoiceCost(Communication communication);


}

