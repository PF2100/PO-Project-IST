package prr.app.terminals;

import prr.core.Network;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add mode import if needed

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

  DoOpenMenuTerminalConsole(Network receiver) {
    super(Label.OPEN_MENU_TERMINAL, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    //(new prr.app.terminals.Menu(_receiver).open());
  }
}
