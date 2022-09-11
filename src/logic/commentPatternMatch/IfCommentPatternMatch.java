package logic.commentPatternMatch;

import logic.commentPatternMatch.AbsCommentPatternMatch.EndMultiCommentPattern;
import logic.commentPatternMatch.AbsCommentPatternMatch.SingleCommentPattern;
import logic.commentPatternMatch.AbsCommentPatternMatch.StartMultiCommentPattern;
import logic.commentPatternMatch.CommentPatternMatchFactory.CommentPatternMatchType;

/**
 * <p>
 * コメントパターン判定のインタフェース
 * <p>
 * コメントを判定する処理を提供するインタフェースを提供するインタフェースであり、<br>
 * 実装する際は、このインタフェースをimplementsした{@link AbsCommentPatternMatch}を継承してください。
 * <p>
 * 各プログラムファイルのコメントパターン判定用クラスを実装する際は{@link IfCommentPatternMatch}で定義されているメソッドをオーバライドして、<br>
 * １行コメント／複数行コメント（開始）／複数行コメント（終了）を判定する処理を実装してください。<br>
 * また、１行コメント／複数行コメント（開始）／複数行コメント（終了）を抽出する正規表現を<br>
 * {@link SingleCommentPattern}、{@link StartMultiCommentPattern}、{@link EndMultiCommentPattern}に定義してください。
 * <p>
 * インスタンスを生成する際は、{@link CommentPatternMatchType#of}を用いて生成してください。
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 * 
 * @see AbsCommentPatternMatch
 * @see JavaCommentPatternMatch
 * @see CsCommentPatternMatch
 * @see CommentPatternMatchFactory
 */
public interface IfCommentPatternMatch {
	/**
	 * <p>
	 * １行コメント判定メソッド
	 * <p>
	 * １行コメントを含んでいるか判定するメソッドです。
	 * 
	 * @param target 検索対象の行
	 * @return １行コメントが存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public boolean isSingleCommentPattern(String target);

	/**
	 * <p>
	 * 複数行コメント（開始）判定メソッド
	 * <p>
	 * 複数行コメント（開始）を含んでいるか判定するメソッドです。
	 * 
	 * @param target 検索対象の行
	 * @return 複数行コメント（開始）が存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public boolean isStartMultiCommentPattern(String target);

	/**
	 * <p>
	 * 複数行コメント（終了）判定メソッド
	 * <p>
	 * 複数行コメント（終了）を含んでいるか判定するメソッドです。
	 * 
	 * @param target 検索対象の行
	 * @return 複数行コメント（終了）が存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public boolean isEndMultiCommentPattern(String target);
	
	/**
	 * <p>
	 * コメントパターン判定のオブジェクトを返却するメソッド
	 * 
	 * @return コメントパターン判定のオブジェクト
	 */
	public <T extends IfCommentPatternMatch> T create();
}
