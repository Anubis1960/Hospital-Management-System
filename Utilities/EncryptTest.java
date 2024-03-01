package Utilities;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EncryptTest {

    @org.junit.jupiter.api.Test
    void toHexString() {
        String password = "root";
        String expected = "4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2";
        String actual = Encrypt.toHexString(Encrypt.encrypt(password));
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void toHexString2() {
        String password = "ceva";
        String expected = "7216976198393b5167a9cac05919f46512f709d54ced2b71794c626e81fed230";
        String actual = Encrypt.toHexString(Encrypt.encrypt(password));
        assertEquals(expected, actual);
    }
}