package prr.app.main;

import prr.core.NetworkManager;
import prr.app.exception.FileOpenFailedException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.ImportFileException;
import prr.app.main.Message;
//Add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<NetworkManager> {
  DoOpenFile(NetworkManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("filename",Message.openFile() );
    //FIXME add command fields
  }

  @Override
  protected final void execute()  throws CommandException {
    String filename= stringField("filename");
    try {
      _receiver.importFile(filename);
    }
    catch(ImportFileException ife) {
      throw new  FileOpenFailedException(ife);
    }

  }
}

