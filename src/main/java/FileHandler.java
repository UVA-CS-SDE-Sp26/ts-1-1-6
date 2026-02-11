/*
*File handler
*/
import java.util.*;

public class FileHandler {

    private ArrayList<String> NamesOfFiles;


    public ArrayList<String> listFiles(){
        //Todo: add logic to read the file names in data
        return NamesOfFiles;
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
