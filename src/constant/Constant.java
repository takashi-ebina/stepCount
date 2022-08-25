package constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * <p>共通の定数クラス
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class Constant {
	/** 
	 * <p>コンストラクタ
	 * <p>共通クラスのため、インスタンス化は不可とする。
	 */
	private Constant() {}
	/** 
	 * <p>ステップカウントCSVのヘッダー名
	 */
	public static final List<String> STEP_COUNT_HEADER_NAME =
			new ArrayList<String>(Arrays.asList("ファイルパス","総行数","実行行数","コメント行数","空行数"));
	/** 
	 * <p>ファイルパス区分
	 */	
	public static enum FilePathType {
		/** ディレクトリ */
		DIRECTORY,
		/** ファイル */
		FILE
	}
	/** 
	 * <p>コマンドオプション区分
	 */	
	public static enum CommandOptionType {
		/** 対話モード */
		INTERACTIVE,
		/** ヘルプモード */
		HELP,
		/** スクリプトモード */
		SCRIPT
	}
}
