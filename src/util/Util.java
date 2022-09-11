package util;

import java.io.File;
import java.util.ArrayList;

/**
 * <p>
 * 各種ユーティリティメソッドを提供するクラス
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class Util {
	/**
	 * <p>
	 * コンストラクタ
	 * <p>
	 * ユーティリティメソッドを提供するクラスのため、インスタンス化は不可とする。
	 */
	private Util() {
	}

	/**
	 * <p>
	 * ファイル拡張子取得メソッド
	 * <p>
	 * Fileオブジェクトからファイルの拡張子を取得し返却する。
	 * 
	 * @param file Fileオブジェクト
	 * @return ファイルの拡張子を文字列型で返却する。引数のFileオブジェクトがnullの場合はnullを返却する。
	 */
	public static String getExtension(final File file) {
		if (file == null) {
			return null;
		}
		final String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim();
	}
	
	/**
	 * 文字列を指定文字列で分割し、配列で返却します。
	 *
	 * @param str 文字列
	 * @param del 区切り文字列
	 * @return 分割された文字列を格納した配列
	 */
	public static String[] split(String str, String del){
		ArrayList<String> list = new ArrayList<String>();
		int pos   = 0;
		int index = 0;
		while ((index = str.indexOf(del, pos)) !=- 1) {
			list.add(str.substring(pos, index));
			pos = index + del.length();
		}
		list.add(str.substring(pos, str.length()));
		return (String[])list.toArray(new String[list.size()]);
	}
}
