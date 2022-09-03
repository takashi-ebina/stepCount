package util;

import java.io.File;

/**
 * <p>各種ユーティリティメソッドを提供するクラス
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class Util {
	/** 
	 * <p>コンストラクタ
	 * <p>共通クラスのため、インスタンス化は不可とする。
	 */
	private Util() {}
	/** 
	 * <p>ファイル拡張子取得メソッド
	 * <p>Fileオブジェクトからファイルの拡張子を取得し返却する。
	 * 
	 * @param file Fileオブジェクト
	 * @return ファイルの拡張子を文字列型で返却する。引数のFileオブジェクトがnullの場合はnullを返却する。
	 */
	public static String getExtension(File file) {
		if (file == null) {
			return null;
		}
		final String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim(); 
	}
}
