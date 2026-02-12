import java.util.ArrayList;

public class ProgramController {
    private final FileHandler fileHandler;
    private final Cipher cipher;

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
    public String handleFileRequest(String fileNumStr, String altKey) {
        try {
            int fileNumber = Integer.parseInt(fileNumStr);
            int index = fileNumber - 1;

            String thing = fileHandler.readFile(index);

            if (thing.equals("Invalid Index")) {
                return thing;
            }

            String usefulKey;
            if (altKey != null) {
                usefulKey = altKey;
            } else {
                usefulKey = fileHandler.readKey();
            }

            return cipher.decipher(thing, usefulKey);

        }
        catch (NumberFormatException e) {
            return "Error: Invalid file number.";
        }
        catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
