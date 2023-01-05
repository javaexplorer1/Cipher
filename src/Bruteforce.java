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

    public void bruteforce() throws IOException {  // вместо запроса имени второго файла - добавлять суффикс.  C:\Downloads\1_encrypted.txt
        System.out.println("Введите путь к файлу для расшифровки:");
        String path = scanner.nextLine();

        Path newPath = PathHelper.buildFileName(path, "_bruteforced");

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
             BufferedWriter writer = Files.newBufferedWriter(newPath)) {
            StringBuilder builder = new StringBuilder();
            reader.mark((int) Files.size(Paths.get(path)));
            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string);
            }

            for (int i = 1; i <= cesarCipher.alphabetLength(); i++) {
                String decrypt = cesarCipher.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)) {
                    System.out.println("Текст успешно сохранен в файл " + newPath.getFileName());
                    System.out.println("Ключ расшифровки: " + i + System.lineSeparator());

                    reader.reset();
                    while (reader.ready()) {
                        String string = reader.readLine();
                        String encryptedDecrypted = cesarCipher.decrypt(string, i);
                        writer.write(encryptedDecrypted + System.lineSeparator());
                    }
                }
            }
        }
    }

    private boolean isValidateText(String text) {

        String answer = "";

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
        System.out.println(text.substring(0, Math.min(text.length(), 1000)));
        System.out.println("Текст корректно расшифрован?");
        do {
            System.out.print("Если корректно - нажмите Y, если не корректно - нажмите N: ");
            if (scanner.hasNextLine()) {
                answer = scanner.nextLine();
            }
        } while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"));

        return (isValidate && answer.equalsIgnoreCase("y"));
    }
}
