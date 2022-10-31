package prr.core;

public abstract class PlatinumState extends ClientState {
    protected Client _client;
    public PlatinumState(Client client) {
        super(client);
    }

    public abstract int calculateCosts();

}