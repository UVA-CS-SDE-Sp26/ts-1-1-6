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
    @Test
    void preservesLowercaseUsingUpperCase(){
        String key = "ABC\nXYZ";
        Cipher svc = new Cipher();

        String out = svc.decipher("xYz", key);
        assertEquals("aBc", out);
    }
    @Test
    void keepsCharactersNotInMapping(){
        String key = "ABC\nXYZ";
        Cipher svc = new Cipher();

        String out = svc.decipher("123-!?", key);
        assertEquals("123-!?", out);
    }
    @Test
    void blankKey(){
        Cipher svc = new Cipher();
        assertThrows(IllegalArgumentException.class, () -> svc.decipher("X", " "));
    }
    @Test
    void lessThanTwoLines(){
        Cipher svc = new Cipher();
        assertThrows(IllegalArgumentException.class, () -> svc.decipher("X", "ABC"));
    }
    @Test
    void mismatchedKeyLengths(){
        Cipher svc = new Cipher();
        assertThrows(IllegalArgumentException.class, () -> svc.decipher("X", "ABC\nXY"));
    }
    @Test
    void duplicateCipherChars(){
        Cipher svc = new Cipher();
        assertThrows(IllegalArgumentException.class, () -> svc.decipher("X", "ABC\nXXY"));
    }
}
