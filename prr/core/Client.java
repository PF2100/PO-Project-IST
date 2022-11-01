package prr.core;

import java.util.*;
import java.io.Serializable;

import prr.core.Terminal;


enum ClientLevel {
    NORMAL,
    GOLD,
    PLATINUM
}


public class Client implements Serializable {

    private static final long serialVersionUID = 202208091753L;

    private final String _key; // Client's unique key
    private String _name;
    private int _taxNumber;
    private ClientLevel _level; // Client's Tariff Level
    private boolean _receiveNotifications; //Client notification settings
    private Map<String,Terminal> _terminals;


    public Client(String key, String name, int taxNumber) {
        _key = key;
        _name = name;
        _taxNumber = taxNumber;
        _level = ClientLevel.NORMAL;
        _receiveNotifications = true;
        _terminals = new HashMap<>();
    }

    public String getKey() {return _key;}

    public String getName() {return _name;}

    public int getTaxNumber() {return _taxNumber;}

    public boolean turnNotificationsOn() {
        if( _receiveNotifications) {
            return false;
        }
        _receiveNotifications = true;
        return true;
    } /*Falta aplicar lógica de notificações */

    public boolean turnNotificationsOff() {
        if( !_receiveNotifications) {
            return false;
        }
        _receiveNotifications = false;
        return true;
    } /*Falta aplicar lógica de notificações */

    public void addTerminal(Terminal terminal) {_terminals.put(terminal.getId(),terminal);}



    //Returns a list of all the client's terminals 
    public List<Terminal> getTerminals () {   
        return new ArrayList<Terminal>(_terminals.values());
    }

    //Alters the clientLevel to the Input
    public void alterClientLevel(String clientLevel) {_level = ClientLevel.valueOf(clientLevel);}


    public String toString() {
        String notifications = "YES";
        if ( !_receiveNotifications) {notifications = "NO";} // if the client does not have the notification on the string is "NO"

        return "CLIENT|"+ _key +"|"+_name+"|" +_taxNumber + "|" + _level +"|" + notifications +"|"
                + _terminals.size() + "|" + Math.round(getPayments()) + "|" + Math.round(getDebts());
    }


    //Iterates through the terminals to obtain all of their debt values
    public double getDebts() {  
        double debt = 0;
        for ( Terminal terminal : _terminals.values()) {     //FIXMEEE 
            debt += terminal.getDebt();
        }
        return debt;
    }
    
    //Iterates through the terminals to obtain all of their payment values, 
    public double getPayments() { 
        double payments = 0;
        for ( Terminal terminal : _terminals.values()) { //FIXMEEE
            payments += terminal.getPayments();
        }
        return payments;
    }

    public Collection<Communication> getMadeCommunications() {
        Collection<Communication> madeCommunications = new ArrayList<>();
        for (Terminal terminal : _terminals.values()) {
            Collection<Communication> communications = terminal.getMadeCommunications();
            if(communications != null) {
                madeCommunications.addAll(communications);
            }
        }
        return madeCommunications;
    }

    public Collection<Communication> getReceivedCommunications() {
        Collection<Communication> receivedCommunications = new ArrayList<>();
        for (Terminal terminal : _terminals.values()) {
            Collection<Communication> communications = terminal.getReceivedCommunications();
            if(communications != null) {
                receivedCommunications.addAll(communications);
            }
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

    @Override
    public int hashCode() {
        return _key.hashCode();
    }
}