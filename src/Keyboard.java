
import java.util.Scanner;
public class Keyboard {

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        Developer dev = new Developer();
        boolean restarter = true;
        while (restarter) {
            System.out.print("\n>>>Input action<<<\n" +
                    "1 - show current files\n" +
                    "2 - show parent name\n" +
                    "3 - show current name\n" +
                    "4 - become parent folder\n" +
                    "5 - check folder to exist in current position\n" +
                    "6 - create folder\n" +
                    "7 - become child folder\n" +
                    "8 - delete all folders\n" +
                    "9 - show files with specific extension\n" +
                    "10 - show folder hierarchically\n" +
                    "11 - check folder to exist hierarchically\n" +
                    "0 - quit\n" +
                    "Input: ");
            int choice = Integer.parseInt(inp.nextLine());
            switch (choice) {
                case 1 -> dev.getFiles();

                case 2 -> dev.getParentName();

                case 3 -> dev.getDirectoryName();

                case 4 -> dev.becomeParent();

                case 5 -> dev.findChildFolder();

                case 6 -> dev.createFolder();

                case 7 -> dev.becomeChild();

                case 8 -> dev.deleteAllFolders();

                case 9 -> dev.getCertainFiles();

                case 10 -> dev.GetHierarchyFolders();

                case 11 -> dev.findFolderHierarchy();

                case 0 -> restarter = false;

            }
        }
        System.out.println("ENDING.........");
    }

}
