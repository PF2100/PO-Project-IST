package prr.core;

public class GoldState extends ClientState {
    protected Client _client;
    public GoldState(Client client) {
        super(client);
    }

    public  void calculateTextCost(Communication communication){
        int units = communication.getUnits();
        double price = 0d;
        if ( units < 100) {
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

    public String toString() {
        return "GOLD";
    }
}
