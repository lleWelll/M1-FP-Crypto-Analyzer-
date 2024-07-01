import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BruteForce extends CaesarCipher {
	// Инициализация словаря для проверки слов во время расшифровки
	// Перед каждым словом, стоит пробел для того, чтобы исключить случайные совпадения
	private static final Set<String> DICTIONARY = new HashSet<>(Arrays.asList(" the", "The ", " you", " and", " for", " but",
			" not", " have", "Have", " just", " with", " this", " these", " will", " could", " that"));

	public void bruteForce(String originalFilePath, String destinationFilePath) {
		String decryptedContent;
		int key = 1; //Начально значение ключа
		boolean notCorrectDecryption = true;
		while (notCorrectDecryption) {
			int coincidenceCounter = 0; //Счетчик, который полказыват сколько слов из словаря попались в тексте
			decrypt(originalFilePath, destinationFilePath, key); //Попытка расшифровки с нынешним ключом
			decryptedContent = FileManager.readFile(destinationFilePath); //Считывание расшифрованного текста
			for (String keyWord : DICTIONARY) {
				if (decryptedContent.contains(keyWord)) { //Если в строке содержится слово из словаря то счетчик увеличивается
					coincidenceCounter++;
				}
			}
			if (coincidenceCounter >= 1) {
				notCorrectDecryption = false;
			} else {
				key++;
			}
		}
	}
}
