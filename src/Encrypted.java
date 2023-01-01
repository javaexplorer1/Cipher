import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encrypted {
    public void encrypted () throws IOException {
        System.out.println("Введите путь к файлу для зашифровки:");
        Scanner scanner = new Scanner(System.in);
        String pathNotEncryptFile = scanner.nextLine();
        System.out.println("Введите ключ шифрования:");
        int key = scanner.nextInt();
        System.out.println("Введите путь для сохранения зашифрованного файла:");
        String pathEncryptFile = scanner.nextLine();
        CesarCipher cesarCipher = new CesarCipher();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathNotEncryptFile));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(pathEncryptFile))) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encrypted = cesarCipher.encrypt(string, key);
                writer.write(encrypted);
            }
        }
        System.out.println("Файл успешно зашифрован");
    }
}
