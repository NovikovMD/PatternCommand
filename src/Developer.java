import Comands.*;

import java.util.Scanner;

public class Developer {
    IComandable getInstance;
    IComandable getFiles;
    IComandable getParentName;
    IComandable becomeParent;
    IComandable getDirectoryName;
    IComandable deleteAllFolders;
    IComandable getHierarchyFolders;
    IComandable findChildFolderCommand;
    IComandable createFolderCommand;
    IComandable becomeChildCommand;
    IComandable getCertainFiles;
    IComandable findFolder;

    public Developer() {
        this.getInstance = new GetInstanceCommand(System.getProperty("user.home") + "\\Desktop");
        getInstance.execute();
        this.getFiles = new GetFilesCommand();
        this.getParentName = new GetParentNameCommand();
        this.getDirectoryName = new GetDirectoryNameCommand();
        this.becomeParent = new BecomeParentCommand();
        this.deleteAllFolders = new DeleteAllFoldersCommand();
        this.getHierarchyFolders = new GetHierarchyFoldersCommand();
        this.findChildFolderCommand = new FindChildFolderCommand();
        this.createFolderCommand = new CreateFolderCommand();
        this.becomeChildCommand = new BecomeChildCommand();
        this.getCertainFiles = new GetCertainFilesCommand();
        this.findFolder = new FindFolderCommand();
    }

    public void getFiles(){
        getFiles.execute();
    }
    public void getParentName(){
        getParentName.execute();
    }
    public void getDirectoryName(){
        getDirectoryName.execute();
    }
    public void becomeParent(){
        becomeParent.execute();
    }
    public void findChildFolder(){
        findChildFolderCommand.execute();
    }
    public void createFolder(){
        createFolderCommand.execute();
    }
    public void becomeChild(){
        becomeChildCommand.execute();
    }
    public void deleteAllFolders(){
        deleteAllFolders.execute();
    }
    public void getCertainFiles(){
        getCertainFiles.execute();
    }
    public void GetHierarchyFolders(){
        getHierarchyFolders.execute();
    }
    public void findFolderHierarchy(){
        findFolder.execute();
    }

}
