package prr.core;

public abstract class GoldState extends ClientState {
    protected Client _client;
    public GoldState(Client client) {
        super(client);
    }

    public abstract int calculateCosts();

}
