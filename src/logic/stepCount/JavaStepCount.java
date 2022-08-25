package logic.stepCount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import data.StepCountData;
import factory.StepCountFactory;
import logic.commentPatternMatch.AbsCommentPatternMatch;
import logic.commentPatternMatch.IfCommentPatternMatch;
/**
 * <p>ステップカウントの具象クラス
 * <p>Javaファイルのステップカウント処理を提供する具象クラスです。
 * <p>インスタンスを生成する際は、{@link StepCountFactory#create(String, File, File, IfCommentPatternMatch)}を用いて生成してください。
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 * 
 * @see IfStepCount
 * @see AbsStepCount
 * @see CsStepCount
 * @see StepCountFactory
 * @see AbsCommentPatternMatch
 */
public class JavaStepCount extends AbsStepCount {

	/** 
	 * コンストラクタ
     * @param inputFile ステップカウント対象ファイル
     * @param outputFile ステップカウント結果出力ファイル
     * @param commentPatternMatch コメント判定用クラス
	 */
	public JavaStepCount(final File inputFile, final File outputFile, final IfCommentPatternMatch commentPatternMatch) {
		super(inputFile, outputFile, commentPatternMatch);
	}
	/**
	 * <p>Javaファイルのステップ数をカウント結果出力対象ファイルに書き込み処理を行う具象メソッド
	 * <p>カウント対象プログラムファイル（{@link inputFile}）を元に、総行数／実行行数／コメント行数／空行数を集計処理を行います。
	 * 
	 * @param stepCountData 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラス
	 * @return 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラス
	 */
	@Override
	protected StepCountData fileReadStepCount(final StepCountData stepCountData) {
		try (BufferedReader bw = new BufferedReader(new FileReader(this.inputFile))) {
			String readLine = "";
			boolean isCommentLine = false;
			while ((readLine = bw.readLine()) != null) {
				final String trimReadLine = readLine.trim();
				stepCountData.incrementTotalStepCount();
				// コメント行判定
				if (isCommentLine) {
					stepCountData.incrementCommentStepCount();
					if (super.isEndMultiCommentPattern(trimReadLine)) isCommentLine = false;
					continue;
				}
				// 空行判定
				if (trimReadLine == null || "".equals(trimReadLine)) {
					stepCountData.incrementEmptyStepCount();
					continue;
				}
				// １行コメント判定
				if (super.isSingleCommentPattern(trimReadLine)) {
					stepCountData.incrementCommentStepCount();
					continue;
				}
				// 複数行コメント（開始）／複数行コメント（終了）判定
				if (super.isStartMultiCommentPattern(trimReadLine) && super.isEndMultiCommentPattern(trimReadLine)) {
					stepCountData.incrementCommentStepCount();
					continue;
				}
				// 複数行コメント（開始）／複数行コメント（終了）判定
				if (super.isStartMultiCommentPattern(trimReadLine) && !super.isEndMultiCommentPattern(trimReadLine)) {
					stepCountData.incrementCommentStepCount();
					isCommentLine = true;
					continue;
				}
				stepCountData.incrementExecStepCount();
			}
		} catch (IOException e) {
			logger.logWarn("Javaステップ数集計処理で例外発生。 ファイル名：" + this.inputFile);
			logger.logError(e);
		}
		return stepCountData;
	}
}
