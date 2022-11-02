package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnknownTerminalException;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

  DoRemoveFriend(Network context, Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
    addStringField("terminalKey",Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminalKey = stringField("terminalKey");
    try{
      _network.removeFriend(_receiver,terminalKey);
    }catch(UnknownTerminalException ute){
      throw new UnknownTerminalKeyException(terminalKey); 
    }

  }
}
