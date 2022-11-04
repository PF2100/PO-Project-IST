package prr.app.client;

import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.client.Message;

import prr.core.exception.UnknownClientException;
import prr.core.Client;
import prr.core.Network;
import java.util.*;

//FIXME add more imports if needed

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

  DoShowClient(Network receiver) {
    super(Label.SHOW_CLIENT, receiver);
    addStringField("clientKey",Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String key = stringField("clientKey");
    try{
      Client client = _receiver.getClient(key);
      _display.popup(client);
      for(String notification : _receiver.getNotifications(client)){
        _display.popup(notification);
      }
    }catch ( UnknownClientException uce) {
      throw new UnknownClientKeyException(key);
    }
  }
}
