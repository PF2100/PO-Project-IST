package prr.core;


public class DefaultBehaviour implements NotificationBehaviour{

    public void notifyClient(Notification notification,Client client){
        client.addNotification(notification);
    }
}
