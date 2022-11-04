package prr.app.lookup;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.*;

import java.util.List;

/**
 * Show unused terminals (without communications).
 */
class DoShowUnusedTerminals extends Command<Network> {

  DoShowUnusedTerminals(Network receiver) {
    super(Label.SHOW_UNUSED_TERMINALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<Terminal> unusedTerminals = _receiver.getUnusedTerminals();
    for (Terminal terminal : unusedTerminals) {
      _display.addLine(terminal.toString());
    }
    _display.display();
  }
}
