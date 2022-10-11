package Comands;

import directory.WorkingDirectory;

import java.util.Scanner;

public class FindChildFolderCommand implements IComandable{
    Scanner inp;

    public FindChildFolderCommand() {
        this.inp = new Scanner(System.in);
    }

    @Override
    public void execute() {

        System.out.print("Print folder name: ");

        String name = inp.nextLine();
        if (WorkingDirectory.getInstance().findChildFolder(name))
            System.out.println(name + " exists in current folder");
        else
            System.out.println(name + " doesn't exist in current folder");
    }
}
