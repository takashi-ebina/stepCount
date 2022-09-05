package data;

import java.io.File;

/**
 * <p>
 * 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラス
 * <p>
 * 集計したステップ数が意図しない値とならないよう、ステップ数を加算する場合はincrementメソッドを利用し値が1ずつのみ加算されるようになっています。<br>
 * そのため、setterメソッドは実装しておらず、新規実装も推奨していません。
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class StepCountData {
	/** 1ファイル単位の総行数 */
	private int totalStepCount = 0;
	/** 1ファイル単位の実行行数 */
	private int execStepCount = 0;
	/** 1ファイル単位のコメント行数 */
	private int commentStepCount = 0;
	/** 1ファイル単位の空行数 */
	private int emptyStepCount = 0;
	/** ステップ数ファイル書き込みフラグ */
	private boolean canWriteStepCount = true;
	/** カウント対象プログラムファイル */
	protected File inputFile;

	/**
	 * <p>
	 * コンストラクタ
	 * 
	 * @param inputFile カウント対象プログラムファイル
	 * @throws IllegalArgumentException カウント対象プログラムファイルがNullの場合
	 */
	public StepCountData(final File inputFile) {
		if (inputFile == null) {
			throw new IllegalArgumentException("コンストラクタの引数の値がNullです");
		}
		this.inputFile = inputFile;
	}

	/**
	 * <p>
	 * 1ファイル単位の総行数を返却するメソッド
	 * 
	 * @return 1ファイル単位の総行数
	 */
	public int getTotalStepCount() {
		return this.totalStepCount;
	}

	/**
	 * <p>
	 * 1ファイル単位の総行数の値をインクリメントするメソッド
	 */
	public void incrementTotalStepCount() {
		this.totalStepCount++;
	}

	/**
	 * <p>
	 * 1ファイル単位の実行行数を返却するメソッド
	 * 
	 * @return 1ファイル単位の実行行数
	 */
	public int getExecStepCount() {
		return this.execStepCount;
	}

	/**
	 * <p>
	 * 1ファイル単位の実行行数の値をインクリメントするメソッド
	 */
	public void incrementExecStepCount() {
		this.execStepCount++;
	}

	/**
	 * <p>
	 * 1ファイル単位のコメント行数を返却するメソッド
	 * 
	 * @return 1ファイル単位のコメント行数
	 */
	public int getCommentStepCount() {
		return this.commentStepCount;
	}

	/**
	 * <p>
	 * 1ファイル単位のコメント行数の値をインクリメントするメソッド
	 */
	public void incrementCommentStepCount() {
		this.commentStepCount++;
	}

	/**
	 * <p>
	 * 1ファイル単位の空行数を返却するメソッド
	 * 
	 * @return 1ファイル単位の空行数
	 */
	public int getEmptyStepCount() {
		return this.emptyStepCount;
	}

	/**
	 * <p>
	 * 1ファイル単位の空行数の値をインクリメントするメソッド
	 */
	public void incrementEmptyStepCount() {
		this.emptyStepCount++;
	}

	/**
	 * <p>
	 * ステップ数ファイル書き込みフラグを返却するメソッド
	 * 
	 * @return ステップ数ファイル書き込みフラグ<br>
	 *         書き込み可能な場合はtrue。書き込み不可の場合はfalseを返却する。
	 */
	public boolean isCanWriteStepCount() {
		return this.canWriteStepCount;
	}

	/**
	 * <p>
	 * ステップ数ファイル書き込みフラグを更新するメソッド
	 * 
	 * @param canWriteStepCount 書き込み可能な場合はtrue。書き込み不可の場合はfalseを設定する。
	 */
	public void setCanWriteStepCount(boolean canWriteStepCount) {
		this.canWriteStepCount = canWriteStepCount;
	}

	/**
	 * <p>
	 * カウント対象プログラムファイルを返却するメソッド
	 * 
	 * @return カウント対象プログラムファイル
	 */
	public File getInputFile() {
		return this.inputFile;
	}

	/**
	 * <p>
	 * {@link StepCountData}で定義されている文字列表現を返却するメソッド
	 * 
	 * @return {@link StepCountData}で定義されている文字列表現
	 */
	@Override
	public String toString() {
		return new StringBuilder()
				.append("stepCountData [totalStepCount=").append(this.totalStepCount)
				.append(", execStepCount=").append(this.execStepCount)
				.append(", commentStepCount=").append(this.commentStepCount)
				.append(", emptyStepCount=").append(this.emptyStepCount)
				.append(", canWriteStepCount=").append(this.canWriteStepCount)
				.append(", inputFile=").append(this.inputFile).append("]").toString();
	}

	/**
	 * <p>
	 * ファイル名／1ファイル単位の総行数／実行行数／コメント行数／空行数をカンマ区切りで返却するメソッド
	 * <p>
	 * {@link StepCountData#canWriteStepCount}がfalseの場合は、ステップ数集計に失敗したとみなし、それぞれの値を0にして出力する。
	 * 
	 * @return {@link StepCountData#canWriteStepCount}がtrueの場合、ファイル名／1ファイル単位の総行数／実行行数／コメント行数／空行数をカンマ区切りにした文字列を返却する。<br>
	 *         falseの場合はファイル名+「(Failure to tally the number of
	 *         steps),0,0,0,0」を結合した文字列を返却する。
	 */
	public String outputDataCommaDelimited() {
		final StringBuilder sb;
		if (canWriteStepCount) {
			sb = new StringBuilder().append(this.inputFile.getAbsolutePath())
					.append(",").append(this.totalStepCount)
					.append(",").append(this.execStepCount)
					.append(",").append(this.commentStepCount)
					.append(",").append(this.emptyStepCount);
		} else {
			sb = new StringBuilder().append(this.inputFile.getAbsolutePath())
					.append("(Failure to tally the number of steps),0,0,0,0");
		}
		return sb.toString();
	}
}
