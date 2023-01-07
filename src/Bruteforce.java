import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Bruteforce {

    private final Scanner scanner = new Scanner(System.in);
    private final CesarCipher cesarCipher = new CesarCipher();

    public void bruteforce() throws IOException {  // C:\Downloads\1_encrypted.txt
        System.out.println("Введите путь к файлу для расшифровки:");
        String path = scanner.nextLine();

        Path newPath = PathHelper.buildFileName(path, "_bruteforced");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(newPath)) {
            StringBuilder builder = new StringBuilder();
            List<String> list = new ArrayList<>();
            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string);
                list.add(string);
            }

            for (int i = 0; i < cesarCipher.alphabetLength(); i++) {
                String decrypt = cesarCipher.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)) {

                    for (String string : list) {
                        String encrypt = cesarCipher.decrypt(string, i);
                        writer.write(encrypt + System.lineSeparator());
                    }
                    System.out.println("Текст успешно сохранен в файл " + newPath.getFileName());
                    System.out.println("Ключ расшифровки: " + i + System.lineSeparator());
                    break;
                }
            }
        }
    }

    private boolean isValidateText(String text) {

        boolean isValidate = false;

        for (String string : text.split(" ")) {
            if (string.length() > 28) {
                return false;
            }
        }
        // проверить корректную работу при использовании метода matcher
        if (text.contains(". ") || text.contains(", ") || text.contains("! ") || text.contains("? ")) {
            isValidate = true;
        }

        while (isValidate) {
            System.out.println(text.substring(0, Math.min(text.length(), 1000)));
            System.out.println("Текст корректно расшифрован? (Y/N)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                isValidate = false;
            } else {
                System.out.println("Выберите Y или N");
            }
        }
        return false;
    }
}
