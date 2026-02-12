/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args) {
        ProgramController programController = new ProgramController();
        UserInterface userInterface = new UserInterface(args, programController);
        System.out.println(userInterface.getReturnMessage());

        FileHandler fileHandler = new FileHandler();
        fileHandler.listFiles();
        System.out.println(fileHandler.listFiles());
        System.out.println(fileHandler.readFile(3));

    }
}
