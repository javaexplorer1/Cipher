import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encrypted {

    private final Scanner scanner = new Scanner(System.in);
    private final CesarCipher cesarCipher = new CesarCipher();

    public void encrypted() throws IOException {
        System.out.println("Введите путь к файлу для шифрования:");
        String pathNotEncryptFile = scanner.nextLine();
        System.out.println("Введите ключ шифрования:");
        int key = Integer.parseInt(scanner.nextLine());

        Path pathEncrypt = PathHelper.buildFileName(pathNotEncryptFile, "_encrypted");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathNotEncryptFile));
             BufferedWriter writer = Files.newBufferedWriter(pathEncrypt)) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encrypted = cesarCipher.encrypt(string, key);
                writer.write(encrypted + System.lineSeparator());
            }
        }
        System.out.println("Файл " + pathEncrypt.getFileName() + " успешно зашифрован" + System.lineSeparator());
    }
}

