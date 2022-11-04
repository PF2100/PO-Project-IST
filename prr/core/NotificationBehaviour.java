package prr.core;

import java.io.Serializable;

public interface NotificationBehaviour extends Serializable { 
    public abstract void  notifyClient(Notification notification,Client client);
}

