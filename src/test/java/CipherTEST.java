import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CipherTEST {

    @Test
    void substitutionAndPunctuation() {
        // plain - ABC
        // cipher - XYZ
        String key = "ABC\nXYZ";
        Cipher svc = new Cipher();

        String out = svc.decipher("Y Z, X!", key);
        assertEquals("B C, A!", out);
    }
}
