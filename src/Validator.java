import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {

		public static boolean isFileExists(String filePath) {
			Path pathToFile = Path.of(filePath);
			try {
				if (! Files.exists(pathToFile)) {
					throw new FileNotFoundException();
				}
				return true;
			} catch (FileNotFoundException e) {
				System.err.println("Such file doesn't exist");
				throw new RuntimeException(e);
			}
		}

		public static boolean isValidKey(int key) {
			int alphabetSize = CaesarCipher.ALPHABET.length; //alphabetSize = 65
			if (key <= 0 || key >= alphabetSize) {
				System.err.println("Invalid key, it should be > 0 and < 65");
				throw new RuntimeException();
			}
			else {
				return true;
			}
		}
}
