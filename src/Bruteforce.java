import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Bruteforce {

    private final Scanner scanner = new Scanner(System.in);
    private final CesarCipher cesarCipher = new CesarCipher();

    public void bruteforce() throws IOException {  // вместо запроса имени второго файла - добавлять суффикс
        System.out.println("Введите путь к файлу для расшифровки:");
        String pathEncryptFile = scanner.nextLine();
        System.out.println("Введите путь для сохранения расшифрованного файла:");
        String pathNotEncryptFile = scanner.nextLine();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptFile));
             BufferedWriter writer = Files.newBufferedWriter((Paths.get(pathNotEncryptFile)))) {
            StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string);
            }

            for (int i = 0; i < cesarCipher.alphabetLength(); i++) {
                String decrypt = cesarCipher.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)) {
                    System.out.println(decrypt);
                }

            }
        }
    }

    private boolean isValidateText(String text) {
        boolean isValidate = false;

        String[] strings = text.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() > 28) {
                return false;
            }
        }
        if (text.contains(". ") || text.contains(", ") || text.contains("! ") || text.contains("? ")) {
            isValidate = true;
        }
        // написать запрос к пользователю на валидацию текста:  Текст валидный (Y/N)?  + проверка ввода
        // если не валидный: isValidate = false


        return false;
    }
}
