import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilehandlerTEST {

    @Mock
    private Cipher mockCipher;

    private FileHandler fileHandler;

    // Helper data to match your requirements
    String[] testListOfFileNames = {"Document1.txt", "SuperSecretSpyDoc.txt", "123_That's_Classified!.txt", "For The President's Eyes Only.txt", "Ciphered.txt"};

    @BeforeEach
    void setUp() throws IOException {
        String testRoot = "src/test/resources/";
        // We pass the mocked cipher into the handler
        fileHandler = new FileHandler(mockCipher, testRoot);
    }


    @Test
    void testWithValidIndex() {
        // Arrange: Stub the mock to return a specific string
        when(mockCipher.decipher("tThis is the text inside 123_That'sClassified.tx")).thenReturn("This is the text inside 123_That'sClassified.txt!");

        // Index 0: 123_That'sClassified.txt
        String result = fileHandler.readFile(0);


        assertNotNull(result);
        assertEquals("This is the text inside 123_That'sClassified.txt!", result);
    }

    @Test
    void testWithInvalidIndex() {
        // Act: Use an obviously out-of-bounds index
        String result = fileHandler.readFile(999);

        // Assert: Based on your pseudocode, it should return "Invalid Index" or null
        // If your code returns null, change this to assertNull(result);
        assertEquals("Invalid Index", result);
    }
    @Test
    void testWithNegativeIndex() {
        String result = fileHandler.readFile(-1);
        assertEquals("Invalid Index", result);
    }

    @Test
    void testSpecialCharacterFilenameReading() {
        int index = fileHandler.listFiles().indexOf("123_That's_Classified!.txt");

        fileHandler.readFile(index);

        verify(mockCipher).decipher(contains("Classified"), anyString());
    }
    @Test
    void testDecipherReceivesCorrectInputs() {
        // 1. Arrange: Define what is actually inside your test files
        String expectedContentInFile = "tThis is the text inside 123_That'sClassified.tx";

        // 2. Act: Call the method
        fileHandler.readFile(0);

        //check that proper inputs are placed into decipher
        verify(mockCipher).decipher(eq(expectedContentInFile));
    }

    @Test
    void testEmptyFileContent() {
        // Assuming "EmptyFile.txt" is in your test resources
        // find the index of EmptyFile.txt
        int index = fileHandler.listFiles().indexOf("EmptyFile.txt");

        fileHandler.readFile(index);

        // Verify that it passes an empty string, not null, to the cipher
        verify(mockCipher).decipher(eq(""), anyString());
    }

    //tests what happens if the file name in the arrayList<> coresponds to a file that's been deleted
    @Test
    void testReadFileThrowsException() {
        // Create a handler with a fake path to force an IOException in the try block
        FileHandler brokenHandler = new FileHandler(mockCipher, "non/existent/path");

        // We need to manually add a name so validIndex returns true,
        // but the actual file read fails.
        // (Note: If NamesOfFiles is private, you might just test
        // the behavior when the folder path is wrong during listFiles)

        String result = brokenHandler.readFile(0);
        assertTrue(result.contains("Error")); // Asserts the catch block was hit
    }

    @Test
    void firstFileInList() {
        // Verify index 0 is handled correctly
        when(mockCipher.decipher("tThis is the text inside 123_That'sClassified.tx")).thenReturn("This is the text inside 123_That'sClassified.txt!");
        assertEquals("This is the text inside 123_That'sClassified.txt!", fileHandler.readFile(0));
    }
    @Test
    void lastFileInList() {
        ArrayList<String> files = fileHandler.listFiles();
        if (!files.isEmpty()) {
            int lastIndex = files.size() - 1;

            fileHandler.readFile(lastIndex);

            verify(mockCipher).decipher(eq("elast fil"), anyString());
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
    void readFile() {
        //ensure the decipher method is actually called
        fileHandler.readFile(0);
        // Verify that the cipher's decipher method was invoked exactly once
        verify(mockCipher, times(1)).decipher(anyString(), anyString());
    }

    @Test
    void readKey() {
        //runs the readKey() function
        String key = fileHandler.readKey();

        //Tests that key isn't empty
        assertNotNull(key);
        assertFalse(key.isEmpty(), "The test key file should not be empty");

        // The expected key from the resources/ciphers/key.txt
        String expectedKey = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890" +
                "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a";
        //tests that key is the same as the expected output
        assertEquals(expectedKey, key, "Key should match the test resource format exactly");
    }
}