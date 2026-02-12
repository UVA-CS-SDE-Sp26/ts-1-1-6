import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramControllerTest {
    @Test
    public void testGetFileListFormatting() { //testing if the format of the list displayed is as expected by Pat (starting with 01, 02, 03...)
        ProgramController controller = new ProgramController();
        String result = controller.getFileList();
        if (!result.equals("No Files Found")) {
            assertTrue(result.startsWith("01"), "Output should start with 01");
        } else {
            assertEquals("No Files Found", result);
        }
    }
    @Test
    public void testHandleFileRequestInvalidFormat() { //checks if the handleFileRequest returns the expected error if it isn't given a number
        ProgramController controller = new ProgramController();
        String result = controller.handleFileRequest("abc", null);
        assertEquals("Error: Invalid file number.", result);
    }
    @Test
    public void testHandleFileRequestWithAlternateKey() { //tests handleFileRequest with a key
        ProgramController controller = new ProgramController();
        String customKey = "abcdefghijklmnopqrstuvwxyz\nzyxwvutsrqponmlkjihgfedcba";
        String result = controller.handleFileRequest("1", customKey);
        assertNotNull(result);
        assertNotEquals("Invalid Index", result);
    }
    @Test
    public void testHandleFileRequestWithoutAlternateKey() { //tests handleFileRequest without a key
        ProgramController controller = new ProgramController();
        String result = controller.handleFileRequest("1", null);
        assertNotNull(result);
        assertNotEquals("Invalid Index", result);
    }
}
