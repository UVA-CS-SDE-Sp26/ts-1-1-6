/*
*File handler
*/
import java.io.File;
import java.util.*;

public class FileHandler {

    private ArrayList<String> namesOfFiles;
    private Cipher cipher;
    private String fileRoot;

    public FileHandler(Cipher cipher) {
        this.cipher = cipher;
        this.namesOfFiles = new ArrayList<>();
        listFiles();
    }

    public FileHandler(String fileRoot) {
        this.fileRoot = fileRoot;
        this.namesOfFiles = new ArrayList<>();
        listFiles();
    }

    public FileHandler(Cipher cipher, String fileRoot) {
        this.cipher = cipher;
        this.namesOfFiles = new ArrayList<>();
        listFiles();
        this.fileRoot = fileRoot;
    }

    public FileHandler() {
        this.cipher = cipher;
        this.namesOfFiles = new ArrayList<>();
        listFiles();
        this.fileRoot = fileRoot;
    }



    public String readKey(){
        String path = fileRoot + "/cipher/key.txt";
        //Todo: add logic to read cipher key from key.txt
        String key = "";
        return key;
    }

    public ArrayList<String> listFiles(){
        String folderPath = fileRoot + "/data/";
        //Todo: add logic to read the file names in data
        File folder = new File("data");
        File[] listOfFiles = folder.listFiles();

        namesOfFiles.clear();
        if(listOfFiles != null){
            for (File file : listOfFiles) {
                if(file.isFile() && file.getName().endsWith(".txt")){
                    namesOfFiles.add(file.getName());
                }
            }
        }
        return namesOfFiles;
    }


    public String readFile(int indexOfFile){
        if(validIndex(indexOfFile)){
            //todo: add logic to read a specific file
            String TextFromFileToReturn = "";
            return TextFromFileToReturn;
        }
        else{
            listFiles();
            return null;
        }

    }
    private boolean validIndex(int indexOfFile){
        //todo: check valid index included in NamesOfFiles Array
        return true;
    }


}
