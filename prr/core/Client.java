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

    private String _key; // Client's unique key
    private String _name;
    private int _taxNumber;
    private ClientLevel _level; // Client's Tariff Level
    private boolean _receiveNotifications;
    // Plano tarifario se implementar
    private Map<String,Terminal> _terminals;


    public Client(String name, String key, int taxNumber) {
        _key = key;
        _name = name;
        _taxNumber = taxNumber;
        _level = ClientLevel.NORMAL;
        _receiveNotifications = true;
        _terminals = new HashMap<>();
    }

    public String getKey() {
        return _key;
    }

    public String getName() {
        return _name;
    }

    public int getTaxNumber() {
        return _taxNumber;
    }

    public void turnNotificationsOn() {
        _receiveNotifications = true;
    }

    public void turnNotificationsOff() {
        _receiveNotifications = false;
    }

    public void addTerminal(Terminal terminal) {
        _terminals.put(terminal.getId(),terminal);
    }

    public List<Terminal> getTerminals () {                //Returns a list of all the client's terminals using the HashMap _terminals
        List <Terminal> terminals = new ArrayList<>();     
        for ( String terminalID : _terminals.keySet()) {
            terminals.add(_terminals.get(terminalID));
        }
        return terminals;
    }

    public void alterClientLevel(String clientLevel) {     //Alters the clientLevel using a String
        _level = ClientLevel.valueOf(clientLevel);
    }


    public String toString() {
        String notifications;
        if (_receiveNotifications) {
            notifications = "YES";
        }
        else {notifications = "NO";}

        return "CLIENT|"+ _key +"|"+_name+"|" +_taxNumber + "|" + _level +"|" + notifications +"|"
                + _terminals.size() + "|" + Math.round(getPayments()) + "|" + Math.round(getDebts());

    }

    public double getDebts() {  //Iterates through the hashMap of terminals to obtain all of their debt values
        double debt = 0;
        for ( String terminalID : _terminals.keySet()) {     
            Terminal terminal = _terminals.get(terminalID);
            debt += terminal.getDebt();
        }
        return debt;
    }

    public double getPayments() { //Iterates through the hashMap of terminals to obtain all of their payment values
        double payments = 0;
        for ( String terminalID : _terminals.keySet()) {
            Terminal terminal = _terminals.get(terminalID);
            payments += terminal.getPayments();
        }
        return payments;
    } 
}
