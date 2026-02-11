import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    @Test
    void getReturnMessageNoArgs() {
        String[] args = {};
        ProgramController programController = mock(ProgramController.class);
        UserInterface userInterface = new UserInterface(args, programController);
    }
}