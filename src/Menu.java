import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Выберите действие: \n" +
                    "1. Зашифровать \n" +
                    "2. Расшифровать \n" +
                    "3. Подобрать ключ перебором \n" +
                    "4. Расшифровать с помощью статистическгог анализа \n" +
                    "5. Выход из программы");

            String answer = console.readLine();

            switch (answer) {
                case "1" -> System.out.println("Зашифровать");
                case "2" -> System.out.println("Расшифровать");
                case "3" -> System.out.println("3. Подобрать ключ перебором");
                case "4" -> System.out.println("4. Расшифровать с помощью статистическго анализа");
                case "5" -> { return; }
            }
        }
    }
}