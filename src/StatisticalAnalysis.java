public class StatisticalAnalysis extends CaesarCipher {
	public void statisticalAnalysis(String originalFilePath, String destinationFilePath) {
		int key;
		char[] encryptedContent = FileManager.readFile(originalFilePath).toCharArray();
		char mostCommonSymbol = findMostCommonSymbol(encryptedContent);
		int commonSymbolPosition = findSymbolPosition(mostCommonSymbol); // Нахождение индекса самого популярного символа (пробела)
		key = findKey(commonSymbolPosition);
		decrypt(originalFilePath, destinationFilePath, key);
	}

	// Метод который возвращает самый часто встречающийся символ (предположительно, символ соответсвующий пробелу)
	private char findMostCommonSymbol(char[] text) {
		char mostCommonSymbol = ' ';
		int maxCharCount = Integer.MIN_VALUE;
		int currentCharCount = 0;
		for (int i = 0; i < ALPHABET.length; i++) {
			char symbolFromAlphabet = ALPHABET[i];
			for (char currentSymbol : text) {
				if (currentSymbol == symbolFromAlphabet) {
					currentCharCount++;
				}
			}
			if (currentCharCount > maxCharCount) {
				maxCharCount = currentCharCount;
				mostCommonSymbol = ALPHABET[i];
			}
			currentCharCount = 0;
		}
		return mostCommonSymbol;
	}

	//Метод, который находит ключ, считая отклонение от пробела
	public int findKey(int mostCommonSymbolPosition) {
		int key;
		int spacePosition = findSymbolPosition(' '); //Нахождение индекса пробела в алфавите
		if (mostCommonSymbolPosition > spacePosition) {
			key = mostCommonSymbolPosition - spacePosition;
		} else if (mostCommonSymbolPosition < spacePosition) {
			key = (mostCommonSymbolPosition + ALPHABET.length) - spacePosition;
		} else {
			key = 0;
		}
		return key;
	}

}
