package main.arg;

import java.util.Objects;

/**
 * <p>
 * コマンドライン引数の値、オプションを簡易に解析するクラス
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class Arguments {
	/** 対話モードフラグ */
	private boolean interactive = false;
	/** ヘルプモードフラグ */
	private boolean help = false;
	/** スクリプトモードフラグ */
	private boolean script = false;
	/** スクリプトモードで利用する引数をオブジェクト化したクラス */
	private ScriptArguments scriptArguments = new ScriptArguments();

	/**
	 * <p>
	 * コンストラクタ
	 * <p>
	 * コマンドライン引数の値を元にオプションの解析を行う。
	 * <ul>
	 * <li>コマンドライン引数の長さが0の場合、対話モードフラグ（{@link interactive}）がtureになる。</li>
	 * <li>コマンドライン引数に{@literal -h}が存在する場合、ヘルプモードフラグ（{@link help}）がtureになる。</li>
	 * <li>コマンドライン引数に{@literal -s}が存在する場合、スクリプトモードフラグ（{@link script}）がtureになる。<br>
	 * また、スクリプトモードで利用する引数を{@link ScriptArguments}に設定する。</li>
	 * </ul>
	 * 
	 * @param args コマンドライン引数
	 */
	public Arguments(String[] args) {
		if (args.length == 0) {
			this.interactive = true;
			return;
		}
		for (int i = 0; i < args.length; i++) {
			if (Objects.equals(args[i], "-h")) {
				this.help = true;
			} else if (Objects.equals(args[i], "-s")) {
				this.script = true;
				try {
					this.scriptArguments = new ScriptArguments(args[i + 1], args[i + 2]);
				} catch (ArrayIndexOutOfBoundsException e) {
					// 「-s」オプション以降に引数が存在しない場合にcatchされる。
					// 例外を握り潰しているが、後続の入力値チェックでエラーになる想定。
				}
			}
		}
	}

	/**
	 * <p>
	 * 対話モードフラグの値を返却するメソッド
	 * 
	 * @return 対話モードフラグ。<br>
	 *         インスタンス生成時にコマンドライン引数の長さが0の場合、trueを返却する。<br>
	 *         それ以外の場合はfalseを返却する。
	 */
	public boolean isInteractive() {
		return this.interactive;
	}

	/**
	 * <p>
	 * ヘルプモードフラグの値を返却するメソッド
	 * 
	 * @return ヘルプモードフラグ。<br>
	 *         インスタンス生成時にコマンドライン引数に{@literal -h}が存在する場合、trueを返却する。<br>
	 *         それ以外の場合はfalseを返却する。
	 */
	public boolean isHelp() {
		return this.help;
	}

	/**
	 * <p>
	 * スクリプトモードフラグの値を返却するメソッド
	 * 
	 * @return スクリプトモードフラグ。<br>
	 *         インスタンス生成時にコマンドライン引数に{@literal -s}が存在する場合、trueを返却する。<br>
	 *         それ以外の場合はfalseを返却する。
	 */
	public boolean isScript() {
		return this.script;
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
