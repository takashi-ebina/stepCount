package main.arg;

import java.util.Objects;

import constant.Constant.ExecuteMode;
import constant.Constant.SortTarget;
import constant.Constant.SortType;
import util.Util;

/**
 * <p>
 * コマンドライン引数の値、オプションを簡易に解析するクラス
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public final class Arguments {

	/** ステップカウント処理実行モード */
	private ExecuteMode stepCountExecuteMode = ExecuteMode.INTERACTIVE;
	/** ステップカウントソート区分 */
	private SortType stepCountSortType = SortType.NO_SORT;
	/** ステップカウントソート対象 */
	private SortTarget stepCountSortTarget = SortTarget.FILEPATH;
	/** スクリプトモードで利用する引数をオブジェクト化したクラス */
	private ScriptArguments scriptArguments = new ScriptArguments();

	/**
	 * <p>
	 * コンストラクタ
	 * <p>
	 * コマンドライン引数の値を元にオプションの解析を行う。
	 * <ul>
	 * <li>コマンドライン引数の長さが0の場合、ステップカウント処理実行モードが対話モードになる。</li>
	 * <li>コマンドライン引数に{@literal -h}が存在する場合、ステップカウント処理実行モードがヘルプモードになる。</li>
	 * <li>コマンドライン引数に{@literal -s}が存在する場合、ステップカウント処理実行モードがスクリプトモードになる。<br>
	 * また、スクリプトモードで利用する引数を{@link ScriptArguments}に設定する。</li>
	 * <li>スクリプトモードで利用する引数は{@literal -input=}および{@literal -output=}を指定する。</li>
	 * <li>{@literal -input=}：「=」以降にカウント対象のディレクトリパスを指定する。</li>
	 * <li>{@literal -output=}：「=」以降にカウント結果出力対象のファイルパスを指定する。</li>
	 * <li>ステップカウント処理の出力順をソートさせたい場合は{@literal -input=}または{@literal -output=}を指定する。</li>
	 * <li>{@literal -asc=}：「=」以降にソート対象の項目を指定する</li>
	 * <li>{@literal -desc=}：「=」以降にソート対象の項目を指定する</li>
	 * <li>ソート対象の項目は0:ファイルパス、1:総行数、2:実行行数、3:コメント行数、4:空行数で指定する。</li>
	 * </ul>
	 * 
	 * @param args コマンドライン引数
	 */
	public Arguments(final String[] args) {
		if (args.length == 0) {
			// 対話モードの場合
			this.stepCountExecuteMode = ExecuteMode.INTERACTIVE;
			return;
		}
		String inputDirectoryPath = null;
		String outputFilePath = null;
		for (final String commandOption : args) {
			if (Objects.equals(commandOption, "-h")) {
				// ヘルプモードの場合
				this.stepCountExecuteMode = ExecuteMode.HELP;
			} else if (Objects.equals(commandOption, "-s")) {
				// スクリプトモードの場合
				this.stepCountExecuteMode = ExecuteMode.SCRIPT;
			} else if (commandOption.startsWith("-input=")) {
				// カウント対象のディレクトリパスを指定している場合
				inputDirectoryPath = Util.split(commandOption, "=")[1];
			} else if (commandOption.startsWith("-output=")) {
				// カウント結果出力対象のファイルパスを指定している場合
				outputFilePath = Util.split(commandOption, "=")[1];
			} else if (commandOption.startsWith("-asc=")) {
				// 昇順ソートを指定している場合
				this.stepCountSortType = SortType.ASCENDING_ORDER;
				this.stepCountSortTarget = SortTarget.lookup(Util.split(commandOption, "=")[1]);
			} else if (commandOption.startsWith("-desc=")) {
				// 降順ソートを指定している場合
				this.stepCountSortType = SortType.DESCENDING_ORDER;
				this.stepCountSortTarget = SortTarget.lookup(Util.split(commandOption, "=")[1]);
			}
		}
		if (this.stepCountExecuteMode == ExecuteMode.SCRIPT) {
			this.scriptArguments = new ScriptArguments(inputDirectoryPath, outputFilePath);
		}
	}

	/**
	 * <p>
	 * ステップカウント処理実行モードを返却するメソッド
	 * 
	 * @return ステップカウント処理実行モード
	 */
	public ExecuteMode getStepCountExecuteMode() {
		return this.stepCountExecuteMode;
	}
	
	/**
	 * <p>
	 * ステップカウントソート区分を返却するメソッド
	 * 
	 * @return ステップカウントソート区分
	 */
	public SortType getStepCountSortType() {
		return this.stepCountSortType;
	}

	/**
	 * <p>
	 * ステップカウントソート対象を返却するメソッド
	 * 
	 * @return ステップカウントソート対象
	 */
	public SortTarget getStepCountSortTarget() {
		return this.stepCountSortTarget;
	}
	
	/**
	 * <p>
	 * スクリプトモードで利用する引数をオブジェクト化したクラスを返却するメソッド
	 * 
	 * @return スクリプトモードで利用する引数をオブジェクト化したクラス
	 */
	public ScriptArguments getScriptArguments() {
		return this.scriptArguments;
	}
	
}
