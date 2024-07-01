/*
	Version 1.0 (01.07.24)
 */

import java.util.Scanner;

//Класс, содержащий метод main для запуска программы
public class CryptoAnalyzer {

	public static String originalFilePath; //Файл с оригинальным текстом
	public static String encryptedFilePath; //Файл для записи зашифрованного текста
	public static String optionalFilePath; //Файл для записи расшифрованного текста

	public static int key;

	public static void main(String[] args) {
		CaesarCipher cipher = new CaesarCipher();
		enteringFilePaths();
		keyEntering();
		cipher.encrypt(originalFilePath, encryptedFilePath, key);
		System.out.println("File encrypted with key = " + key);
		switch (modeChoice()) {
			case 1:
				keyEntering();
				cipher.decrypt(encryptedFilePath, optionalFilePath, key);
				System.out.println("File successfully decrypted");
				break;
			case 2:
				BruteForce brute = new BruteForce();
				brute.bruteForce(encryptedFilePath, optionalFilePath);
				System.out.println("File successfully decrypted");
				break;
			case 3:
				StatisticalAnalysis statistical = new StatisticalAnalysis();
				statistical.statisticalAnalysis(encryptedFilePath, optionalFilePath);
				System.out.println("File successfully decrypted");
				break;
			default:
				break;
		}
	}

	//Метод для реализации меню выбора
	private static int modeChoice() {
		System.out.println("Enter number 1 for decrypt with known key text \n2 for brute force\n" +
				"3 for statistical analysis, recommended for large texts\n0 for exit");
		Scanner scan = new Scanner(System.in);
		if (!scan.hasNextInt()) {
			System.err.println("Not a correct number");
			return 0;
		}
		return scan.nextInt();
	}

	//Инициализация файлов для чтения и записи
	private static void enteringFilePaths() {
		Scanner scan = new Scanner(System.in);
		String input;
		System.out.println("Enter path to file with original text (\"src.txt\" or other .txt file on your pc):");
		input = scan.nextLine();
		if (Validator.isFileExists(input)) {
			originalFilePath = input;
		}
		System.out.println("Enter path where to encrypt the text (\"dest.txt\" or other .txt file on your pc):");
		input = scan.nextLine();
		if (Validator.isFileExists(input)) {
			encryptedFilePath = input;
		}
		System.out.println("Enter path where to decrypt text (\"opt.txt\" or other .txt file on your pc):");
		input = scan.nextLine();
		if (Validator.isFileExists(input)) {
			optionalFilePath = input;
		}
	}

	//Инициализация ключа
	private static void keyEntering() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter key value (it should be >= 1 and <= 64)");
		int keyValue = scan.nextInt();
		if (Validator.isValidKey(keyValue)) {
			key = keyValue;
		}
	}
}
