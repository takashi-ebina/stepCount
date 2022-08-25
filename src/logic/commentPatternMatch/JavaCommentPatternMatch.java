package logic.commentPatternMatch;

import java.util.Objects;
import java.util.regex.Pattern;

import factory.CommentPatternMatchFactory;
/**
 * <p>Javaコメントパターン判定用抽象クラス
 * <p>Javaコメントを判定する処理を提供する具象クラスです。
 * <p>{@link IfCommentPatternMatch}で定義されているメソッドをオーバライドして、１行コメント／複数行コメント（開始）／複数行コメント（終了）を判定する処理を実装しています。
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
public class JavaCommentPatternMatch extends AbsCommentPatternMatch {
	/**
	 * <p>１行コメント判定メソッド
	 * <p>１行コメントを含んでいるか判定するメソッドです。
	 * 
	 * @param target 検索対象の行
	 * @return １行コメントが存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	@Override
	public boolean isSingleCommentPattern(final String target) {
	    Pattern singleCommentPattern = Pattern.compile(SingleCommentPattern.Java.getValue());
	    return  singleCommentPattern.matcher(target).find();
	}
	/**
	 * <p>複数行コメント（開始）判定メソッド
	 * <p>複数行コメント（開始）を含んでいるか判定するメソッドです。
	 * 
	 * @param target 検索対象の行
	 * @return 複数行コメント（開始）が存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	@Override
	public boolean isStartMultiCommentPattern(final String target) {
	    Pattern startMultiCommentPattern = Pattern.compile(StartMultiCommentPattern.Java.getValue());
	    return  startMultiCommentPattern.matcher(target).find();
	}
	/**
	 * <p>複数行コメント（終了）判定メソッド
	 * <p>複数行コメント（終了）を含んでいるか判定するメソッドです。
	 * 
	 * @param target 検索対象の行
	 * @return 複数行コメント（終了）が存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	@Override
	public boolean isEndMultiCommentPattern(final String target) {
	    Pattern endMultiCCommentPattern = Pattern.compile(EndMultiCommentPattern.Java.getValue());
	    return  endMultiCCommentPattern.matcher(target).find();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof JavaCommentPatternMatch)) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash();
	}
}
