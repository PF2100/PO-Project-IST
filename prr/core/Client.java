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

    private static final long serialVersionUID = 202210141616L;

    private String _key; // Client's unique key
    private String _name;
    private int _taxNumber;
    private ClientLevel _level; // Client's Tariff Level
    private boolean _receiveNotifications;
    // Plano tarifario se implementar
    private Map<String,Terminal> _terminals;
 
    public Client(String name, String key , int taxNumber) {
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

    public String getTaxNumber() {
        return _taxNumber;
    }

    public void turnNotificationsOn() {
        _receiveNotifications = true;
    }

    public void turnNotificationsOff() {
        _receiveNotifications = false;
    }

    public List<Terminal> getTerminals () {  //Returns a list of all the client's terminals using the HashMap _terminals
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
        return 

    }

    //calculateDebt()
    //calculatePayments()




    

    

    



    
}
