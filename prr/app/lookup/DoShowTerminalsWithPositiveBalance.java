package prr.app.lookup;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;
//FIXME add more imports if needed

/**
 * Show terminals with positive balance.
 */
class DoShowTerminalsWithPositiveBalance extends Command<Network> {

  DoShowTerminalsWithPositiveBalance(Network receiver) {
    super(Label.SHOW_TERMINALS_WITH_POSITIVE_BALANCE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<Terminal> terminalsWithPosBalance = _receiver.getTerminalWithPosBalance();
    for(Terminal terminal : terminalsWithPosBalance){
      _display.popup(terminal.toString());
    }
  }
}
