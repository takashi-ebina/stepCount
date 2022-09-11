package logic.commentPatternMatch;

import constant.EnumReverseLookup;

/**
 * <p>
 * コメントパターンマッチクラスの生成に利用するFactoryクラス。
 * <p>
 * {@link IfCommentPatternMatch}をimplementsしたクラスオブジェクトを生成する場合は、<br>
 * {@link CommentPatternMatchType#of(String)}の引数に生成するオブジェクトに紐づく拡張子を渡してください。
 * <p>
 * 対応する拡張子は{@link CommentPatternMatchType}に定義されています。
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 * 
 * @see IfCommentPatternMatch
 * @see AbsCommentPatternMatch
 * @see JavaCommentPatternMatch
 * @see CsCommentPatternMatch
 */
public class CommentPatternMatchFactory {
	/**
	 * <p>
	 * コメントパターンマッチクラスの種別をもつ列挙型クラス
	 */
	public enum CommentPatternMatchType {
		/** Javaコメント判定用オブジェクト */
		Java("java", new JavaCommentPatternMatch()),
		/** Csコメント判定用オブジェクト */
		Cs("cs", new CsCommentPatternMatch());

		/** 拡張子 */
		private final String extension;
		/** コメントパターン判定用オブジェクト */
		private final IfCommentPatternMatch commentPatternMatch;

		/**
		 * <p>
		 * コンストラクタ
		 * 
		 * @param extension 拡張子
		 * @param commentPatternMatch コメントパターン判定用オブジェクト
		 */
		CommentPatternMatchType(final String extension, final IfCommentPatternMatch commentPatternMatch) {
			this.extension = extension;
			this.commentPatternMatch = commentPatternMatch;
		}

		/**
		 * <p>
		 * 拡張子の値を返却するメソッド
		 * 
		 * @return 拡張子
		 */
		public String getExtension() {
			return this.extension;
		}

		/**
		 * <p>
		 * コメントパターン判定オブジェクトを返却するメソッド
		 * 
		 * @return コメントパターン判定オブジェクト
		 */
		public IfCommentPatternMatch getCommentPatternMatch() {
			return this.commentPatternMatch;
		}

		/**
		 * <p>
		 * 拡張子に対応するコメントパターン判定オブジェクトの値を返却するメソッド
		 * <p>
		 * 拡張子から該当のEnumを取得する処理は{@link EnumReverseLookup#lookup(Object)}に移譲しています。
		 * 
		 * @param extension 拡張子
		 * @throws IllegalArgumentException 拡張子に対応するクラスが存在しない場合
		 * @return 拡張子に対応するコメントパターン判定オブジェクト
		 */
		public static IfCommentPatternMatch getByCommentPatternMatch(final String extension) throws IllegalArgumentException {
			return new EnumReverseLookup<>(CommentPatternMatchType.class, CommentPatternMatchType::getExtension)
					.lookup(extension.toLowerCase()).getCommentPatternMatch();
		}

		/**
		 * <p>
		 * 拡張子に対応するEnumの存在判定メソッド
		 * <p>
		 * 拡張子から該当のEnumを取得する処理は{@link EnumReverseLookup#containsAttribute(Object)}に移譲しています。
		 * 
		 * @param extension 拡張子
		 * @return 拡張子に対応するEnumが存在する場合trueを返却。それ以外の場合はfalseを返却する。
		 */
		public static boolean containsExtension(final String extension) {
			return new EnumReverseLookup<>(CommentPatternMatchType.class, CommentPatternMatchType::getExtension)
					.containsAttribute(extension.toLowerCase());
		}

		/**
		 * <p>
		 * 引数の拡張子に紐づくコメントパターン判定用オブジェクトを返却するメソッド
		 * 
		 * @param extension           拡張子
		 * 
		 * @throws IllegalArgumentException 引数の拡張子に対応するコメントパターン判定用オブジェクトが存在しない場合
		 * @return コメントパターン判定用オブジェクト
		 */
		public static IfCommentPatternMatch of(final String extension) {
			return getByCommentPatternMatch(extension.toLowerCase()).create();
		}
	}
}
