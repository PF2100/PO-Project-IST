package prr.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.regex.Pattern;

import prr.core.exception.*;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

enum TerminalMode {
    BUSY,
    ON,
    SILENCE,
    OFF
}

public class Terminal implements Serializable /* FIXME maybe addd more interfaces */ {

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
    //Colocar Comunicacoes


    public Terminal(String terminalID, Client owner) throws NumberFormatException,InvalidKeyNumberException {
        if(!(Pattern.matches("[0-9]{6}", terminalID))) {
            throw new InvalidKeyNumberException(); 
        }
        _id = terminalID;
        _debt = 0;
        _payments = 0;
        _owner = owner;
        _mode = TerminalMode.ON;
    }

// FIXME define attributes
// FIXME define contructor(s)
// FIXME define methods

    public String getId() {
        return _id;
    }

    public TerminalMode getMode() {
        return _mode;
    }

    public double getDebt() {
        return _debt;
    }

    public double getPayments() {
        return _payments;
    }

    public Map<String, Terminal> getFriends() {
        return _friends;
    }

    public Client getOwner() {
        return _owner;
    }


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

    public boolean setOnIdle() { // É suposto dar true

        if (_mode == TerminalMode.ON) {
            return false;
        } else {
            _mode = TerminalMode.ON;
            return true;
        }
    }

    public boolean setBusy() {
        if (_mode == TerminalMode.ON || _mode == TerminalMode.SILENCE) {
            _mode = TerminalMode.BUSY;
            return true;
        } else {
            return false;
        }
    }

    public boolean setOnSilent() {
        if (_mode == TerminalMode.ON || _mode == TerminalMode.BUSY) {
            _mode = TerminalMode.SILENCE;
            return true;
        } else {
            return false;
        }
    }

    public boolean turnOff() {
        if (_mode == TerminalMode.ON || _mode == TerminalMode.SILENCE) {
            _mode = TerminalMode.OFF;
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String message = "BASIC" +"|"+ _id + "|" + _owner.getKey() + "|" + _mode + "|" + Math.round(_payments)
                + "|" + Math.round(_debt);

        /* 
        for (String terminalID : _friends.keySet()) {
            message = message + "|" + terminalID;
        }
        */
        return message;
    }

    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive communication) and
     * it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {
        // FIXME add implementation code
        return true; // Tava a dar erro
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     **/
    public boolean canStartCommunication() {
        // FIXME add implementation code
        return true; // Tava a dar erro
    }

    public void addFriend(Terminal friend) {
        _friends.put(friend.getId(), friend);
    }
}
