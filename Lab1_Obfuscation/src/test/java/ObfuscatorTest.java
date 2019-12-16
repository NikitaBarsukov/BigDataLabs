import org.junit.Test;

import static org.junit.Assert.*;

public class ObfuscatorTest {

    final byte   KEY             = 8;
    final String WORD            = "random_word";
    final String OBFUSCATED_WORD = "ziflgeW\u007Fgzl";

    @Test
    public void stringObfuscation() {
        Obfuscator obfuscator = new Obfuscator(KEY);
        String result = obfuscator.obfuscate(WORD);
        assertEquals(OBFUSCATED_WORD, result);
    }

    @Test
    public void stringDeobfuscation() {
        Obfuscator obfuscator = new Obfuscator(KEY);
        String result = obfuscator.obfuscate(OBFUSCATED_WORD);
        assertEquals(WORD, result);
    }
}