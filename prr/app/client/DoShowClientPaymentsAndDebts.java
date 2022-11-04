package prr.app.client;

import prr.core.Client;
import prr.core.Network;
import prr.core.exception.UnknownClientException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

  DoShowClientPaymentsAndDebts(Network receiver) {
    super(Label.SHOW_CLIENT_BALANCE, receiver);
    addStringField("clientKey",Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String clientKey = stringField("clientKey");
    try{
      Client client = _receiver.getClient(clientKey);
      _display.popup(Message.clientPaymentsAndDebts(clientKey, Math.round(client.getPayments()), Math.round(client.getDebts())));
    }catch(UnknownClientException uce){
      throw new UnknownClientKeyException(clientKey);
    }
  }
}
