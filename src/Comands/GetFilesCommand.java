package Comands;

import directory.WorkingDirectory;

import java.util.ArrayList;

public class GetFilesCommand implements IComandable {

    @Override
    public void execute() {
        ArrayList<String> Files = WorkingDirectory.getInstance().getFiles();
        for (String file : Files) {
            System.out.println(file);
        }
    }
}
