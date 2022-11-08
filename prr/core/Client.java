package prr.core;

import java.util.*;
import java.io.Serializable;

public class Client implements Serializable {

    private static final long serialVersionUID = 202208091753L;
    private final String _key; // Client's unique key
    private String _name;
    private int _taxNumber;
    private ClientState _state; // Client's Tariff Level
    private boolean _receiveNotifications; //Client notification settings
    private Map<String,Terminal> _terminals;
    private NotificationBehaviour _notificationBehaviour;
    private List<Notification> _notifications = new ArrayList<>();
    private CommunicationType _previousComm;


    public Client(String key, String name, int taxNumber) {
        _key = key;
        _name = name;
        _taxNumber = taxNumber;
        _state = new NormalState(this);
        _receiveNotifications = true;
        _terminals = new HashMap<>();
        _notificationBehaviour = new DefaultBehaviour();
    }

    public void addNotification(Notification notification){
        if(!_notifications.contains(notification)){
            _notifications.add(notification);
        }
    }

    public String getKey() {return _key;}

    public String getName() {return _name;}

    public int getTaxNumber() {return _taxNumber;}

    public boolean canReceiveNotifications(){return _receiveNotifications;};

    public void getNotification(Terminal terminal, String message){
        Notification notification = new Notification(terminal, message);
        _notificationBehaviour.notifyClient(notification,this);
    }

    public boolean turnNotificationsOn() {
        if( _receiveNotifications) {
            return false;
        }
        _receiveNotifications = true;
        return true;
    }

    public boolean turnNotificationsOff() {
        if( !_receiveNotifications) {
            return false;
        }
        _receiveNotifications = false;
        return true;
    }

    public void addTerminal(Terminal terminal) {_terminals.put(terminal.getId(),terminal);}

    //Returns a list of all the client's terminals 
    public List<Terminal> getTerminals () {   
        return new ArrayList<Terminal>(_terminals.values());
    }

    //Alters the clientLevel to the Input
    public void setClientState(ClientState state) {
        _state = state;
    }

    public String toString() {
        String notifications = "YES";
        if ( !_receiveNotifications) {notifications = "NO";} // if the client does not have the notification on the string is "NO"

        return "CLIENT|"+ _key +"|"+_name+"|" +_taxNumber + "|" + _state.toString() +"|" + notifications +"|"
                + _terminals.size() + "|" + Math.round(getPayments()) + "|" + Math.round(getDebts());
    }

    public List<Notification> getNotifications(){
        return new ArrayList<>(_notifications);
    }
    public void clearNotifications(){
        _notifications.clear();
    }

    public Collection<Communication> getMadeCommunications() {
        Collection<Communication> madeCommunications = new ArrayList<>();
        for (Terminal terminal : _terminals.values()) {
            Collection<Communication> communications = terminal.getMadeCommunications();
            madeCommunications.addAll(communications);
        }
        return madeCommunications;
    }

    public Collection<Communication> getReceivedCommunications() {
        Collection<Communication> receivedCommunications = new ArrayList<>();
        for (Terminal terminal : _terminals.values()) {
            Collection<Communication> communications = terminal.getReceivedCommunications();
            receivedCommunications.addAll(communications);
        }
        return receivedCommunications;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Client ){
            return (this.getKey().compareToIgnoreCase( ((Client)other).getKey())) == 0;
        }
        return false;
    }

    void calculateTextCost(Communication communication) {
        _state.calculateTextCost(communication);
    }

    void calculateVoiceCost(Communication communication) {
        _state.calculateVoiceCost(communication);
    }

    void calculateVideoCost(Communication communication) {
        _state.calculateVideoCost(communication);
    }

    public double getDebts(){
        double debt = 0;
        for(Terminal terminal : _terminals.values()) {
            debt += terminal.getDebts();
        }
        return debt;
    }

    public double getPayments(){
        double payments = 0;
        for(Terminal terminal : _terminals.values()) {
            payments += terminal.getPayments();
        }
        return payments;
    }

    public double getBalance() {
        return getPayments() - getDebts(); 
    }

    @Override
    public int hashCode() {
        return _key.hashCode();
    }

    public void checkUpdates(CommunicationType type){
        _state.checkUpdates(type);
    }

    public void setCommunicationType(CommunicationType type){
        _previousComm = type;
    }
    public CommunicationType getPreviousType(){
        return _previousComm;
    }
    public void setPreviousType(CommunicationType type){
        _previousComm = type;
    }
}