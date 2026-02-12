
public class UserInterface {
    private String[] args;
    ProgramController programController;

    public UserInterface(String[] args, ProgramController programController) {
        this.args = args;
    }

    public String getReturnMessage() {
        if (args.length == 0) {
            return programController.getFileList();
        }
        else if (args.length == 1) {
            return programController.handleFileRequest(args[0], null);
        }
        else if (args.length == 2) {
            return programController.handleFileRequest(args[0], args[1]);
        }
        else {
            return "Error: Too many arguments. Pass up to two arguments.";
        }
    }
}
