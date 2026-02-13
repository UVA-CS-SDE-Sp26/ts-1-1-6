import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    ProgramController mockProgramController;
    ProgramController trueProgramController;

    @BeforeEach
    void setup() {
        mockProgramController = mock(ProgramController.class);
        when(mockProgramController.getFileList()).thenReturn("01 filea.txt\n" + "02 fileb.txt\n" + "03 filec.txt");
        when(mockProgramController.handleFileRequest("01", null)).thenReturn("Contents of file 01.");
        when(mockProgramController.handleFileRequest("04", null)).thenReturn("Invalid Index");
        when(mockProgramController.handleFileRequest("01", "alt_key.txt")).thenReturn("Deciphered contents of file 01 according to alt_key.txt.");
        when(mockProgramController.handleFileRequest("a", null)).thenReturn("Error: Invalid file number.");

        trueProgramController = new ProgramController(); // used for integration testing
    }

    @Test
    void getReturnMessageNoArgs() {
        String[] args = {};
        UserInterface userInterface = new UserInterface(args, mockProgramController);
        String expected = "01 filea.txt\n" + "02 fileb.txt\n" + "03 filec.txt";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing no arguments should yield a list of files");
    }

    @Test
    void getReturnMessageOneArgument() {
        String[] args = {"01"};
        UserInterface userInterface = new UserInterface(args, mockProgramController);
        String expected = "Contents of file 01.";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing one valid argument should yield file contents.");
    }

    @Test
    void getReturnMessageOneArgumentInvalidIndex() {
        String[] args = {"04"};
        UserInterface userInterface = new UserInterface(args, mockProgramController);
        String expected = "Invalid Index";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing an invalid index should yield an error.");
    }

    @Test
    void getReturnMessageOneArgumentInvalidNumber() {
        String[] args = {"a"};
        UserInterface userInterface = new UserInterface(args, mockProgramController);
        String expected = "Error: Invalid file number.";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing an invalid number should yield an error.");
    }

    @Test
    void getReturnMessageTwoArguments() {
        String[] args = {"01", "alt_key.txt"};
        UserInterface userInterface = new UserInterface(args, mockProgramController);
        String expected = "Deciphered contents of file 01 according to alt_key.txt.";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing two arguments should decipher file contents according to alternate key.");
    }

    @Test
    void getReturnMessageAlternateKeyVerification() {
        String[] args = {"01", "alt_key.txt"};
        UserInterface userInterface = new UserInterface(args, mockProgramController);
        String unexpected = "Contents of file 01.";
        String actual = userInterface.getReturnMessage();
        assertNotEquals(unexpected, actual, "Passing an alternate key should decipher file contents.");
    }

    @Test
    void getReturnMessageTooManyArguments() {
        String[] args = {"01", "alt_key.txt", "extra"};
        UserInterface userInterface = new UserInterface(args, mockProgramController);
        String unexpected = "Error: Too many arguments. Pass up to two arguments.";
        String actual = userInterface.getReturnMessage();
        assertEquals(unexpected, actual, "Passing more than two arguments should yield an error.");
    }

    // Integration Tests

    @Test
    void getReturnMessageNoArgsIntegrated() {
        String[] args = {};
        UserInterface userInterface = new UserInterface(args, trueProgramController);
        String expected = "01 123_That'sClassified.txt\n" + "02 CustomCipher.txt\n" + "03 Ciphered.txt\n" + "04 For The President's Eyes Only.txt\n" + "05 Document1.txt\n" + "06 SuperSecretSpyDoc.txt\n";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing no arguments should yield a list of files");
    }

    @Test
    void getReturnMessageOneArgumentIntegrated() {
        String[] args = {"01"};
        UserInterface userInterface = new UserInterface(args, trueProgramController);
        String expected = "Sghr hr sgd sdws hmrhcd Z12_Sg0s'rBk0rrhehdc.sws!";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing one valid argument should yield file contents deciphered according to the default key.");
    }

    @Test
    void getReturnMessageOneArgumentInvalidIndexIntegrated() {
        String[] args = {"07"};
        UserInterface userInterface = new UserInterface(args, trueProgramController);
        String expected = "Invalid Index";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing an invalid index should yield an error.");
    }

    @Test
    void getReturnMessageOneArgumentInvalidNumberIntegrated() {
        String[] args = {"a"};
        UserInterface userInterface = new UserInterface(args, trueProgramController);
        String expected = "Error: Invalid file number.";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing an invalid number should yield an error.");
    }

    @Test
    void getReturnMessageTwoArgumentsIntegrated() {
        String[] args = {"01", "alt_key.txt"};
        UserInterface userInterface = new UserInterface(args, trueProgramController);
        String expected = "Tjku ku vjg vgzv kpukfg 123_Tjcv'uCncuukhkgf.vzv!";
        String actual = userInterface.getReturnMessage();
        assertEquals(expected, actual, "Passing two arguments should decipher file contents according to alternate key.");
    }

    @Test
    void getReturnMessageAlternateKeyVerificationIntegrated() {
        String[] args = {"01", "alt_key.txt"};
        UserInterface userInterface = new UserInterface(args, trueProgramController);
        String unexpected = "Contents of file 01.";
        String actual = userInterface.getReturnMessage();
        assertNotEquals(unexpected, actual, "Passing an alternate key should decipher file contents.");
    }

    @Test
    void getReturnMessageTooManyArgumentsIntegrated() {
        String[] args = {"01", "alt_key.txt", "extra"};
        UserInterface userInterface = new UserInterface(args, trueProgramController);
        String unexpected = "Error: Too many arguments. Pass up to two arguments.";
        String actual = userInterface.getReturnMessage();
        assertEquals(unexpected, actual, "Passing more than two arguments should yield an error.");
    }
}

