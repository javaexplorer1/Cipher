import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parsing {

    private final Scanner scanner = new Scanner(System.in);

    private final Map<Character, Integer> encrypted = new HashMap<>();
    private final Map<Character, Integer> open = new HashMap<>();
    private final Map<Character, Character> decrypted = new HashMap<>();

    public void parser() throws IOException {
        System.out.println("Введите полный адрес зашифрованного файла");
        String pathEncrypted = scanner.nextLine();
        System.out.println("Введите полный адрес файла с открытым текстом");
        String pathOpen = scanner.nextLine();
        Path newPath = PathHelper.buildFileName(pathEncrypted, "_parsing");

        List<Map.Entry<Character, Integer>> listEncrypted = mapToList(fillMapValues(encrypted, pathEncrypted));
        List<Map.Entry<Character, Integer>> listOpen = mapToList(fillMapValues(open, pathOpen));

        if (listEncrypted.size() <= listOpen.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                decrypted.put(listEncrypted.get(i).getKey(), listOpen.get(i).getKey());
            }
        } else {
            System.out.println("Размер файла статистики недостаточен, необходимо больше символов");
        }

        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathEncrypted));
             BufferedWriter writer = Files.newBufferedWriter(newPath)) {
            while (reader.ready()) {
                StringBuilder builder = new StringBuilder();
                String string = reader.readLine();
                for (char encryptedChar : string.toCharArray()) {
                    Character decryptedChar = decrypted.get(encryptedChar);
                    builder.append(decryptedChar);
                }
                writer.write(builder + System.lineSeparator());
            }
        }
        System.out.println("Текст успешно сохранен в файл " + newPath.getFileName() + System.lineSeparator());
    }

    private Map<Character, Integer> fillMapValues(Map<Character, Integer> map, String path) throws IOException {

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string);
            }
        }
        for (char aChar : builder.toString().toCharArray()) {
            if (!map.containsKey(aChar)) {
                map.put(aChar, 1);
            } else {
                map.put(aChar, map.get(aChar) + 1);
            }
        }
        return map;
    }

    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(entries);
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());

//        @Override
//        public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
//            return o2.getValue()- o1.getValue();
//        }

        return list;
    }
}
