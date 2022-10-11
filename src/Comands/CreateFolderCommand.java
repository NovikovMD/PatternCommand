package Comands;

import directory.WorkingDirectory;

import java.util.Scanner;

public class CreateFolderCommand implements IComandable{
    Scanner inp;

    public CreateFolderCommand() {

        this.inp = new Scanner(System.in);
    }

    @Override
    public void execute() {

        System.out.print("Print folder name: ");
        String name = inp.nextLine();
        WorkingDirectory.getInstance().createFolder(name);
    }
}
