package Comands;

import directory.WorkingDirectory;

public class GetInstanceCommand implements IComandable{
    private String directoryName;

    public GetInstanceCommand(String directoryName){
        this.directoryName = directoryName;
    }

    @Override
    public void execute() {
        WorkingDirectory.getInstance(directoryName);
    }
}
