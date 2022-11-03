package prr.core;

public class PlatinumState extends ClientState {
    protected Client _client;
    public PlatinumState(Client client) {
        super(client);
    }

    public  void calculateTextCost(Communication communication){
        int units = communication.getUnits();
        double price = 0d;
        if ( units < 50) {
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

    public String toString() {
        return "PLATINUM";
    }
}