import java.nio.file.Path;


public class PathHelper {

    public static Path buildFileName(String path, String suffix) {
        Path pathPrimary = Path.of(path);
        Path directory = pathPrimary.getParent();
        String fileName = pathPrimary.getFileName().toString();
        String newFileName;
        if (fileName.contains(".")) {
            int indexOfDot = fileName.lastIndexOf(".");
            newFileName = fileName.substring(0, indexOfDot) + suffix +
                    fileName.substring(indexOfDot);
        } else {
            newFileName = fileName + suffix;
        }
        return directory.resolve(Path.of(newFileName));
    }
}