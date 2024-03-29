package prr.app.client;

import prr.core.Network;
import prr.core.exception.UnknownClientException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

  DoDisableClientNotifications(Network receiver) {
    super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("clientKey",Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String clientKey = stringField("clientKey");
    try{
      if(!_receiver.disableClientNotifications(clientKey)) {
        _display.popup(Message.clientNotificationsAlreadyDisabled());
      }
    }catch(UnknownClientException uce){
      throw new UnknownClientKeyException(clientKey);
    }
  }
}