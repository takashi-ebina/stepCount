package main.arg;

/**
 * <p>
 * スクリプトモード（コマンドライン引数に{@literal -s} カウント対象のディレクトリパス カウント結果出力対象のファイルパス）で<br>
 * ステップカウント処理を実行する際に、コマンドライン引数に指定されている値を格納するクラス
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public final class ScriptArguments {
	/** カウント対象のディレクトリパス */
	private String inputDirectoryPath;
	/** カウント結果出力対象のファイルパス */
	private String outputFilePath;

	/**
	 * <p>
	 * コンストラクタ
	 * <p>
	 * コマンドライン引数に{@literal -s}が存在する場合、このコンストラクタが利用される。
	 * 
	 * @param inputDirectoryPath カウント対象のディレクトリパス
	 * @param outputFilePath     カウント結果出力対象のファイルパス
	 * @throws IllegalArgumentException コンストラクタの引数がNullの場合
	 */
	public ScriptArguments(final String inputDirectoryPath, final String outputFilePath) {
		if (inputDirectoryPath == null || outputFilePath == null) {
			throw new IllegalArgumentException("コンストラクタの引数の値がNullです");
		}
		this.inputDirectoryPath = inputDirectoryPath;
		this.outputFilePath = outputFilePath;
	}

	/** コンストラクタ */
	public ScriptArguments() {
		this.inputDirectoryPath = "";
		this.outputFilePath = "";
	}

	/**
	 * <p>
	 * カウント対象のディレクトリパスを返却するメソッド
	 * 
	 * @return カウント対象のディレクトリパス
	 */
	public String getInputDirectoryPath() {
		return inputDirectoryPath;
	}

	/**
	 * <p>
	 * カウント対象のディレクトリパスを返却するメソッド
	 * 
	 * @return カウント対象のディレクトリパス
	 */
	public String getOutputFilePath() {
		return outputFilePath;
	}
}
