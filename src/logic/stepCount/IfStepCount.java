package logic.stepCount;

import java.io.File;

import factory.StepCountFactory;
import logic.commentPatternMatch.IfCommentPatternMatch;

/**
 * <p>ステップカウントのインタフェース
 * <p>各プログラムファイルのステップカウント処理を提供するインタフェースであり、<br>
 * 実装する際は、このインタフェースをimplementsした{@link AbsStepCount}を継承してください。
 * <p>ステップカウントのインスタンスを生成する際は、{@link StepCountFactory#create(String, File, File, IfCommentPatternMatch)}を用いて生成してください。
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 * 
 * @see AbsStepCount
 * @see JavaStepCount
 * @see CsStepCount
 */
public interface IfStepCount {
	/**
	 * <p>プログラムファイルのステップ数を集計するメソッド
	 * <p>プログラムファイルのステップ数を集計する際の共通処理を実装してください。
	 * 各プログラムファイル毎に異なる処理に関しては、{@link AbsStepCount}、およびそれを継承したサブクラスに実装してください。
	 */
	public void stepCount();
}
