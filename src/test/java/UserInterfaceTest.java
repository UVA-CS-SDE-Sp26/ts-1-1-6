import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    ProgramController programController;

    @BeforeEach
    void setup() {
        programController = mock(ProgramController.class);
        when(programController.getFileList()).thenReturn("01 filea.txt\n" + "02 fileb.txt\n" + "03 filec.txt");
        when(programController.handleFileRequest("01", null)).thenReturn("Contents of file 01.");
        when(programController.handleFileRequest("04", null)).thenReturn("Error: Invalid file number.");
        when(programController.handleFileRequest("01", "alt_key.txt")).thenReturn("Deciphered contents of file 01 according to alt_key.txt.");
    }

    @Test
    void getReturnMessageNoArgs() {
        String[] args = {};
        UserInterface userInterface = new UserInterface(args, programController);
        String expected = "01 filea.txt\n" + "02 fileb.txt\n" + "03 filec.txt";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing no arguments should yield a list of files");
    }

    @Test
    void getReturnMessageOneArgument() {
        String[] args = {"01"};
        UserInterface userInterface = new UserInterface(args, programController);
        String expected = "Contents of file 01.";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing one valid argument should yield file contents.");
    }

    @Test
    void getReturnMessageOneArgumentInvalidIndex() {
        String[] args = {"04"};
        UserInterface userInterface = new UserInterface(args, programController);
        String expected = "Invalid Index";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing an invalid index should yield an error.");
    }

    @Test
    void getReturnMessageOneArgumentInvalidNumber() {
        String[] args = {"a"};
        UserInterface userInterface = new UserInterface(args, programController);
        String expected = "Error: Invalid file number.";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing an invalid number should yield an error.");
    }

    @Test
    void getReturnMessageTwoArguments() {
        String[] args = {"01", "alt_key.txt"};
        UserInterface userInterface = new UserInterface(args, programController);
        String expected = "Deciphered contents of file 01 according to alt_key.txt.";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing two arguments should decipher file contents according to alternate key.");
    }

    @Test
    void getReturnMessageAlternateKeyVerification() {
        String[] args = {"01", "alt_key.txt"};
        UserInterface userInterface = new UserInterface(args, programController);
        String unexpected = "Contents of file 01.";
        String actual = userInterface.getReturnMessage();
        assertNotEquals(unexpected, actual, "Passing an alternate key should decipher file contents.");
    }

    @Test
    void getReturnMessageTooManyArguments() {
        String[] args = {"01", "alt_key.txt", "extra"};
        UserInterface userInterface = new UserInterface(args, programController);
        String unexpected = "Error: Too many arguments. Pass up to two arguments.";
        String actual = userInterface.getReturnMessage();
        assertNotEquals(unexpected, actual, "Passing more than two arguments should yield an error.");
    }
}

