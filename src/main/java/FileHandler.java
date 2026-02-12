/*
*File handler
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileHandler {

    private ArrayList<String> namesOfFiles;
    private Cipher cipher;
    private String fileRoot;



    public FileHandler() {
        this.namesOfFiles = new ArrayList<>();
        this.fileRoot = System.getProperty("user.dir");
        //fills the namesOfFiles arrayList to avoid error about read file called first
        listFiles();
    }


    //for setting the directory to the test resources dir.
    public FileHandler(String fileRoot) {
        this.fileRoot = fileRoot;
        this.namesOfFiles = new ArrayList<>();
        //fills the namesOfFiles arrayList to avoid error about read file called first
        listFiles();
    }
    //Constructor used for testing



    /**
     * Takes in path to key's file
     * Reads the default key from /ciphers/key.txt
     * @return String
     */
    public String readKey(String path){
        if(path.isEmpty()){
            path = fileRoot + "/ciphers/key.txt";
        }

        File keytxt = new File(path);
        if(!keytxt.exists()){
            return null;
        }
        String key = readFileToString(keytxt);
            return key.isEmpty() ? null : key;
    }

    /**
     * Reads file names in data folder and adds them to namesOfFiles
     * @return ArrayList<String>
     */
    public ArrayList<String> listFiles(){
        String folderPath = fileRoot + "/data/";
        File folder = new File(folderPath);
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

    /**
     * Reads the file at the index within namesOfFiles ArrayList<>
     */
    public String readFile(int indexOfFile){

        if(validIndex(indexOfFile)) {
            //finds the filename at the requested index
            String fileName = namesOfFiles.get(indexOfFile);
            //collects the file at fileName
            File fileToRead = new File(fileRoot + "/data/" + fileName);
            //collects string from fileToRead
            return readFileToString(fileToRead);

        }else {
                return "Invalid Index";
            }

        }

    /**
     * Reads the string within given file
     * @return String
     */
    private String readFileToString(File file){
        StringBuilder string = new StringBuilder();

        try(Scanner scanner = new Scanner(file)) {
            //loops through lines in file and adds it to the string
            while (scanner.hasNextLine()){
            string.append(scanner.nextLine());
            //adds new line to replecate lines in file
            if (scanner.hasNextLine()) {
                string.append("\n");
            }
            }
            return string.toString().trim();
        }catch (FileNotFoundException e){
            return "Error: File Not Found";
        }

    }


    private boolean validIndex(int indexOfFile){
        return indexOfFile >= 0 && indexOfFile < namesOfFiles.size();
    }


}
