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

    private TerminalState _state; 

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
        _state = new IdleState(this);
        _ongoingCommunication = null;
    }


    /* 
    public boolean setOnIdle() {
        return _state.setOnIdle(this);
    }
    */

    public String getId() {return _id;}

    public TerminalState getState() {return _state;}

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


    public void makeSms(Terminal to, String message) {
        // implementar
    }

    protected void acceptSms(Terminal from) {
        // implementar
    }

    public void makeVoiceCall(Terminal to) {
        // implementar
    }

    protected void acceptVoiceCall(Terminal from) {
        // implementar
        //_mode = TerminalMode.BUSY;
    }

    public void endOngoingCommunication(int size) {
        // implementar 
        //this.setOnIdle();
    }

    public Communication getOngoingCommunication() {
        return _ongoingCommunication;
    }

   
    public boolean setBusy() {
        return _state.setBusy(this);
    }

    public boolean setOnSilent() {
        return _state.setOnSilent(this);
    }

    public boolean turnOff() {
        return _state.turnOff(this);
    }

    public abstract String toString();
   
    /* 
    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive communication) and
     * it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {

        return false;
        //return _state.canEndCurrentCommunication();
        //return  _mode == TerminalMode.BUSY && _ongoingCommunication != null && _ongoingCommunication.getFrom().getId().equals(_id);
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     *
    /* */
    public boolean canStartCommunication() {
        return true;
        //return _state.canStartCommunication(); 
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

    public void setTerminalState(TerminalState state) {
        _state = state;
    }
}
