import java.nio.file.Path;
import java.util.Random;

public class CesarCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ !#$%&()—*+,-./:;<=>?@[]^_{|}~"; //   C:\Downloads\1.txt

    public String encrypt(String message, int key) {

        StringBuilder builder = new StringBuilder();
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = ALPHABET.indexOf(chars[i]);

            if (index >= 0) {
                int newIndex = (index + key) % ALPHABET.length();
                char charAt;
                charAt = (newIndex < 0) ? ALPHABET.charAt(newIndex + ALPHABET.length()) : ALPHABET.charAt(newIndex);
                builder.append(charAt);
            }
        }
        return builder.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, key * (-1));
    }

    public int alphabetLength() {
        return ALPHABET.length();
    }
}

