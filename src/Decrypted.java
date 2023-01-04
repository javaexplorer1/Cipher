import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Decrypted {

    private final Scanner scanner = new Scanner(System.in);
    private final CesarCipher cesarCipher = new CesarCipher();

    public void decrypted() throws IOException {
        System.out.println("Введите путь к файлу для расшифровки:");
        String pathEncryptFile = scanner.nextLine();
        System.out.println("Введите ключ расшифрования:");
        int key = Integer.parseInt(scanner.nextLine());

        Path pathNotEncrypt = PathHelper.buildFileName(pathEncryptFile, "_decrypted");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathEncryptFile));
             BufferedWriter writer = Files.newBufferedWriter(pathNotEncrypt)) {
            while (reader.ready()) {
                String string = reader.readLine();
                String decrypted = cesarCipher.decrypt(string, key);
                writer.write(decrypted + System.lineSeparator());
            }
        }
        System.out.println("Файл " + pathNotEncrypt.getFileName() + " успешно расшифрован" + System.lineSeparator());
    }
}
