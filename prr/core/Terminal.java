package prr.core;

import java.io.Serial;
import java.io.Serializable;

import java.util.regex.Pattern;
import prr.core.exception.*;

import java.util.*;




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
    private Map<Integer,Communication> _madeCommunications = new HashMap<>();
    private Map<Integer,Communication> _receivedCommunications = new HashMap<>();
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


    public String getId() {return _id;}

    public TerminalState getState() {return _state;}

    public double getDebt() {return _debt;}

    public double getPayments() {return _payments;}

    public Collection<Communication> getMadeCommunications(){
        if (_madeCommunications == null) {
            return null;
        }
        return _madeCommunications.values();
    }


    public Collection<Communication> getReceivedCommunications() {
        if (_receivedCommunications == null) {
            return null;
        }
        return _receivedCommunications.values();
    }

    public Collection<String> getFriends() {return _friends.keySet();}

    public Client getOwner() {return _owner;}

    //Returns the communication if it was successfuly made, null if not
    public Communication makeSms(Terminal to, String message) {
        return _state.makeSms(to,message);
    }

    protected boolean acceptSms(Terminal from) {
        return _state.acceptSms(from);
    }

    public void makeVoiceCall(Terminal to) {
        // implementar
    }

    protected void acceptVoiceCall(Terminal from) {
        // implementar
    }

    public void endOngoingCommunication(int size) {
        // implementar 
    }

    public Communication getOngoingCommunication() {
        return _ongoingCommunication;
    }


    public boolean setOnIdle() {return _state.setOnIdle();}

    public boolean setBusy() {return _state.setBusy();}

    public boolean setOnSilent() {return _state.setOnSilent();}

    public boolean turnOff() {return _state.turnOff();}

    public abstract String toString();
   
    /* 
    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive communication) and
     * it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {

        return true;
        //return _state.canEndCurrentCommunication();
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

    public void addMadeCommunications(Communication comms) {
        _madeCommunications.put(comms.getId(), comms);
    }

    public void addReceivedCommunications(Communication comms) {
        _receivedCommunications.put(comms.getId(), comms);
    }
}
