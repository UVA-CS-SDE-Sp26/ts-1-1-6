import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.FileHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FilehandlerTEST {
    String[] TestListOfFileNames = {"Document1.txt", "SuperSecretSpyDoc.txt", "123_That's_Classified!.txt", "For The President's Eyes Only.txt", "Ciphered.txt"}


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