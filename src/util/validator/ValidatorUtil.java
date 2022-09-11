package util.validator;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

import constant.Constant.ExecuteMode;
import constant.Constant.FilePathType;

/**
 * <p>
 * 入力チェック処理のユーティリティメソッドを提供するクラス
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class ValidatorUtil {
	/**
	 * <p>
	 * コンストラクタ
	 * <p>
	 * ユーティリティメソッドを提供するクラスのため、インスタンス化は不可とする。
	 */
	private ValidatorUtil() {
	}

	/**
	 * <p>
	 * 入力ディレクトリパスチェック
	 * <p>
	 * 以下のチェックを実施する。
	 * <ol>
	 * <li>空文字チェック<br>
	 * <li>ディレクトリ判定チェック<br>
	 * </ol>
	 * 
	 * @param inputPath 入力ディレクトリパス
	 * @return 全ての入力チェックが正常の場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public static boolean inputDirectoryCheck(final String inputPath) {

		if ("".equals(inputPath)) {
			System.out.println("--> 入力フォルダが指定されていません。");
			return false;
		}
		if (!fileCheck(inputPath, FilePathType.DIRECTORY)) {
			System.out.println("--> 入力フォルダが指定に誤りがあります。");
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * 出力ファイルチェック
	 * <p>
	 * 以下のチェックを実施する。
	 * <ol>
	 * <li>空文字チェック<br>
	 * <li>ファイル判定チェック<br>
	 * <li>CSV形式チェック<br>
	 * </ol>
	 * 
	 * @param outputPath 出力ファイルパス
	 * @param mode       コマンドオプション区分
	 * @return 全ての入力チェックが正常の場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public static boolean outputFileCheck(final String outputPath, final ExecuteMode mode) {

		if ("".equals(outputPath)) {
			System.out.println("--> 出力フォルダが指定されていません。");
			return false;
		}
		if (fileCheck(outputPath, FilePathType.DIRECTORY)) {
			System.out.println("--> 出力指定がフォルダになっています。 ファイルを指定してください");
			return false;
		}
		if (!isExtensionForCsv(new File(outputPath))) {
			System.out.println("--> 拡張子がCSVではありません。");
			return false;
		}
		if (mode == ExecuteMode.INTERACTIVE && new File(outputPath).exists()) {
			System.out.println("--> ------------------------------------------------");
			System.out.println("--> 該当のファイルが既に存在します。 上書きされてしまいますがよろしいですか？ y / n");
			System.out.println("--> ファイルパス：" + outputPath);
			System.out.println("--> ------------------------------------------------");
			@SuppressWarnings("resource")
			final Scanner sn = new Scanner(System.in);
			while (true) {
				final String decide = sn.next().toLowerCase().trim();
				if (Objects.equals(decide, "y")) {
					break;
				} else if (Objects.equals(decide, "n")) {
					return false;
				} else {
					System.out.println("--> y または n を入力してください");
				}
			}
		}
		return true;
	}

	/**
	 * <p>
	 * ファイルチェック
	 * <p>
	 * 引数のファイルパスがファイルまたはディレクトリであるかを判定する。<br>
	 * 引数のファイルパスタイプ（{@link FilePathType}）によってチェック内容が変動する。
	 * 
	 * @param path         チェック対象のファイルパス
	 * @param filePathType ファイルパスタイプ<br>
	 *                     {@link FilePathType#DIRECTORY}の場合はディレクトリであるかのチェック処理、<br>
	 *                     {@link FilePathType#FILE}の場合はファイルであるかのチェック処理を行う。<br>
	 * @return 全ての入力チェックが正常の場合はtrueを返却。それ以外の場合はfalseを返却する。
	 * @see FilePathType
	 */
	private static boolean fileCheck(final String path, final FilePathType filePathType) {
		boolean retFlg = false;
		File file = new File(path);
		switch (filePathType) {
		case DIRECTORY:
			retFlg = file.isDirectory();
			break;
		case FILE:
			if (file.isDirectory()) {
				retFlg = false;
			} else {
				retFlg = file.canRead();
			}
			break;
		default:
		}
		return retFlg;
	}

	/**
	 * <p>
	 * CSVファイルチェック
	 * <p>
	 * 引数のファイルの拡張子がCSVであるか判定する。
	 * 
	 * @param file チェック対象のファイル
	 * @return ファイルの拡張子がCSVの場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	private static boolean isExtensionForCsv(final File file) {
		final String fileName = file.getName();
		final String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim();
		return Objects.equals(extension, "csv");
	}
}
