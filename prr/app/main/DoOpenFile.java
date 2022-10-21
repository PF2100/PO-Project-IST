package prr.app.main;

import prr.core.NetworkManager;
import prr.app.exception.FileOpenFailedException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.ImportFileException;
import prr.core.exception.UnavailableFileException;
import prr.app.main.Message;
//Add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<NetworkManager> {
  DoOpenFile(NetworkManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("filename",Message.openFile() );
  }

  @Override
  protected final void execute()  throws CommandException {
    String filename = stringField("filename");
    try {
      _receiver.load(filename);
    }
    catch(UnavailableFileException ufe) {
      throw new FileOpenFailedException(ufe);
    }

  }
}

