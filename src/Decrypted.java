import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Decrypted {
    public void decrypted () throws IOException {          // найти и исправить ошибку, добавить перенос строк
        System.out.println("Введите путь к файлу для расшифровки:");
        Scanner scanner = new Scanner(System.in);
        String pathEncryptFile = scanner.nextLine();
        System.out.println("Введите ключ расшифрования:");
        int key = scanner.nextInt();
        System.out.println("Введите путь для сохранения расшифрованного файла:");
        String pathNotEncryptFile = scanner.nextLine();
        CesarCipher cesarCipher = new CesarCipher();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathEncryptFile));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(pathNotEncryptFile))) {
            while (reader.ready()) {
                String string = reader.readLine();
                String decrypted = cesarCipher.decrypt(string, key);
                writer.write(decrypted);
            }
        }
        System.out.println("Файл успешно расшифрован");

    }
}
