import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;


//makes interface to test the methods that file handler uses
interface MockCipher {
    String decipher(String cipherText, String optionalKeyPath);
}


class FilehandlerTEST {


    private String testResourcePath;
    private FileHandler fileHandler;
    private MockCipher mockCipher;


    String[] TestListOfFileNames = {"Document1.txt", "SuperSecretSpyDoc.txt", "123_That's_Classified!.txt", "For The President's Eyes Only.txt", "Ciphered.txt"}



    @BeforeEach
    void setUp() throws IOException {
        testResourcePath = "";
        //uses mock Cipher class and sets the test directory
        fileHandler = new FileHandler(mockCipher, testResourcePath);
    }

    @Test
    void integerArgumentsGiven(){
        FileHandler filehandler = null;
        try {
            filehandler = new FileHandler();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals("This is the text inside 123_That'sClassified.txt!", filehandler.readFile(0));
    }

    @Test
    void TestInputtingValidIndexBeforeRunningTheList(){
        FileHandler filehandler = new FileHandler();
        assert()
    }

    @Test
    void validIndexWithValidIndex(){}

    @Test
    void validIndexWithInvalidIndex(){}

    @Test
    void validIndexWithValidIntNotCorespondingToATextFile(){
    }

    @Test
    void readFile(){
    }
    @Test
    void PutFileInArray(){
    }

    @Test
    void TestWithInvalidIndex(){}

    @Test
    void TestWithValidIndex(){}

    @Test
    void FirstFileInList(){}

    @Test
    void LastFileInList(){}

    @Test
    void TestArrayOfFileNames(){}

    @Test
    void TestWeirdFileNames(){}

    @Test
    void TestIfDataFolderIsMissingOREmpty(){}






}