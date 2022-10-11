package Comands;

import directory.WorkingDirectory;

import java.util.Scanner;

public class BecomeChildCommand implements IComandable{
    Scanner inp;

    public BecomeChildCommand() {
        this.inp = new Scanner(System.in);
    }

    @Override
    public void execute() {

        System.out.print("Print folder name: ");
        String name = inp.nextLine();
        if (!WorkingDirectory.getInstance().becomeChild(name))
            System.out.println("No folder with name:" + name);
    }
}
