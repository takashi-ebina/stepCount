package util.validator;

import java.io.File;
import java.util.Scanner;

import constant.Constant.FilePathType;

public class ValidatorUtil {

	private ValidatorUtil() {
		
	}
	public static boolean inputFileCheck(final String inputPath) {
		
		if ("".equals(inputPath)) {
			System.out.println("--> [ERROR]:入力フォルダが指定されていません。");
			return false;
		}
		if (!fileCheck(inputPath, FilePathType.DIRECTORY)) {
			System.out.println("--> [ERROR]:入力フォルダが指定に誤りがあります。");
			return false;
		}
		return true;
	}
	public static boolean outputFileCheck(final String outputPath) {
		
		if ("".equals(outputPath)) {
			System.out.println("--> [ERROR]:出力フォルダが指定されていません。");
			return false;
		}
		if (fileCheck(outputPath, FilePathType.DIRECTORY)) {
			System.out.println("--> [ERROR]:出力指定がフォルダになっています。 ファイルを指定してください");
			return false;
		}
		if (!isExtensionForCsv(new File(outputPath))) {
			System.out.println("--> [ERROR]:拡張子がCSV形式ではありません。");
			return false;
		}
		if (new File(outputPath).exists()) {
			System.out.println("--> ------------------------------------------------");
			System.out.println("--> 該当のファイルが既に存在します。 上書きされてしまいますがよろしいですか？ y / n");
			System.out.println("--> ファイルパス：" + outputPath);
			System.out.println("--> ------------------------------------------------");
			@SuppressWarnings("resource")
			final Scanner sn = new Scanner(System.in);
			while (true) {
				final String decide = sn.next().toLowerCase().trim();
				if ("y".equals(decide)) {
					break;
				} else if ("n".equals(decide)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean fileCheck(final String path, final FilePathType mode) {
		boolean retFlg = false;
		File f = new File(path);
		switch (mode) {
			case DIRECTORY:
				retFlg = f.isDirectory();
				break;
			case FILE:
				if (f.isDirectory()) {
					retFlg = false;
				} else {
					retFlg = f.canRead();
				}
			default:
		}
		return retFlg;
	}
	
	private static boolean isExtensionForCsv(final File f) {
        final String fileName = f.getName();
        final String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim(); 
		return "csv".equals(extension);
	}
}
