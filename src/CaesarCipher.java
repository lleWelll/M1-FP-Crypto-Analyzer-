public class CaesarCipher {
	// Инициализация алфавита (английский алфавит со специальными символами)
	protected static final char[] ALPHABET = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '(', ')', '!', '?', '.', ',', '-', '_', '"',
			'\'', ':', '\n'};

	// Шифрование оригинального текста шифром цезаря
	public void encrypt(String originalFilePath, String destinationFilePath, int key) {
		char[] originalCharArray = FileManager.readFile(originalFilePath).toCharArray(); //Чтение оригинального текста
		StringBuilder encryptedString = new StringBuilder(); //Строка для записи зашифрованных символов
		for (char currentSymbol : originalCharArray) {
			encryptedString.append(shiftCurrentSymbol(currentSymbol, key)); //Добавление в строку зашифрованный символ
		}
		FileManager.writeFile(encryptedString.toString(), destinationFilePath); //Запись зашифрованного текста
	}

	// Расшифровка текста
	public void decrypt(String originalFilePath, String destinationFilePath, int key) {
		char[] encryptedCharArray = FileManager.readFile(originalFilePath).toCharArray(); //Чтение зашифрованного текста
		StringBuilder decryptedString = new StringBuilder(); //Строка для записи расшифрованных символов
		key *= -1; //Перевод ключа для обратного поиска символа
		for (char currentSymbol : encryptedCharArray) {
			decryptedString.append(shiftCurrentSymbol(currentSymbol, key)); //Добавление в строку расшифрованный символ
		}
		FileManager.writeFile(decryptedString.toString(), destinationFilePath); //Запись расшифрованного текста
	}

	protected int findSymbolPosition(char currentSymbol) {
		int symbolAlphabetPosition; //Индекс числа в алфавите шифрования
		for (int i = 0; i < ALPHABET.length; i++) {
			if (currentSymbol == ALPHABET[i]) {
				symbolAlphabetPosition = i;
				return symbolAlphabetPosition;
			}
		}
		return -1;
	}

	protected char shiftCurrentSymbol(char currentSymbol, int key) {
		int symbolAlphabetPosition = findSymbolPosition(currentSymbol); //Индекс числа в алфавите шифрования
		if (symbolAlphabetPosition == -1) { //Если символ не был найден в алфавите он возвращается в исходном виде
			return currentSymbol;
		}
		int shiftedPosition = symbolAlphabetPosition + key; //Смещения индекса текущего символа на key расстояние
		if (shiftedPosition >= ALPHABET.length) {
			shiftedPosition -= ALPHABET.length;
		} else if (shiftedPosition < 0) {
			shiftedPosition += ALPHABET.length;
		}
		return ALPHABET[shiftedPosition];
	}
}
