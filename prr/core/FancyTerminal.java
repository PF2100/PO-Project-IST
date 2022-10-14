package prr.core;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String terminalID, Client owner, String Mode) {
        super(terminalID, owner, Mode);
    }

    public void makeVideoCall(Terminal to) {
        // implementar
    }

    protected void acceptVideoCall(Terminal from) {
        // implementar
        super.setBusy();
    }
}
