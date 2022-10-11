package Comands;

import directory.WorkingDirectory;

public class BecomeParentCommand implements IComandable{
    @Override
    public void execute() {
        WorkingDirectory.getInstance().becomeParent();
    }
}
