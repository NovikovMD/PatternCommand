package Comands;

import directory.WorkingDirectory;

import java.util.Scanner;

public class FindFolderCommand implements IComandable{
    Scanner inp;

    public FindFolderCommand() {
        this.inp = new Scanner(System.in);
    }

    @Override
    public void execute() {

        System.out.print("Print extension: ");
        String name = inp.nextLine();

        if (WorkingDirectory.getInstance().findFolder(name))
            System.out.println(name + " exists");
        else
            System.out.println(name + " doesn't exist");
    }
}
