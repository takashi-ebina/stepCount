package data;

/**
 * <p>1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラス。
 * <p>集計したステップ数が意図しない値とならないよう、ステップ数を加算する場合はincrementメソッドを利用し値が1ずつのみ加算されるようになっています。<br>
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
    /**
     * <p>1ファイル単位の総行数を返却するメソッド
     * 
     * @return 1ファイル単位の総行数
     */
	public int getTotalStepCount() {
		return this.totalStepCount;
	}
	/**
	 * <p>1ファイル単位の総行数の値をインクリメントするメソッド
	 */
	public void incrementTotalStepCount() {
		this.totalStepCount++;
	}
    /**
     * <p>1ファイル単位の実行行数を返却するメソッド
     * 
     * @return 1ファイル単位の実行行数
     */
	public int getExecStepCount() {
		return this.execStepCount;
	}
	/**
	 * <p>1ファイル単位の実行行数の値をインクリメントするメソッド
	 */
	public void incrementExecStepCount() {
		this.execStepCount++;
	}
    /**
     * <p>1ファイル単位のコメント行数を返却するメソッド
     * 
     * @return 1ファイル単位のコメント行数
     */
	public int getCommentStepCount() {
		return this.commentStepCount;
	}
	/**
	 * <p>1ファイル単位のコメント行数の値をインクリメントするメソッド
	 */
	public void incrementCommentStepCount() {
		this.commentStepCount++;
	}
    /**
     * <p>1ファイル単位の空行数を返却するメソッド
     * 
     * @return 1ファイル単位の空行数
     */
	public int getEmptyStepCount() {
		return this.emptyStepCount;
	}
	/**
	 * <p>1ファイル単位の空行数の値をインクリメントするメソッド
	 */
	public void incrementEmptyStepCount() {
		this.emptyStepCount++;
	}
    /**
     * <p>{@link StepCountData}で定義されている文字列表現を返却するメソッド
     * 
     * @return {@link StepCountData}で定義されている文字列表現
     */
	@Override
	public String toString() {
		return new StringBuilder()
				.append("stepCountData [totalStepCount=").append(this.totalStepCount)
				.append(", execStepCount=").append(this.execStepCount)
				.append(", commentStepCount=").append(this.commentStepCount)
				.append(", emptyStepCount=").append(this.emptyStepCount).append("]").toString();	
	}
    /**
     * <p>1ファイル単位の総行数／実行行数／コメント行数／空行数をカンマ区切りで返却するメソッド
     * 
     * @return 1ファイル単位の総行数／実行行数／コメント行数／空行数をカンマ区切りにした文字列
     */
	public String outputDataCommaDelimited() {
		return new StringBuilder()
				.append(this.totalStepCount).append(",")
				.append(this.execStepCount).append(",")
				.append(this.commentStepCount).append(",")
				.append(this.emptyStepCount).toString();
	}
}