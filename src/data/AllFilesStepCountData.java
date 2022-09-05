package data;

import java.util.List;

/**
 * <p>
 * 全ファイルの総行数／実行行数／コメント行数／空行数を集計するデータクラス
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class AllFilesStepCountData {
	/** 全ファイルの総行数 */
	private int allFilesTotalStepCount = 0;
	/** 全ファイルの実行行数 */
	private int allFilesExecStepCount = 0;
	/** 全ファイルのコメント行数 */
	private int allFilesCommentStepCount = 0;
	/** 全ファイルの空行数 */
	private int allFilesEmptyStepCount = 0;
	/** 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスリスト */
	private final List<StepCountData> stepCountDatalist;
	
	/**
	 * <p>
	 * コンストラクタ
	 * 
	 * @param stepCountDatalist 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスリスト
	 */
	public AllFilesStepCountData(final List<StepCountData> stepCountDatalist) {
		this.stepCountDatalist = stepCountDatalist;
		for (StepCountData stepCountData : stepCountDatalist) {
			if (stepCountData.isCanWriteStepCount()) {
				this.allFilesTotalStepCount += stepCountData.getTotalStepCount();
				this.allFilesExecStepCount += stepCountData.getExecStepCount();
				this.allFilesCommentStepCount += stepCountData.getCommentStepCount();
				this.allFilesEmptyStepCount += stepCountData.getEmptyStepCount();
			}
		}
	}
	
	/**
	 * <p>
	 * 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスリストを返却するメソッド
	 * 
	 * @return 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスリスト
	 */
	public List<StepCountData> getStepCountDatalist() {
		return this.stepCountDatalist;
	}
	
	/**
	 * <p>
	 * {@link AllFilesStepCountData}で定義されている文字列表現を返却するメソッド
	 * 
	 * @return {@link AllFilesStepCountData}で定義されている文字列表現
	 */
	@Override
	public String toString() {
		return new StringBuilder()
				.append("AllFilesStepCountData [allFilesTotalStepCount=").append(this.allFilesTotalStepCount)
				.append(", allFilesExecStepCount=").append(this.allFilesExecStepCount)
				.append(", allFilesCommentStepCount=").append(this.allFilesCommentStepCount)
				.append(", allFilesEmptyStepCount=").append(this.allFilesEmptyStepCount).append("]").toString();
	}

	/**
	 * <p>
	 * "合計"／全ファイルの総行数／実行行数／コメント行数／空行数をカンマ区切りで返却するメソッド
	 * 
	 * @return "合計"／全ファイルの総行数／実行行数／コメント行数／空行数をカンマ区切りにした文字列を返却する。
	 */
	public String outputDataCommaDelimited() {
		return new StringBuilder()
				.append("合計").append(",")
				.append(this.allFilesTotalStepCount).append(",")
				.append(this.allFilesExecStepCount).append(",")
				.append(this.allFilesCommentStepCount).append(",")
				.append(this.allFilesEmptyStepCount).toString();

	}
}
