import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("""
                    Выберите действие:\s
                    1. Зашифровать\s
                    2. Расшифровать\s
                    3. Подобрать ключ перебором\s
                    4. Расшифровать с помощью статистического анализа\s
                    5. Выход из программы""");

            String answer = console.readLine();

            switch (answer) {
                case "1" -> new EncryptedDecrypted().encryptedDecrypted(true);
                case "2" -> new EncryptedDecrypted().encryptedDecrypted(false);
                case "3" -> System.out.println("3. Подобрать ключ перебором");
                case "4" -> System.out.println("4. Расшифровать с помощью статистического анализа");
                case "5" -> { return; }
            }
        }
    }
}