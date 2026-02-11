/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args) {
        ProgramController programController = new ProgramController();
        UserInterface userInterface = new UserInterface(args, programController);
        System.out.println(userInterface.getReturnMessage());
    }
}
