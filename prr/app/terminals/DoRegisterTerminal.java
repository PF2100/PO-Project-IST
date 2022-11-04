package prr.app.terminals;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnknownClientException;
import prr.core.exception.InvalidKeyNumberException;
import prr.app.terminals.Message;

import prr.core.exception.KeyAlreadyExistsException;

import prr.app.exception.DuplicateClientKeyException;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    addStringField("key", Message.terminalKey());
    addOptionField("type", Message.terminalType(),"FANCY","BASIC");
    addStringField("clientId",Message.clientKey());

  }

  @Override
  protected final void execute() throws CommandException {
    String key = stringField("key");
    String type = stringField("type");
    String clientId = stringField("clientId");
    try {  
      Terminal terminal = _receiver.registerTerminal(type,key,clientId);
    }catch(UnknownClientException uce){
      throw new UnknownClientKeyException(clientId);
    }
    catch(NumberFormatException | InvalidKeyNumberException ikne) {
      throw new InvalidTerminalKeyException(key);
    }
    catch(KeyAlreadyExistsException kaee) {
      throw new DuplicateTerminalKeyException(key);
    }
  }
}
