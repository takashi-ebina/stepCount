package logic.commentPatternMatch;

import factory.CommentPatternMatchFactory;

/**
 * <p>コメントパターン判定用抽象クラス
 * <p>コメントを判定する処理を提供する抽象クラスです。
 * <p>各プログラムファイルのコメントパターン判定用クラスを実装する際は{@link IfCommentPatternMatch}で定義されているメソッドをオーバライドして、<br>
 * １行コメント／複数行コメント（開始）／複数行コメント（終了）を判定する処理を実装してください。<br>
 * また、１行コメント／複数行コメント（開始）／複数行コメント（終了）を抽出する正規表現を<br>
 * {@link SingleCommentPattern}、{@link StartMultiCommentPattern}、{@link EndMultiCommentPattern}に定義してください。
 * <p>インスタンスを生成する際は、{@link CommentPatternMatchFactory#create}を用いて生成してください。
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 * 
 * @see IfCommentPatternMatch
 * @see AbsCommentPatternMatch
 * @see CsCommentPatternMatch
 * @see CommentPatternMatchFactory
 */
public abstract class AbsCommentPatternMatch implements IfCommentPatternMatch {

	/** １行コメント種別をもつ列挙型クラス */
	public static enum SingleCommentPattern {
		/** Java１行コメント*/
		Java("^//.*"),
		/** Cs１行コメント */
		Cs("^//.*");
		/** １行コメント種別 */
		private String value;
		/** 
		 * コンストラクタ
		 * @param value １行コメント種別
		 */
		SingleCommentPattern(final String value) {
			this.value = value;
		}
	    /**
	     * <p>１行コメント種別 の値を返却するメソッド
	     * @return １行コメント種別 
	     */
		public String getValue() {
			return this.value;
		}
	};
	/** 複数行コメント（開始）種別をもつ列挙型クラス */
	public static enum StartMultiCommentPattern {
		/** Java複数行コメント（開始）*/
		Java("^/\\*[^*]*"),
		/** Cs複数行コメント（開始）*/
		Cs("^/\\*[^*]*");
		/** 複数行コメント（開始）種別 */
		private String value;
		/** 
		 * コンストラクタ
		 * @param value 複数行コメント（開始）種別
		 */
		StartMultiCommentPattern(final String value) {
			this.value = value;
		}
	    /**
	     * <p>複数行コメント（開始）種別 の値を返却するメソッド
	     * @return 複数行コメント（開始）種別 
	     */
		public String getValue() {
			return this.value;
		}
	};
	/** 複数行コメント（終了）種別をもつ列挙型クラス */
	public static enum EndMultiCommentPattern {
		/** Java複数行コメント（終了） */
		Java("\\*/$"),
		/** Cs複数行コメント（終了） */
		Cs("\\*/$");
		/** 複数行コメント（終了）種別 */
		private String value;
		/** 
		 * コンストラクタ
		 * @param value 複数行コメント（終了）種別
		 */
		EndMultiCommentPattern(final String value) {
			this.value = value;
		}
	    /**
	     * <p>複数行コメント（終了）種別 の値を返却するメソッド
	     * @return 複数行コメント（終了）種別 
	     */
		public String getValue() {
			return this.value;
		}
	};
}
