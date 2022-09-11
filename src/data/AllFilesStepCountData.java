package data;

import java.util.List;
import java.util.Objects;

import constant.Constant.SortTarget;
import constant.Constant.SortType;

/**
 * <p>
 * 全ファイルの総行数／実行行数／コメント行数／空行数を集計するデータクラス
 * <p>
 * 全ファイルの総行数／実行行数／コメント行数／空行数の集計処理は<br>
 * {@link AllFilesStepCountData#AllFilesStepCountData}コンストラクタで行なっています。<br>
 * そのため、setterメソッドは実装しておらず、新規実装も推奨していません。
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public final class AllFilesStepCountData {
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
	 * <p>
	 * 引数の1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスリストを元に、<br>
	 * 全ファイルの総行数／実行行数／コメント行数／空行数の集計を実施します。
	 * <p>
	 * {@link StepCountData#canWriteStepCount}の値がfalseのデータクラスに関しては、<br>
	 * 1ファイル単位のステップ数の集計が失敗したデータクラスとみなし、全ファイルのステップ数の集計対象外とします。
	 * @throws IllegalArgumentException 引数にNullが存在する場合
	 * @param stepCountDataList   1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスリスト
	 * @param stepCountSortType   ソート区分
	 * @param stepCountSortTarget ソート対象
	 */
	public AllFilesStepCountData(final List<StepCountData> stepCountDataList, 
			final SortType stepCountSortType, final SortTarget stepCountSortTarget) {

		if (stepCountDataList == null) {
			throw new IllegalArgumentException("コンストラクタの引数の値がNullです");
		}

		this.stepCountDatalist = sortStepCountDataList(stepCountDataList, stepCountSortType, stepCountSortTarget);

		for (StepCountData stepCountData : stepCountDataList) {
			if (!stepCountData.canWriteStepCount()) {
				continue;
			}
			this.allFilesTotalStepCount += stepCountData.getTotalStepCount();
			this.allFilesExecStepCount += stepCountData.getExecStepCount();
			this.allFilesCommentStepCount += stepCountData.getCommentStepCount();
			this.allFilesEmptyStepCount += stepCountData.getEmptyStepCount();
		}
	}
	
	/**
	 * <p>
	 * 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスリストのソートメソッド
	 * <p>
	 * 引数のソート区分、ソート対象を元に<br>
	 * 1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスリストのソートを実施します。
	 * 
	 * @param stepCountDataList   1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスリスト
	 * @param stepCountSortType   ソート区分
	 * @param stepCountSortTarget ソート対象
	 * @return ソート後の1ファイル単位の総行数／実行行数／コメント行数／空行数を集計するデータクラスリスト
	 */
	private List<StepCountData> sortStepCountDataList(final List<StepCountData> stepCountDataList, 
			final SortType stepCountSortType, final SortTarget stepCountSortTarget) {
		if (stepCountSortType == SortType.ASCENDING_ORDER) {
			if (stepCountSortTarget == SortTarget.FILEPATH) {
				stepCountDataList.sort((o1, o2) -> o1.getInputFile().getName().compareTo(o2.getInputFile().getName()));
			} else if (stepCountSortTarget == SortTarget.TOTALSTEPCOUNT) {
				stepCountDataList.sort((o1, o2) -> o1.getTotalStepCount() - o2.getTotalStepCount());
			} else if (stepCountSortTarget == SortTarget.EXECSTEPCOUNT) {
				stepCountDataList.sort((o1, o2) -> o1.getExecStepCount() - o2.getExecStepCount());
			} else if (stepCountSortTarget == SortTarget.COMMENTSTEPCOUNT) {
				stepCountDataList.sort((o1, o2) -> o1.getCommentStepCount() - o2.getCommentStepCount());
			} else if (stepCountSortTarget == SortTarget.EMPTYSTEPCOUNT) {
				stepCountDataList.sort((o1, o2) -> o1.getEmptyStepCount() - o2.getEmptyStepCount());
			}
		} else if (stepCountSortType == SortType.ASCENDING_ORDER) {
			if (stepCountSortTarget == SortTarget.FILEPATH) {
				stepCountDataList.sort((o1, o2) -> o2.getInputFile().getName().compareTo(o1.getInputFile().getName()));
			} else if (stepCountSortTarget == SortTarget.TOTALSTEPCOUNT) {
				stepCountDataList.sort((o1, o2) -> o2.getTotalStepCount() - o1.getTotalStepCount());
			} else if (stepCountSortTarget == SortTarget.EXECSTEPCOUNT) {
				stepCountDataList.sort((o1, o2) -> o2.getExecStepCount() - o1.getExecStepCount());
			} else if (stepCountSortTarget == SortTarget.COMMENTSTEPCOUNT) {
				stepCountDataList.sort((o1, o2) -> o2.getCommentStepCount() - o1.getCommentStepCount());
			} else if (stepCountSortTarget == SortTarget.EMPTYSTEPCOUNT) {
				stepCountDataList.sort((o1, o2) -> o2.getEmptyStepCount() - o1.getEmptyStepCount());
			}
		}
		return stepCountDataList;
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
				.append(", allFilesEmptyStepCount=").append(this.allFilesEmptyStepCount)
				.append(", stepCountDatalist=").append(this.stepCountDatalist).append("]").toString();
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
	
	/**
	 * <p>
	 * このオブジェクトが引数の他のオブジェクトが等しいかどうかを判定するメソッド
	 *
	 * @param obj 比較対象のオブジェクト
	 * @return このオブジェクトが引数と同じである場合はtrue。それ以外の場合はfalseを返却する。
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AllFilesStepCountData)) {
			return false;
		}
		
		AllFilesStepCountData test = (AllFilesStepCountData) obj;
		if (!(Objects.equals(this.allFilesTotalStepCount, test.allFilesTotalStepCount))) {
			return false;
		}
		if (!(Objects.equals(this.allFilesExecStepCount, test.allFilesExecStepCount))) {
			return false;
		}
		if (!(Objects.equals(this.allFilesCommentStepCount, test.allFilesCommentStepCount))) {
			return false;
		}
		if (!(Objects.equals(this.allFilesEmptyStepCount, test.allFilesEmptyStepCount))) {
			return false;
		}
		if (!(Objects.equals(this.stepCountDatalist, test.stepCountDatalist))) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * オブジェクトのハッシュ・コード値を返却するメソッド
	 * 
	 * @return このオブジェクトのハッシュ・コード値。
	 */
	@Override
	public int hashCode() {
		return Objects.hash();
	}
}
