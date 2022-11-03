package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnknownTerminalException;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

  DoAddFriend(Network context, Terminal terminal) {
    super(Label.ADD_FRIEND, context, terminal);
    addStringField("terminalKey",Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminalKey = stringField("terminalKey");
    try{
      _network.addFriend(_receiver.getId(),terminalKey);
    }catch(UnknownTerminalException ute){
      throw new UnknownTerminalKeyException(terminalKey); 
    }

  }
}
