package factory;

import constant.EnumReverseLookup;
import logic.commentPatternMatch.AbsCommentPatternMatch;
import logic.commentPatternMatch.CsCommentPatternMatch;
import logic.commentPatternMatch.IfCommentPatternMatch;
import logic.commentPatternMatch.JavaCommentPatternMatch;
/**
 * <p>コメントパターンマッチクラスの生成に利用するFactoryクラス。
 * <p>{@link IfCommentPatternMatch}をimplementsしたクラスオブジェクトを生成する場合は、<br>
 * {@link CommentPatternMatchFactory#create(String)}の引数に生成するオブジェクトに紐づく拡張子を渡してください。
 * <p>対応する拡張子は{@link CommentPatternMatchType}に定義されています。
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
	 * コメントパターンマッチクラスの種別をもつ列挙型クラス
	 */
	public enum CommentPatternMatchType {
		/** Javaコメント判定用オブジェクト */
		Java("java", "logic.commentPatternMatch.JavaCommentPatternMatch"),
		/** Csコメント判定用オブジェクト */
		Cs("cs", "logic.commentPatternMatch.CsCommentPatternMatch");
		/** 拡張子 */
		private final String extension;
		/** クラス名（完全修飾子） */
		private final String className;
		/** 
		 * コンストラクタ
		 * @param extension 拡張子
		 * @param className クラス名（完全修飾子）
		 */
		CommentPatternMatchType(final String extension, final String className) {
			this.extension = extension;
			this.className = className;
		}
	    /**
	     * <p>拡張子の値を返却するメソッド
	     * @return 拡張子
	     */
		public String getExtension() {
			return this.extension;
		}
	    /**
	     * <p>クラス名（完全修飾子）の値を返却するメソッド
	     * @return クラス名（完全修飾子）
	     */
		public String getClassName() {
			return this.className;
		}
	    /**
	     * <p>拡張子に対応するクラス名（完全修飾子）の値を返却するメソッド
	     * <p>拡張子から該当のEnumを取得する処理は{@link EnumReverseLookup#lookup(Object)}に移譲しています。
	     * @param extension 拡張子
	     * @return 拡張子に対応するクラス名（完全修飾子）
	     */
		public static String getByClassName(final String extension) {
			return new EnumReverseLookup<>(CommentPatternMatchType.class, CommentPatternMatchType::getExtension).lookup(extension).getClassName();
		}
	    /**
	     * <p>拡張子に対応するEnumの存在判定メソッド
	     * <p>拡張子から該当のEnumを取得する処理は{@link EnumReverseLookup#containsExtension(Object)}に移譲しています。
	     * @param extension 拡張子
	     * @return 拡張子に対応するEnumが存在する場合trueを返却。それ以外の場合はfalseを返却する。
	     */
		public static boolean containsExtension(final String extension) {
			return new EnumReverseLookup<>(CommentPatternMatchType.class, CommentPatternMatchType::getExtension).containsExtension(extension);
		}
	}
    /**
     * <p>拡張子に対応するコメントパターンマッチオブジェクトを生成するメソッド
     * @param extension 拡張子
     * @throws Exception {@link IfCommentPatternMatch}のインスタンス生成時に例外が発生した場合。
     * @return コメントパターンマッチオブジェクト
     */
	public static IfCommentPatternMatch create (final String extension) throws Exception {
		final String className = CommentPatternMatchType.getByClassName(extension);
		IfCommentPatternMatch commentPatternMatchObj = null;
		try {
			commentPatternMatchObj = (IfCommentPatternMatch)Class.forName(className)
					.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.out.println("--> [ERROR]:インスタンスを生成することができませんでした。 クラス名：" + className);
			throw e;
		}
		return commentPatternMatchObj;
	}
}
