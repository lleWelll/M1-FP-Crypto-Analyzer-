import java.io.*;

public class FileManager {
	public static String readFile(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			StringBuilder fileContent = new StringBuilder();
			while (reader.ready()) {
				fileContent.append((char) reader.read());
			}
			return fileContent.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeFile(String content, String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
