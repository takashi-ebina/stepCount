package logic.stepCount;

import java.io.File;

import data.StepCountData;
import logic.commentPatternMatch.AbsCommentPatternMatch;
import logic.commentPatternMatch.IfCommentPatternMatch;
import logic.stepCount.StepCountFactory.StepCountType;
import util.log.Log4J2;

/**
 * <p>
 * ステップカウントの抽象クラス
 * <p>
 * 各プログラムファイルのステップカウント処理を提供する抽象クラスです。<br>
 * 各プログラムファイル毎のステップカウント処理を実装する際は、<br>
 * このクラスを継承し、{@link AbsStepCount#fileReadStepCount(StepCountData)}をオーバーライドして、個別に実装してください。<br>
 * また、１行コメント／複数行コメント（開始）／複数行コメント（終了）を判定するための正規表現の定義および実装を<br>
 * {@link AbsCommentPatternMatch}を継承したクラスに記載してください。
 * <p>
 * ステップカウントのインスタンスを生成する際は、{@link StepCountType#of(String, IfCommentPatternMatch)}を用いて生成してください。
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 * 
 * @see IfStepCount
 * @see JavaStepCount
 * @see CsStepCount
 * @see StepCountFactory
 * @see AbsCommentPatternMatch
 */
public abstract class AbsStepCount implements IfStepCount {
	/** Log4J2インスタンス */
	protected final Log4J2 logger = Log4J2.getInstance();
	/** コメントパターン判定用クラス */
	protected IfCommentPatternMatch commentPatternMatch = null;

	/**
	 * コンストラクタ
	 * <p>
	 * ファクトリクラス（{@link StepCountType#of}）で利用するために実装しています。<br>
	 * 本クラスを継承する際は引数なしのコンストラクタを実装してください。
	 */
	protected AbsStepCount() {
	}

	/**
	 * コンストラクタ
	 * 
	 * @param commentPatternMatch コメント判定用クラス
	 * @throws IllegalArgumentException コメントパターン判定用クラスがNullの場合
	 */
	public AbsStepCount(final IfCommentPatternMatch commentPatternMatch) {
		if (commentPatternMatch == null) {
			throw new IllegalArgumentException("コンストラクタの引数の値がNullです");
		}
		this.commentPatternMatch = commentPatternMatch;
	}

	/**
	 * <p>
	 * プログラムファイルのステップ数を集計するメソッド
	 * <p>
	 * プログラムファイルのステップ数の集計処理を行い、1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスを返却します。
	 * 
	 * @param inputFile ステップカウント対象ファイル
	 * @return 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラス
	 */
	@Override
	public StepCountData stepCount(final File inputFile) {
		return fileReadStepCount(new StepCountData(inputFile));
	}

	/**
	 * <p>
	 * １行コメント判定メソッド
	 * <p>
	 * １行コメントを含んでいるか判定するメソッドです。
	 * <p>
	 * 実際の処理は{@link IfCommentPatternMatch#isSingleCommentPattern}に移譲しています。
	 * 
	 * @param target 検索対象の行
	 * @return １行コメントが存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public boolean isSingleCommentPattern(final String target) {
		return commentPatternMatch.isSingleCommentPattern(target);
	}

	/**
	 * <p>
	 * 複数行コメント（開始）判定メソッド
	 * <p>
	 * 複数行コメント（開始）を含んでいるか判定するメソッドです。
	 * <p>
	 * 実際の処理は{@link IfCommentPatternMatch#isStartMultiCommentPattern}に移譲しています。
	 * 
	 * @param target 検索対象の行
	 * @return 複数行コメント（開始）が存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public boolean isStartMultiCommentPattern(final String target) {
		return commentPatternMatch.isStartMultiCommentPattern(target);
	}

	/**
	 * <p>
	 * 複数行コメント（終了）判定メソッド
	 * <p>
	 * 複数行コメント（終了）を含んでいるか判定するメソッドです。
	 * <p>
	 * 実際の処理は{@link IfCommentPatternMatch#isEndMultiCommentPattern}に移譲しています。
	 * 
	 * @param target 検索対象の行
	 * @return 複数行コメント（終了）が存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public boolean isEndMultiCommentPattern(final String target) {
		return commentPatternMatch.isEndMultiCommentPattern(target);
	}

	/**
	 * <p>
	 * プログラムファイルのステップ数をカウント結果出力対象ファイルに書き込み処理を行う抽象メソッド
	 * <p>
	 * カウント対象プログラムファイル（{@link StepCountData#getInputFile()}）を元に、総行数／実行行数／コメント行数／空行数を集計処理を行います。
	 * 
	 * @param stepCountData 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラス
	 * @return 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラス
	 */
	abstract protected StepCountData fileReadStepCount(final StepCountData stepCountData);
}
