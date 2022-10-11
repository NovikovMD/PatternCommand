package Comands;

import directory.WorkingDirectory;

import java.util.Scanner;

public class GetCertainFilesCommand implements IComandable{
    Scanner inp;

    public GetCertainFilesCommand() {
        this.inp = new Scanner(System.in);
    }

    @Override
    public void execute() {

        System.out.print("Print extension: ");
        String extension = inp.nextLine();
        var arr = WorkingDirectory.getInstance().getCertainFiles(extension);
        if (arr.size() == 0)
            System.out.println("No files with extension: " + extension);
        else {
            for (var w : arr)
                System.out.println(w);
        }
    }
}
