public class Filehandler {

    private String[] NamesOfFiles;


    public String[] readOrListFiles(){
        //Todo: add logic to read the file names in data
        return NamesOfFiles;
    }
    public String readOrListFiles(int indexOfFile){
        if(validIndex(indexOfFile)){

            //todo: add logic to read a specific file
            String TextFromFileToReturn = "";
            return TextFromFileToReturn;
        }
        else{
            readOrListFiles();
            return null;
        }

    }
    private boolean validIndex(int indexOfFile){
        //todo: check valid index included in NamesOfFiles Array
        return true;
    }






}
