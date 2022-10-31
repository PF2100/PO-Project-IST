package prr.core;

public abstract class NormalState extends ClientState {
    protected Client _client;
    public NormalState(Client client) {
        super(client);
    }

    public abstract int calculateCosts();

}
