/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(args);
        System.out.println(userInterface.getReturnMessage());
    }
}
