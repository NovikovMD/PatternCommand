package Comands;

import directory.WorkingDirectory;

public class DeleteAllFoldersCommand implements IComandable{

    @Override
    public void execute() {
        WorkingDirectory.getInstance().deleteAllFolders();
    }
}
