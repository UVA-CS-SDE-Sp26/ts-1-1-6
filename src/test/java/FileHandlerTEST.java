import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class FilehandlerTEST {


    private FileHandler fileHandler;

    // Helper data to match your requirements
    String[] testListOfFileNames = {"Document1.txt", "SuperSecretSpyDoc.txt", "123_That's_Classified!.txt", "For The President's Eyes Only.txt", "Ciphered.txt"};

    private FilehandlerTEST() {
    }

    static FilehandlerTEST createFilehandlerTEST() {
        return new FilehandlerTEST();
    }

    @BeforeEach
    void setUp() throws IOException {
        String testRoot = "src/test/resources/";
        fileHandler = new FileHandler(testRoot);
    }


    @Test
    void testReadFileToStringMultiLineIntegrity() {
        ArrayList<String> files = fileHandler.listFiles();
        int index = files.indexOf("For The President's Eyes Only.txt");

        assertTrue(index != -1, "Test file 'For The President's Eyes Only.txt' must exist in data folder");
        //find SuperSecretSpyDoc.txt in the test resources folder
        String result = fileHandler.readFile(index);

        assertTrue(result.contains("\n"), "The reader should preserve newlines between file lines");

        String expected = "Line 1 Content\nLine 2 Content\nLine 3 Content";
        assertEquals(expected, result.trim(), "The multi-line string should match the file content exactly");
    }


    @Test
    void testWithValidIndex() {
        // Index 0: Should correspond to 123_That's_Classified!.txt
        String result = fileHandler.readFile(0);

        String expectedRaw = "tThis is the text inside 123_That'sClassified.tx";
        assertNotNull(result);
        assertEquals(expectedRaw, result);
    }

    @Test
    void testWithInvalidIndex() {
        String result = fileHandler.readFile(999);
        assertEquals("Invalid Index", result);
    }
    @Test
    void testWithNegativeIndex() {
        String result = fileHandler.readFile(-1);
        assertEquals("Invalid Index", result);
    }


    /*

    //removing old test cases from when FileHandler was deciphering files
    @Test
    void testDecipherReceivesCorrectInputs() {
        // 1. Arrange: Define what is actually inside your test files
        String expectedContentInFile = "tThis is the text inside 123_That'sClassified.tx";

        // 2. Act: Call the method
        fileHandler.readFile(0);

        //check that proper inputs are placed into decipher
        verify(mockCipher).decipher(eq(expectedContentInFile));
    }

     */
    //New test that proper text is returned
    @Test
    void testReadFileReturnsText() {
        String result = fileHandler.readFile(0);


        String expectedRaw = "tThis is the text inside 123_That'sClassified.tx";
        assertEquals(expectedRaw, result, "FileHandler should return the undeciphered content from disk.");
    }

    @Test
    void testEmptyFileContent() {
        ArrayList<String> files = fileHandler.listFiles();
        int index = files.indexOf("EmptyFile.txt");

        // Ensure the file was actually found in the list first
        assertTrue(index != -1, "EmptyFile.txt must exist in src/test/resources/data/ for this test");

        String result = fileHandler.readFile(index);

        // This proves your readFileToString helper handled the empty scanner correctly.
        assertEquals("", result, "FileHandler should return an empty string for empty files.");
    }



    @Test
    void firstFileInList() {
        String result = fileHandler.readFile(0);

        String expectedRawContent = "tThis is the text inside 123_That'sClassified.tx";

        assertNotNull(result, "The result should not be null for a valid file index");
        assertEquals(expectedRawContent, result, "FileHandler should return the raw ciphered text for index 0");
    }

    @Test
    void lastFileInList() {
        ArrayList<String> files = fileHandler.listFiles();
        if (!files.isEmpty()) {
            int lastIndex = files.size() - 1;
            String result = fileHandler.readFile(lastIndex);

            // Matches the content of your last test file
            assertEquals("elast fil", result);
        }
    }

    @Test
    void testArrayOfFileNames() {
        //tests that the strings are all file names
        ArrayList<String> files = fileHandler.listFiles();
        assertNotNull(files, "The file list should not be null");
        // Verify that the files (Strings) end with .txt
        for (String fileName : files) {
            assertTrue(fileName.endsWith(".txt"));
        }
    }

    @Test
    void testIfDataFolderIsMissingOrEmpty() {
        // This test checks how File Handler handles the data folder being empty
        // If the folder is empty, listFiles() should return an empty list, not crash
        ArrayList<String> files = fileHandler.listFiles();
        assertNotNull(files);
    }


    @Test
    void testReadKeyReturnsNullOnMissingFile() {
        FileHandler invalidPath = new FileHandler("invalid/path/");

        String result = invalidPath.readKey();
        assertNull(result, "Should return null for missing files so the Cipher doesn't use an error message as a key.");
    }

    @Test
    void readKey() {
        String key = fileHandler.readKey();

        assertNotNull(key);
        assertFalse(key.isEmpty());

        // IMPORTANT: Note the \n between the two lines to match your teammate's split("\\R")
        String expectedKey = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890\n" +
                "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a";

        // We use .strip() or .trim() depending on your FileHandler's readFileToString implementation
        assertEquals(expectedKey.trim(), key.trim(), "Key should match the test resource format exactly");
    }

    //Updated test to match with cipher class's formating
    @Test
    void testReadKeyFormatForCipherCompatibility() {
        String key = fileHandler.readKey();

        assertNotNull(key, "readKey() should return null if missing, not crash");

        //matched test to Cipher class's check for line breaks
        String[] lines = key.split("\\R");
        //cipher class is expecting 2 lines
        assertEquals(2, lines.length, "Key must have exactly two lines for the Cipher to work");

      //cipher throws an error if the lines are different lengths
        assertEquals(lines[0].length(), lines[1].length(),
                "The plain text line and cipher line in key.txt must be the same length");
        //checks the first line with the test file's key
        assertTrue(lines[0].contains("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"), "First line should contain the alphabet sequence");
    }



}