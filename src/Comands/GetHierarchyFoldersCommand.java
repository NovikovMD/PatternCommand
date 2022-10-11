package Comands;

import directory.WorkingDirectory;

public class GetHierarchyFoldersCommand implements IComandable{
    @Override
    public void execute() {
        var arr = WorkingDirectory.getInstance().getHierarchyFolders();
        for (var w : arr)
            System.out.println(w);
    }
}
