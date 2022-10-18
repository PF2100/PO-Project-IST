package prr.app.client;

import prr.core.Network;
import prr.app.exception.DuplicateClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.client.Message;

import prr.core.exception.KeyAlreadyExistsException;



/**
 * Register new client.
 */
class DoRegisterClient extends Command<Network> {

  DoRegisterClient(Network receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    addStringField("chave",Message.key());
    addStringField("name",Message.name());
    addIntegerField("taxID",Message.taxId());
  }
  
  @Override
  protected final void execute() throws CommandException { 
    String name = stringField("name");
    String key = stringField("chave");
    Integer taxID = integerField("taxID"); 
    try {
    _receiver.registerClient(key, name, taxID);
    }catch(KeyAlreadyExistsException kaee) {
      throw new DuplicateClientKeyException(key);
    }
  }
}
