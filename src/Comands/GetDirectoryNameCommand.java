package Comands;

import directory.WorkingDirectory;

public class GetDirectoryNameCommand implements IComandable{
    @Override
    public void execute() {
        System.out.println(WorkingDirectory.getInstance().getDirectoryName());
    }
}
