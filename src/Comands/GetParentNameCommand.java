package Comands;

import directory.WorkingDirectory;

public class GetParentNameCommand implements IComandable {

    @Override
    public void execute() {
        System.out.println(WorkingDirectory.getInstance().getParentName());
    }
}
