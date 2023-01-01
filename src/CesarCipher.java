public class CesarCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";  // добавить рус алфавит и цифры

    public String encrypt(String message, int key) {
        StringBuilder builder = new StringBuilder();
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = ALPHABET.indexOf(chars[i]);
            if (index >= 0) {
                int newIndex = (index + key) % ALPHABET.length();
                char charAt;
                if (newIndex < 0) {               //в тернарный оператор
                    charAt = ALPHABET.charAt(newIndex + ALPHABET.length());
                } else {
                    charAt = ALPHABET.charAt(newIndex);
                }
                builder.append(charAt);
            }
        }
        return builder.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, key * (-1));
    }


}
