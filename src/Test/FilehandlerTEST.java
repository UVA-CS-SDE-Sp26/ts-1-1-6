import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FilehandlerTEST {
    String[] TestListOfFileNames = {"Document1.txt", "SuperSecretSpyDoc.txt", "123_That's_Classified!.txt", "For The President's Eyes Only.txt", "Ciphered.txt"}

    @Test
    void noArgumentsGiven(){
        FileHandler filehandler = new FileHandler();
        assertEquals(TestListOfFileNames, filehandler.readOrListFiles());
    }

    @Test
    void integerArgumentsGiven(){
        FileHandler filehandler = new FileHandler();
        assertEquals("This is the text inside 123_That'sClassified.txt!", filehandler.readOrListFiles(0));
    }

    @Test
    void TestInputingValidIndexBeforeRunningTheList(){
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
    void checkIfDeciphered(){

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