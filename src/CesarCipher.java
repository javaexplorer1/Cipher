import java.nio.file.Path;
import java.util.Random;

public class CesarCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ !#$%&()—*+,-./:;<=>?@[]^_{|}~"; // добавить !#$%&()*+,-./:;<=>?@[]^_{|}~   \\\"#$%&'()*+,-./:;<=>?@[]^_`{|}~  C:\Downloads\1.txt

    public String encrypt(String message, int key) {

        StringBuilder builder = new StringBuilder();
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = ALPHABET.indexOf(chars[i]);
//            if (Character.isWhitespace(chars[i])) {
//                builder.append(chars[i]);
//            }
            if (index >= 0) {
                int newIndex = (index + key) % ALPHABET.length();
                char charAt;
//                if (newIndex < 0) {               //в тернарный оператор
//                    charAt = ALPHABET.charAt(newIndex + ALPHABET.length());
//                } else {
//                   charAt = ALPHABET.charAt(newIndex);
//            }
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


    public String buildFileName(String path, String suffix) {
        Path pathPrimary = Path.of(path);
        String directory = pathPrimary.getParent().toString();
        String fileNamePrimaryString = pathPrimary.getFileName().toString();
        String newFileName;
        if (fileNamePrimaryString.contains(".")) {
            int indexOfDot = fileNamePrimaryString.lastIndexOf(".");
            newFileName = fileNamePrimaryString.substring(0, indexOfDot) + suffix +
                    fileNamePrimaryString.substring(indexOfDot);
        } else {
            newFileName = fileNamePrimaryString + suffix;
        }
        return directory + "\\" + newFileName;
    }

 /*   public static String shaffleString(String alphabet) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (builder.length() < alphabet.length()) {
            int index = random.nextInt(alphabet.length());
            if (builder.indexOf(String.valueOf(alphabet.charAt(index))) == -1) {
                builder.append(alphabet.charAt(index));
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String stringShaffled = shaffleString(ALPHABET);
        System.out.println(ALPHABET);
        System.out.println(stringShaffled);
        System.out.println("ALPHABET length: " + ALPHABET.length() + "; stringShaffled length: " + stringShaffled.length());
    } */
}

