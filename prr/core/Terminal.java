package prr.core;

import java.io.Serial;
import java.io.Serializable;

import java.util.regex.Pattern;
import prr.core.exception.*;

import java.util.*;

/*
 *  Enum values that represent the Terminal's Mode
 */

enum TerminalMode {
    BUSY,
    IDLE,
    SILENCE,
    OFF
}

public abstract class Terminal implements Serializable /* FIXME maybe addd more interfaces */ {

    /**
     * Serial number for serialization.
     */
    @Serial
    private static final long serialVersionUID = 202208091753L;

    private final String _id;
    private TerminalMode _mode; 
    private double _debt;
    private double _payments;
    private Map<String, Terminal> _friends;
    private Client _owner;
    private Map<String,Communication> _madeCommunications;
    private Map<String,Communication> _receivedCommunications;
    private InteractiveCommunication _ongoingCommunication;


    public Terminal(String terminalId, Client owner) throws InvalidKeyNumberException {
        if(!(Pattern.matches("[0-9]{6}", terminalId))) {
            throw new InvalidKeyNumberException(terminalId);
        }
        _id = terminalId;
        _debt = 0;
        _payments = 0;
        _owner = owner;
        _mode = TerminalMode.IDLE;
    }


    public String getId() {return _id;}

    public TerminalMode getMode() {return _mode;}

    public double getDebt() {return _debt;}

    public double getPayments() {return _payments;}

    public ArrayList<String> getMadeCommunications() {
        if (_madeCommunications == null) {
            return null;
        }
        return new ArrayList<>(_madeCommunications.keySet());
    }


    public ArrayList<String> getReceivedCommunications() {
        if (_receivedCommunications == null) {
            return null;
        }
        return new ArrayList<>(_receivedCommunications.keySet());
    }

    public Collection<String> getFriends() {return _friends.keySet();}

    public Client getOwner() {return _owner;}


    public void makeSMS(Terminal to, String message) {
        // implementar
    }

    protected void acceptSMS(Terminal from) {
        // implementar
    }

    public void makeVoiceCall(Terminal to) {
        // implementar
    }

    protected void acceptVoiceCall(Terminal from) {
        // implementar
        _mode = TerminalMode.BUSY;
    }

    public void endOngoingCommunication(int size) {
        // implementar 
        this.setOnIdle();
    }

    public boolean setOnIdle() { //Sets the terminal Mode to Idle

        if (_mode == TerminalMode.IDLE) { return false; }
        else {
            _mode = TerminalMode.IDLE;
            return true;
        }}

    public boolean setBusy() {
        if (_mode == TerminalMode.IDLE || _mode == TerminalMode.SILENCE) {
            _mode = TerminalMode.BUSY;
            return true;
        } 
        else {return false;}}

    public boolean setOnSilent() {
        if (_mode == TerminalMode.IDLE || _mode == TerminalMode.BUSY) {
            _mode = TerminalMode.SILENCE;
            return true;
        } 
        else {return false;}}

    public boolean turnOff() {
        if (_mode == TerminalMode.IDLE || _mode == TerminalMode.SILENCE) {
            _mode = TerminalMode.OFF;
            return true;
        } else {
            return false;
        }
    }

    public abstract String toString();
   

    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive communication) and
     * it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {
        return  _mode == TerminalMode.BUSY && _ongoingCommunication != null && _ongoingCommunication.getFrom().getId().equals(_id) ;
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     **/
    public boolean canStartCommunication() {
        if( _mode != TerminalMode.OFF && _mode != TerminalMode.BUSY) {
            return true;
        }
        return false;
        
    }

    public void addFriend(Terminal friend) {
        _friends.put(friend.getId(), friend);
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Terminal ){
            return (this.getId().compareTo( ((Terminal)other).getId())) == 0;
        }
        return false;
    }
}
