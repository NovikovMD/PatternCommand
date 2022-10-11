package directory;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class WorkingDirectory {
    private static WorkingDirectory instance;
    private  String directoryName;

    private WorkingDirectory(String directoryName){

        this.directoryName = directoryName;
    }

    public static WorkingDirectory getInstance(String directoryName){
        if (instance == null)
            instance = new WorkingDirectory(directoryName);

        return instance;
    }
    public static WorkingDirectory getInstance(){
        return instance;
    }

    public ArrayList<String> getFiles(){
        //find directory
        File wa = new File(directoryName);
        //create output
        ArrayList<String> output = new ArrayList<>();

        //foreach in current directory
        for (var w:
                wa.listFiles()) {
            output.add(w.getName());
        }

        return output;
    }

    public String getParentName(){
        //create output string
        StringBuilder parentName = new StringBuilder(directoryName);

        //init index
        int i=parentName.length()-1;

        while (i>=0){
            //delete name of current directory
            if (parentName.charAt(i) != '\\') {
                parentName.deleteCharAt(i);
                i--;
            }
            else {
                //delete last "/" and exit "while"
                parentName.deleteCharAt(i);
                i = -1;
            }
        }

        return parentName.toString();
    }

    public void becomeParent(){
        directoryName = getParentName();
    }

    public String getDirectoryName(){
        return directoryName;
    }

    public void setDirectoryName(String newName){
        directoryName = newName;
    }

    public boolean findChildFolder(String s){
        ArrayList<String> arr = instance.getFiles();
        for (int i=0;i<arr.size();i++){
            if (arr.get(i).equals(s))
                return true;
        }
        return false;
    }

    public boolean createFolder(String s){
        File f = new File(directoryName + "\\" + s);
        try{
            if (f.mkdir())
                return true;
            else
                return false;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean becomeChild(String s){
        ArrayList arr = instance.getFiles();
        for (int i=0;i<arr.size();i++){
            if (arr.get(i).equals(s)){
                directoryName = directoryName + "\\" + s;
                return true;
            }
        }
        return false;
    }

    public void deleteAllFolders(){
        ArrayList arr = instance.getFiles();
        for (int i=0;i<arr.size();i++){
            File f = new File(directoryName + "\\"+ arr.get(i));
            if (f.isDirectory())
                f.delete();
        }
    }

    public ArrayList<String> getCertainFiles(String extension){
        //find directory
        File wa = new File(directoryName);
        //create output
        ArrayList<String> output = new ArrayList<>();

        //foreach in current directory
        for (var w:
                wa.listFiles()) {
            if (w.getName().contains(extension))
                output.add(w.getName());
        }

        return output;
    }

    public ArrayList<String> getHierarchyFolders(){
        //output keeps directory names
        ArrayList<String> output = new ArrayList<>();
        StringBuilder curDir =  new StringBuilder(directoryName);

        //index of depth in hierarchy
        int deepIndex = 0;
        //indexer for certain level of depth in hierarchy
        ArrayList<Integer> index = new ArrayList<Integer>();

        do {
            if (index.size()-1<deepIndex)
                index.add(0);

            ArrayList<String> allFolders = getFolders(curDir.toString());

            //check if we have more folders on that level
            if (allFolders.size()>0 && allFolders.size()!=index.get(deepIndex)){
                //go deeper and set current depth index + 1
                curDir.append("\\").append(allFolders.get(index.get(deepIndex)));
                index.set(deepIndex,index.get(deepIndex)+1);
                deepIndex++;
            }
            else{
                //add current directory to output
                output.add(curDir.toString());

                //go higher and set current depth index == 0
                index.set(deepIndex,0);
                deepIndex--;
                curDir.replace(0,curDir.length(),getParentName(curDir.toString()));
            }

        }while(deepIndex>=0);

        return output;
    }

    private ArrayList<String> getFolders(String directoryName){
        File wa = new File(directoryName);
        ArrayList<String> output = new ArrayList<>();

        //foreach in current directory
        for (var w:
                wa.listFiles()) {
            if (w.isDirectory())
                output.add(w.getName());
        }

        return output;
    }

    private String getParentName(String curDir){
        //create output string
        StringBuilder parentName = new StringBuilder(curDir);

        //init index
        int i=parentName.length()-1;

        while (i>=0){
            //delete name of current directory
            if (parentName.charAt(i) != '\\') {
                parentName.deleteCharAt(i);
                i--;
            }
            else {
                //delete last "/" and exit "while"
                parentName.deleteCharAt(i);
                i = -1;
            }
        }

        return parentName.toString();
    }

    public boolean findFolder(String needToFind){
        //output keeps directory names
        StringBuilder curDir =  new StringBuilder(directoryName);

        //index of depth in hierarchy
        int deepIndex = 0;
        //indexer for certain level of depth in hierarchy
        ArrayList<Integer> index = new ArrayList<Integer>();

        do {

            if (curDir.toString().contains(needToFind))
                return true;


            if (index.size()-1<deepIndex)
                index.add(0);

            ArrayList<String> allFolders = getFolders(curDir.toString());

            //check if we have more folders on that level
            if (allFolders.size()>0 && allFolders.size()!=index.get(deepIndex)){
                //go deeper and set current depth index + 1
                curDir.append("\\").append(allFolders.get(index.get(deepIndex)));
                index.set(deepIndex,index.get(deepIndex)+1);
                deepIndex++;
            }
            else{
                //go higher and set current depth index == 0
                index.set(deepIndex,0);
                deepIndex--;
                curDir.replace(0,curDir.length(),getParentName(curDir.toString()));
            }

        }while(deepIndex>=0);
        return false;
    }

}
