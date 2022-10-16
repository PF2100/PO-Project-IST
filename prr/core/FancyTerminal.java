package prr.core;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String terminalID, Client owner) {
        super(terminalID, owner);
    }

    public void makeVideoCall(Terminal to) {
        // implementar
    }

    protected void acceptVideoCall(Terminal from) {
        // implementar
        super.setBusy();
    }

    @Override
    public String toString() {
        String message = "FANCY" +"|"+ super.getId() + "|" + super.getOwner().getKey() + "|" + super.getMode()
                + "|" + super.getPayments() + "|" + super.getDebt();
        /* 
        for (String terminalID : super.getFriends().keySet()) {
            message = message + "|" + terminalID;
        }
        */
        return message;
    }
}