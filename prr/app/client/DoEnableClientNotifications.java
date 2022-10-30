package prr.app.client;

import prr.core.Network;
import prr.core.exception.UnknownClientException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

  DoEnableClientNotifications(Network receiver) {
    super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("clientKey",Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String clientKey = stringField("clientKey");
    try{
      if(!_receiver.enableClientNotifications(clientKey)) {
        System.out.println(Message.clientNotificationsAlreadyEnabled());
      }
    }catch(UnknownClientException uce){
      throw new UnknownClientKeyException(clientKey);
    }
  }
}
