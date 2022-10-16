package prr.app.terminals;

import prr.core.Network;
import prr.core.Terminal;
import prr.app.terminals.Message;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    addStringField("key", Message.terminalKey());
    addStringField("type",Message.terminalType());
    addStringField("clientID",Message.clientKey());

  }

  @Override
  protected final void execute() throws CommandException { // InvalidTerminalKeyException DuplicateTerminalKeyException é preciso adicionar
    String key = stringField("key");
    String type = stringField("type");
    String clientID = stringField("clientID");  
    Terminal terminal = _receiver.registerTerminal(key,type,clientID);
  }
}
