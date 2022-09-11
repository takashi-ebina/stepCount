package constant;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 共通の定数クラス
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class Constant {
	/**
	 * <p>
	 * コンストラクタ
	 * <p>
	 * 共通クラスのため、インスタンス化は不可とする。
	 */
	private Constant() {
	}

	/**
	 * <p>
	 * ステップカウントCSVのヘッダー名
	 */
	public static final List<String> STEP_COUNT_HEADER_NAME = new ArrayList<String>(
			Arrays.asList("ファイルパス", "総行数", "実行行数", "コメント行数", "空行数"));

	/**
	 * <p>
	 * 改行コード(UNIXでは"\n")
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * <p>
	 * 文字コード（Java仮想マシンのデフォルトの文字セット）
	 */
	public static final String CHARSET_NAME = Charset.defaultCharset().name();

	/**
	 * <p>
	 * ファイルパス区分の種別をもつ列挙型クラス
	 */
	public static enum FilePathType {
		/** ディレクトリ */
		DIRECTORY,
		/** ファイル */
		FILE
	}

	/**
	 * <p>
	 * ステップカウント処理実行モードの種別をもつ列挙型クラス
	 */
	public static enum ExecuteMode {
		/** 対話モード */
		INTERACTIVE,
		/** ヘルプモード */
		HELP,
		/** スクリプトモード */
		SCRIPT
	}

	/**
	 * <p>
	 * ソート区分の種別をもつ列挙型クラス
	 */
	public static enum SortType {
		/** ソートなし */
		NO_SORT,
		/** 昇順ソート */
		ASCENDING_ORDER,
		/** 降順ソート */
		DESCENDING_ORDER
	}

	/**
	 * <p>
	 * ソート対象の種別をもつ列挙型クラス
	 */
	public static enum SortTarget {
		/** ファイルパス */
		FILEPATH("0"),
		/** 総行数 */
		TOTALSTEPCOUNT("1"),
		/** 実行行数 */
		EXECSTEPCOUNT("2"),
		/** コメント行数 */
		COMMENTSTEPCOUNT("3"),
		/** 空行数 */
		EMPTYSTEPCOUNT("4");

		/** ソート対象コード */
		private final String sortTargetCode;

		/**
		 * <p>
		 * コンストラクタ
		 * 
		 * @param sortTargetCode ソート対象コード
		 */
		SortTarget(final String sortTargetCode) {
			this.sortTargetCode = sortTargetCode;
		}

		/**
		 * <p>
		 * ソート対象コードの値を返却するメソッド
		 * 
		 * @return ソート対象コード
		 */
		public String getSortTargetCode() {
			return this.sortTargetCode;
		}

		/**
		 * <p>
		 * ソート対象コードに対応するソート対象の種別を返却するメソッド
		 * <p>
		 * ソート対象コードから該当のEnumを取得する処理は{@link EnumReverseLookup#lookup(Object)}に移譲しています。
		 * 
		 * @param sortTargetCode ソート対象コード
		 * @throws IllegalArgumentException ソート対象コードに対応するソート対象の種別が存在しない場合
		 * @return ソート対象コードに対応するソート対象の種別
		 */
		public static SortTarget lookup(final String sortTargetCode) throws IllegalArgumentException {
			return new EnumReverseLookup<>(SortTarget.class, SortTarget::getSortTargetCode).lookup(sortTargetCode);
		}
	}
}
