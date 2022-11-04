package prr.app.lookup;

import prr.core.Network;
import prr.core.Communication;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;
import java.util.ArrayList;

/**
 * Command for showing all communications.
 */
class DoShowAllCommunications extends Command<Network> {

  DoShowAllCommunications(Network receiver) {
    super(Label.SHOW_ALL_COMMUNICATIONS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<Communication> communications = _receiver.getCommunications();
      for (Communication communication : communications ){
        _display.addLine(communication.toString());
      }
      _display.display();
  }
}
