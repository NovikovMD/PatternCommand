package tests;

import directory.WorkingDirectory;
import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class TestWrkDirectory {

    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {
        Field instance = WorkingDirectory.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void tryGetInstance() {
        ;
        Assert.assertNotNull(WorkingDirectory.getInstance("First"));
    }

    @Test
    public void tryGetFiles() {
        WorkingDirectory.
                getInstance(System.getProperty("user.home")+"\\Desktop").
                createFolder("Test");
        WorkingDirectory.getInstance().becomeChild("Test");
        WorkingDirectory.
                getInstance().
                createFolder("Test2");
        var test = WorkingDirectory.
        getInstance().
                getFiles();

        Assert.assertEquals("Test2", test.get(0));
        WorkingDirectory.getInstance().deleteAllFolders();

        File f = new File(System.getProperty("user.home")+"\\Desktop\\Test");
        f.delete();


    }

    @Test
    public void tryGetParentName() {
        Assert.assertEquals("First\\Parent", WorkingDirectory.
                getInstance("First\\Parent\\Current").getParentName());

    }

    @Test
    public void tryGetParentName2() {
        Assert.assertEquals("", WorkingDirectory.
                getInstance("Current").getParentName());
    }

    @Test
    public void tryBecomeParent() {
        WorkingDirectory.
                getInstance("Parent\\Current").becomeParent();
        Assert.assertEquals("Parent", WorkingDirectory.getInstance().getDirectoryName());
    }

    @Test
    public void tryBecomeParent2() {
        WorkingDirectory.
                getInstance("Current").becomeParent();
        Assert.assertEquals("", WorkingDirectory.getInstance().getDirectoryName());
    }

    @Test
    public void tryFindChildFolder(){
        File f = new File(System.getProperty("user.home")+"\\Desktop\\Test");
        f.mkdir();
        File f2 = new File(System.getProperty("user.home")+"\\Desktop\\Test\\src");
        f2.mkdir();
        Assert.assertTrue(WorkingDirectory.getInstance(System.getProperty("user.home")+"\\Desktop\\Test").
                findChildFolder("src"));
        f2.delete();
        f.delete();
    }
    @Test
    public void tryFindChildFolder2(){
        Assert.assertFalse(WorkingDirectory.getInstance(System.getProperty("user.home")+"\\Desktop").
                findChildFolder("NonExistingFolder"));
    }

    @Test
    public void tryCreateFolder(){
        File f = new File(System.getProperty("user.home")+"\\Desktop\\Test");
        f.mkdir();
        Assert.assertTrue(WorkingDirectory.getInstance(System.getProperty("user.home")+"\\Desktop\\Test").
                createFolder("src"));
        Assert.assertNotNull(WorkingDirectory.getInstance().getFiles().get(0));
        File f2 = new File(System.getProperty("user.home")+"\\Desktop\\Test\\src");
        f2.delete();
        f.delete();
    }

    @Test
    public void tryBecomeChild(){
        File f = new File(System.getProperty("user.home")+"\\Desktop\\Test");
        f.mkdir();

        File f2 = new File(System.getProperty("user.home")+"\\Desktop\\Test\\SomeFolder");
        f2.mkdir();
        Assert.assertTrue(WorkingDirectory.getInstance(System.getProperty("user.home")+"\\Desktop\\Test").
                becomeChild("SomeFolder"));
        Assert.assertEquals(System.getProperty("user.home")+"\\Desktop\\Test\\SomeFolder",
                WorkingDirectory.getInstance().getDirectoryName());
        f2.delete();
        f.delete();
    }
    @Test
    public void tryDeleteAllFolders(){
        File f = new File(System.getProperty("user.home")+"\\Desktop\\Test");
        f.mkdir();
        WorkingDirectory.getInstance(System.getProperty("user.home")+"\\Desktop\\Test").
                createFolder("SomeFolder");
        WorkingDirectory.getInstance(System.getProperty("user.home")+"\\Desktop\\Test").
                createFolder("SomeFolder2");
        WorkingDirectory.getInstance().deleteAllFolders();
        ArrayList<String> arr = WorkingDirectory.getInstance().getFiles();
        for (int i=0;i<arr.size();i++){
            Assert.assertTrue(arr.get(i).contains("."));
        }
        f.delete();
    }

    @Test
    public void tryGetCertainFiles() throws IOException {
        File f = new File(System.getProperty("user.home")+"\\Desktop\\Test");
        f.mkdir();
        File textFile = new File(System.getProperty("user.home")+"\\Desktop\\Test\\text.txt");
        textFile.createNewFile();
        var arr = WorkingDirectory.getInstance(System.getProperty("user.home")+"\\Desktop\\Test").
                getCertainFiles("txt");
        for (int i=0;i<arr.size();i++){
            Assert.assertTrue(arr.get(i).contains("txt"));
        }
        textFile.delete();
        f.delete();

    }

    @Test
    public void tryTreeFolders() throws IOException {
        File f = new File(System.getProperty("user.home")+"\\Desktop\\Test");
        f.mkdir();
        WorkingDirectory.getInstance(System.getProperty("user.home")+"\\Desktop\\Test")
                .createFolder("test1");
        WorkingDirectory.getInstance().createFolder("test2");
        WorkingDirectory.getInstance().becomeChild("test1");
        WorkingDirectory.getInstance().createFolder("test1_1");
        WorkingDirectory.getInstance().createFolder("test1_2");
        WorkingDirectory.getInstance().becomeParent();
        WorkingDirectory.getInstance().becomeChild("test2");
        WorkingDirectory.getInstance().createFolder("test2_1");
        WorkingDirectory.getInstance().createFolder("test2_2");
        WorkingDirectory.getInstance().createFolder("test2_3");

        WorkingDirectory.getInstance().setDirectoryName(System.getProperty("user.home")+"\\Desktop\\Test");
        var arr = WorkingDirectory.getInstance()
                .getHierarchyFolders();

        Assert.assertEquals(System.getProperty("user.home")+"\\Desktop\\Test\\test1\\test1_1",arr.get(0));
        Assert.assertEquals(System.getProperty("user.home")+"\\Desktop\\Test\\test1\\test1_2",arr.get(1));
        Assert.assertEquals(System.getProperty("user.home")+"\\Desktop\\Test\\test1",arr.get(2));
        Assert.assertEquals(System.getProperty("user.home")+"\\Desktop\\Test\\test2\\test2_1",arr.get(3));
        Assert.assertEquals(System.getProperty("user.home")+"\\Desktop\\Test\\test2\\test2_2",arr.get(4));
        Assert.assertEquals(System.getProperty("user.home")+"\\Desktop\\Test\\test2\\test2_3",arr.get(5));
        Assert.assertEquals(System.getProperty("user.home")+"\\Desktop\\Test\\test2",arr.get(6));
        Assert.assertEquals(System.getProperty("user.home")+"\\Desktop\\Test",arr.get(7));


        FileUtils.deleteDirectory(f);


    }
    @Test
    public void tryFindFolder() throws IOException {
        File f = new File(System.getProperty("user.home")+"\\Desktop\\Test");
        f.mkdir();
        WorkingDirectory.getInstance(System.getProperty("user.home")+"\\Desktop\\Test")
                .createFolder("test1");
        WorkingDirectory.getInstance().createFolder("test2");
        WorkingDirectory.getInstance().becomeChild("test1");
        WorkingDirectory.getInstance().createFolder("test1_1");
        WorkingDirectory.getInstance().createFolder("test1_2");
        WorkingDirectory.getInstance().becomeParent();
        WorkingDirectory.getInstance().becomeChild("test2");
        WorkingDirectory.getInstance().createFolder("test2_1");
        WorkingDirectory.getInstance().createFolder("test2_2");
        WorkingDirectory.getInstance().createFolder("test2_3");

        WorkingDirectory.getInstance().setDirectoryName(System.getProperty("user.home")+"\\Desktop\\Test");
        Assert.assertTrue(WorkingDirectory.getInstance()
                .findFolder("test2_1"));


        FileUtils.deleteDirectory(f);
    }
    @Test
    public void tryFindFolder2() throws IOException {
        File f = new File(System.getProperty("user.home")+"\\Desktop\\Test");
        f.mkdir();
        WorkingDirectory.getInstance(System.getProperty("user.home")+"\\Desktop\\Test")
                .createFolder("test1");
        WorkingDirectory.getInstance().createFolder("test2");
        WorkingDirectory.getInstance().becomeChild("test1");
        WorkingDirectory.getInstance().createFolder("test1_1");
        WorkingDirectory.getInstance().createFolder("test1_2");
        WorkingDirectory.getInstance().becomeParent();
        WorkingDirectory.getInstance().becomeChild("test2");
        WorkingDirectory.getInstance().createFolder("test2_1");
        WorkingDirectory.getInstance().createFolder("test2_2");
        WorkingDirectory.getInstance().createFolder("test2_3");

        WorkingDirectory.getInstance().setDirectoryName(System.getProperty("user.home")+"\\Desktop\\Test");
        Assert.assertFalse(WorkingDirectory.getInstance()
                .findFolder("test2_5"));


        FileUtils.deleteDirectory(f);
    }
}
