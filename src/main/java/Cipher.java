import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cipher {
    public String decipher(String cipherText, String keyText) {
        if (cipherText == null) {
            cipherText = "";
        }
        KeyMapping mapping = validateKey(keyText);
        return applyDecipher(cipherText, mapping);
    }

    private static class KeyMapping{
        final Map<Character, Character> cipherToPlain;
        KeyMapping(Map<Character, Character> cipherToPlain){
            this.cipherToPlain = cipherToPlain;
        }
    }

    private KeyMapping validateKey(String keyText){
        if (keyText == null || keyText.isBlank()){
            throw new IllegalArgumentException("Key text is missing or blank.");
        }

        // Split on any line break type
        String[] lines = keyText.split("\\R", -1);
        if (lines.length < 2){
            throw new IllegalArgumentException("Key text must contain at least two lines.");
        }
        String plain = lines[0];
        String cipher = lines[1];
        if (plain.isEmpty() || cipher.isEmpty()){
            throw new IllegalArgumentException("Key lines must not be empty.");
        }
        if (plain.length() != cipher.length()){
            throw new IllegalArgumentException("Key lines must be the same length.");
        }

        // Checking for duplicates
        Set<Character> seenCipher = new HashSet<>();
        for (char c : cipher.toCharArray()){
            if(!seenCipher.add(c)){
                throw new IllegalArgumentException("Duplicate cipher character found: '" + c + "'");
            }
        }
        // Plain mapping (building cipher)
        Map<Character, Character> cipherToPlain = new HashMap<>();
        for(int i = 0; i < plain.length(); i++){
            cipherToPlain.put(cipher.charAt(i), plain.charAt(i));
        }
        return new KeyMapping(cipherToPlain);
    }

    private String applyDecipher(String input, KeyMapping mapping){
        StringBuilder out = new StringBuilder(input.length());
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            Character direct = mapping.cipherToPlain.get(ch);
            if (Character.isLowerCase(ch)){
                char upper = Character.toUpperCase(ch);
                Character upperMapped = mapping.cipherToPlain.get(upper);
                if (direct != null) {
                    out.append(direct);
                    continue;
                }
            }
            out.append(ch);
        }
        return out.toString();
    }
}
