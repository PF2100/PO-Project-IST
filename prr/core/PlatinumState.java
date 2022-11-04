package prr.core;

public class PlatinumState extends ClientState {
    public PlatinumState(Client client) {
        super(client);
    }

    public  void calculateTextCost(Communication communication){
        int units = communication.getUnits();
        double price = 0d;
        if (units < 50) {
            price = 0 ;
        }
        else {
            price = 4;
        }
        communication.setCost(price * communication.getDiscount());
    }

    public  void calculateVideoCost(Communication communication){
        int units = communication.getUnits();
        double price = units * 30d ;
        communication.setCost(price * communication.getDiscount());
    }
    public  void calculateVoiceCost(Communication communication){
        int units = communication.getUnits();
        double price = units * 20d ;
        communication.setCost(price * communication.getDiscount());
    }


    public void upgradeClient(CommunicationType type){}
    
    public void downgradeClient(CommunicationType type) {
        if(_client.getBalance() < 0){
            _client.setClientState(new NormalState(_client));
        }
        else if(_consecutiveCommunications == 2 && _client.getPreviousType().equals(CommunicationType.TEXT) && _client.getBalance() > 0){
            _client.setClientState(new GoldState(_client));
        }
    }



    public String toString() {
        return "PLATINUM";
    }
}