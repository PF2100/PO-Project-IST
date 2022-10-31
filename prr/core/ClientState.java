package prr.core;

import java.io.Serializable;

public abstract class ClientState implements Serializable{
    protected Client _client;

    public ClientState(Client client) {
        _client = client;
    }

    public abstract int calculateCosts();

}

