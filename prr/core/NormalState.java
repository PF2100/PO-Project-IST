package prr.core;

public class NormalState extends ClientState {
    protected Client _client;
    public NormalState(Client client) {
        super(client);
    }

    

    public  void calculateTextCost(Communication communication){
        int units = communication.getUnits();
        double price = 0.0;
        if ( units < 50) {
            price = 10 ;
        }
        else if ( 50 <= units  && units < 100) {
            price = 16;
        }
        else {
            price = 2 * units ;
        }
        communication.setCost(price * communication.getDiscount());
    }

    public  void calculateVideoCost(Communication communication){
        int units = communication.getUnits();
        double price = units * 30 ;
        communication.setCost(price * communication.getDiscount());
    }
    public  void calculateVoiceCost(Communication communication){
        int units = communication.getUnits();
        double price = units * 20 ;
        communication.setCost(price * communication.getDiscount());
    }

    public String toString() {
        return "NORMAL";
    }

    public void CheckUpdate(Communication communication) {
        
    }

}
