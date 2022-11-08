package prr.core;

import prr.core.NormalState;
import prr.core.PlatinumState;

public class GoldState extends ClientState {
    public GoldState(Client client) {
        super(client);
    }

    public  void calculateTextCost(Communication communication){
        int units = communication.getUnits();
        double price = 0d;
        if (units < 100) {
            price = 10;
        }
        else {
            price = 2d * units ;
        }
        communication.setCost(price * communication.getDiscount());
    }

    public  void calculateVideoCost(Communication communication){
        int units = communication.getUnits();
        double price = units * 20d ;
        communication.setCost(price * communication.getDiscount());
    }
    public  void calculateVoiceCost(Communication communication){
        int units = communication.getUnits();
        double price = units * 10d ;
        communication.setCost(price * communication.getDiscount());
    }

    public void upgradeClient(CommunicationType type){
        if(_consecutiveCommunications == 5 && _client.getPreviousType().equals(CommunicationType.VIDEO) && _client.getBalance() > 0) {
            _client.setClientState(new PlatinumState(_client));
        }
    }
    
    public void downgradeClient(CommunicationType type) {
        if(_client.getBalance() < 0){
            _client.setClientState(new NormalState(_client));
        }
    }

    public String toString() {
        return "GOLD";
    }
}
