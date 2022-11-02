package prr.core;

public class PlatinumState extends ClientState {
    protected Client _client;
    public PlatinumState(Client client) {
        super(client);
    }

    public  double calculateTextCost(int length){
        return 0.0;
    }
    public  double calculateVideoCost(int duration){
        return 0.0;
    }
    public  double calculateVoiceCost(int duration){
        return 0.0;
    }

    public String toString() {
        return "PLATINUM";
    }
}