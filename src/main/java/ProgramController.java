import java.util.ArrayList;

public class ProgramController {
    private FileHandler fileHandler;
    private Cipher cipher;

    public ProgramController() {
        this.fileHandler = new FileHandler();
        this.cipher = new Cipher();
    }
    public String getFileList() {
        ArrayList<String> files = fileHandler.listFiles();
        if(files.isEmpty()) {
            return "No Files Found";
        }
        else {
            StringBuilder fileList = new StringBuilder();
            int i = 1;
            for (String file : files) {
                fileList.append(String.format("%02d %s\n", i++, file));
            }
            return fileList.toString();
        }
    }
}
