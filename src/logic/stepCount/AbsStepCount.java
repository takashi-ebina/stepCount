package logic.stepCount;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.StepCountData;
import factory.StepCountFactory;
import logic.commentPatternMatch.AbsCommentPatternMatch;
import logic.commentPatternMatch.IfCommentPatternMatch;
import util.log.Log4J2;
/**
 * <p>ステップカウントの抽象クラス
 * <p>各プログラムファイルのステップカウント処理を提供する抽象クラスです。<br>
 * 各プログラムファイル毎のステップカウント処理を実装する際は、このクラスを継承し、{@link AbsStepCount#fileReadStepCount(StepCountData)}をオーバーライドして、個別に実装してください。
 * <p>また、１行コメント／複数行コメント（開始）／複数行コメント（終了）を判定するための正規表現の定義および実装を{@link AbsCommentPatternMatch}を継承したクラスに記載してください。
 * <p>ステップカウントのインスタンスを生成する際は、{@link StepCountFactory#create(String, File, File, IfCommentPatternMatch)}を用いて生成してください。
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
	protected final Log4J2 logger;
	/** カウント対象プログラムファイル */
	protected final File inputFile;
	/** カウント結果出力対象ファイル */
	protected final File outputFile;
	/** コメントパターンマッチクラス */
	protected final IfCommentPatternMatch commentPatternMatch;
	
	/** 
	 * コンストラクタ
     * @param inputFile ステップカウント対象ファイル
     * @param outputFile ステップカウント結果出力ファイル
     * @param commentPatternMatch コメント判定用クラス
	 */
	public AbsStepCount(final File inputFile, final File outputFile, final IfCommentPatternMatch commentPatternMatch) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		this.commentPatternMatch = commentPatternMatch;
		this.logger = Log4J2.getInstance();
	}
	/**
	 * <p>プログラムファイルのステップ数を集計するメソッド
	 * <p>プログラムファイルのステップ数の集計処理および、集計結果をカウント結果出力対象ファイル（{@link outputFile}）に書き込む処理を行います。
	 */
	@Override
	public void stepCount() {
		fileWriteStepCount(fileReadStepCount(new StepCountData()));
	}
	/**
	 * <p>１行コメント判定メソッド
	 * <p>１行コメントを含んでいるか判定するメソッドです。
	 * <p>実際の処理は{@link IfCommentPatternMatch#isSingleCommentPattern}に移譲しています。
	 * 
	 * @param target 検索対象の行
	 * @return １行コメントが存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public boolean isSingleCommentPattern(final String target) {
		return commentPatternMatch.isSingleCommentPattern(target);
	}
	/**
	 * <p>複数行コメント（開始）判定メソッド
	 * <p>複数行コメント（開始）を含んでいるか判定するメソッドです。
	 * <p>実際の処理は{@link IfCommentPatternMatch#isStartMultiCommentPattern}に移譲しています。
	 * 
	 * @param target 検索対象の行
	 * @return 複数行コメント（開始）が存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public boolean isStartMultiCommentPattern(final String target) {
	    return commentPatternMatch.isStartMultiCommentPattern(target);
	}
	/**
	 * <p>複数行コメント（終了）判定メソッド
	 * <p>複数行コメント（終了）を含んでいるか判定するメソッドです。
	 * <p>実際の処理は{@link IfCommentPatternMatch#isEndMultiCommentPattern}に移譲しています。
	 * 
	 * @param target 検索対象の行
	 * @return 複数行コメント（終了）が存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public boolean isEndMultiCommentPattern(final String target) {
		return commentPatternMatch.isEndMultiCommentPattern(target);
	}
	/**
	 * <p>プログラムファイルのステップ数をステップカウント結果出力ファイルに書き込むメソッド
	 * <p>引数の1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスを元に、<br>
	 * 集計結果をカウント結果出力対象ファイル（{@link outputFile}）に書き込む処理を行います。
	 * <p>[書き込み内容]<br>
	 * ファイルパス,総行数,実行行数,コメント行数,空行数
	 * 
	 * @param stepCountData 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラス
	 */
	protected void fileWriteStepCount(final StepCountData stepCountData) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.outputFile))) {
			bw.write(this.inputFile.getName() + stepCountData.outputDataCommaDelimited());
		} catch (IOException e) {
			logger.logWarn("ステップ数書き込み処理で例外発生。 カウント対象プログラムファイル：" 
					+ this.inputFile + ", カウント結果出力対象ファイル：" + this.outputFile);
			logger.logError(e);
		}
	}

	/**
	 * <p>プログラムファイルのステップ数をカウント結果出力対象ファイルに書き込み処理を行う抽象メソッド
	 * <p>カウント対象プログラムファイル（{@link inputFile}）を元に、総行数／実行行数／コメント行数／空行数を集計処理を行います。
	 * 
	 * @param stepCountData 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラス
	 * @return 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラス
	 */
	abstract protected StepCountData fileReadStepCount(final StepCountData stepCountData);
}
