import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parsing {

    public void parser() throws IOException {
        System.out.println("Введите полный адрес зашифрованного файла");
        Scanner scanner = new Scanner(System.in);
        String pathEncrypted = scanner.nextLine();
        System.out.println("Введите полный адрес файла с открытым текстом");
        String pathOpen = scanner.nextLine();
        Map<Character, Integer> encrypted = new HashMap<>();
        Map<Character, Integer> open = new HashMap<>();
        Map<Character, Integer> fillEncrypted = fillMapValues(encrypted, pathEncrypted);
        Map<Character, Integer> fillOpen = fillMapValues(open, pathOpen);
        Set<Map.Entry<Character, Integer>> entries = fillEncrypted.entrySet();
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(entries);
//        Collections.sort(list, );




    }

    private Map<Character, Integer> fillMapValues (Map<Character, Integer> map, String path) throws IOException{

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

}
