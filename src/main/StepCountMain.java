package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

import constant.Constant;
import constant.Constant.CommandOptionType;
import factory.CommentPatternMatchFactory;
import factory.CommentPatternMatchFactory.CommentPatternMatchType;
import factory.StepCountFactory;
import factory.StepCountFactory.StepCountType;
import logic.commentPatternMatch.IfCommentPatternMatch;
import logic.stepCount.IfStepCount;
import main.arg.Arguments;
import util.log.Log4J2;
import util.validator.ValidatorUtil;

/**
 * <p>ステップカウント処理を行うメインクラス
 * <p>対話形式または引数にカウント対象のディレクトリパス、カウント結果出力対象のファイルパスを入力し、<br>
 * フォルダ内に存在するプログラムファイルのステップ数を集計し、CSV形式でファイルに出力します。 
 * <p> [使い方]<br>
 * java StepCountMain<br>
 * java StepCountMain [オプション] inputDirectoryPath outputFilePath<br>
 * [オプション]<br>
 * {@literal -h}:このメッセージを表示して終了する。<br>
 * {@literal -s}:スクリプトモードで実行する。（オプションを指定しない場合は対話モード）<br>
 * <p> [出力ファイルイメージ]<br>
 * ファイルパス,総行数,実行行数,コメント行数,空行数<br>
 * /Users/xxx.java,30,20,4,6<br>
 * ...
 * <p>[対応プログラムファイル]<br>
 * <ul>
 * <li>Java</li>
 * </ul>
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class StepCountMain {
	
	/** Log4J2インスタンス */
	private static final Log4J2 logger = Log4J2.getInstance();
	/**
	 * <p>ステップカウント処理を行うエントリーポイントメソッド
	 * 
	 * @param args 未入力の場合、対話モードで処理を実行。それ以外の場合は引数に応じて処理が変動する。
	 */
	public static void main(String args[]) {
		Arguments arguments = new Arguments(args);
		if (arguments.isHelp()) {
			printHelp();
		} else if (arguments.isInteractive()) {
			stepCountInteractiveMode();
		} else if (arguments.isScript()) {
			stepCountScriptMode(arguments);
		} else {
			System.out.println("--> 存在しないオプションを指定しています。");
		}
	}
	/**
	 * <p>対話モードのステップカウントメソッド
	 * <p>[処理概要]<br>
	 * <ol>
	 * <li>カウント対象のディレクトリパスの入力<br>
	 * <li>カウント対象のディレクトリパスの入力チェック<br>
	 * <li>カウント結果出力対象のファイルパスの入力<br>
	 * <li>カウント結果出力対象のファイルパスの入力チェック<br>
	 * <li>カウント結果出力対象のファイルパスのCSVヘッダー書き込み<br>
	 * <li>カウント対象ディレクトリのステップカウント処理<br>
	 * </ol>
	 */
	private static void stepCountInteractiveMode() {
		System.out.println("--> ------------------------------------------------");
		System.out.println("--> カウント対象のディレクトリパスを入力してください");
		System.out.println("--> ------------------------------------------------");
		@SuppressWarnings("resource")
		final Scanner sn = new Scanner(System.in);
		final String inputDirectoryPath = sn.next();
		if (!ValidatorUtil.inputDirectoryCheck(inputDirectoryPath)) {
			System.out.println("--> ステップカウント処理を終了します。");
			return;
		}
		
		System.out.println("--> ------------------------------------------------");
		System.out.println("--> カウント結果出力対象のファイルパスを入力してください");
		System.out.println("--> ファイル拡張子：CSV");
		System.out.println("--> ------------------------------------------------");
		final String outputFilePath = sn.next();
		if (!ValidatorUtil.outputFileCheck(outputFilePath, CommandOptionType.INTERACTIVE)) {
			System.out.println("--> ステップカウント処理を終了します。");
			return;
		}
		execStepCount(new File(inputDirectoryPath), new File(outputFilePath));
	}
	/**
	 * <p>スクリプトモードのステップカウントメソッド
	 * <p>[処理概要]<br>
	 * <ol>
	 * <li>カウント対象のディレクトリパスの入力チェック<br>
	 * <li>カウント結果出力対象のファイルパスの入力チェック<br>
	 * <li>カウント結果出力対象のファイルパスのCSVヘッダー書き込み<br>
	 * <li>カウント対象ディレクトリのステップカウント処理<br>
	 * </ol>
	 * 
	 * @param args コマンドライン引数の値、オプションを簡易に解析するクラス。
	 * @see Arguments
	 */
	private static void stepCountScriptMode(Arguments args) {
    	final String inputDirectoryPath = args.getScriptArguments().getInputDirectoryPath();
    	final String outputFilePath = args.getScriptArguments().getOutputFilePath();
 
		if (!ValidatorUtil.inputDirectoryCheck(inputDirectoryPath)) {
			System.out.println("--> ステップカウント処理を終了します。");
			return;
		}
		if (!ValidatorUtil.outputFileCheck(outputFilePath, CommandOptionType.SCRIPT)) {
			System.out.println("--> ステップカウント処理を終了します。");
			return;
		}
		execStepCount(new File(inputDirectoryPath), new File(outputFilePath));
	}
	/**
	 * <p>ステップカウント処理実行メソッド
	 * <p>引数のカウント対象のディレクトリ、カウント結果出力対象のファイルを元にステップカウント処理を実行する。
	 * <p>処理中に例外が発生した場合はエラーメッセージを出力し処理を終了する。
	 * 
	 * @param inputDirectory カウント対象のディレクトリ。
	 * @param outputFile カウント結果出力対象のファイル。
	 */
	private static void execStepCount(final File inputDirectory, final File outputFile) {
		try {
			logger.logInfo("ステップカウント処理開始");
			System.out.println("--> ステップカウント処理を開始します。");
			
			writeHeaderStepCount(outputFile);
			writeStepCountInDirectory(inputDirectory, outputFile);
			
			System.out.println("--> ステップカウント処理を終了します。");
			logger.logInfo("ステップカウント処理正常終了");
		} catch (Exception e) {
			logger.logWarn("ステップカウント処理異常終了");
			logger.logError(e);
			
			System.err.println("--> ステップカウント処理で異常が発生しました。処理を終了します。");
			return;
		}	
	}
	/**
	 * <p>CSVヘッダー書き込みメソッド
	 * <p>引数のカウント結果出力対象ファイルに対して、CSVのヘッダーの書き込み処理を行う。
	 * <p>CSVヘッダーの定義は{@link Constant#STEP_COUNT_HEADER_NAME}参照。
	 * 
	 * @param outputFile カウント結果出力対象ファイル
	 * @see Constant#STEP_COUNT_HEADER_NAME
	 */
	private static void writeHeaderStepCount(final File outputFile) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
			final StringJoiner sj = new StringJoiner(",");
			Constant.STEP_COUNT_HEADER_NAME.stream().forEach(r -> sj.add(r));
			bw.write(sj.toString());
		} catch (IOException e) {
			logger.logError(e);
		}
	}
	/**
	 * <p>ディレクトリに対するステップ数カウントメソッド
	 * <p>引数のカウント対象のディレクトリに対して再帰処理を行い、ステップカウント対象の拡張子をもつファイルに対して、<br>
	 * 総行数,実行行数,コメント行数,空行数を集計し、引数のカウント結果出力対象ファイルに結果の書き込み処理を行う。
	 * 
	 * @param inputDirectory カウント対象ディレクトリ
	 * @param outputFile カウント結果出力対象ファイル
	 * @throws Exception {@link IfCommentPatternMatch}、および{@link IfStepCount}のインスタンス生成時に例外が発生した場合。
	 * @see CommentPatternMatchType#containsExtension
	 * @see StepCountType#containsExtension
	 * @see CommentPatternMatchFactory#create
	 * @see StepCountFactory#create
	 * @see IfCommentPatternMatch
	 * @see IfStepCount#stepCount
	 */
	private static void writeStepCountInDirectory(final File inputDirectory, final File outputFile) throws Exception {
        File[] files = inputDirectory.listFiles();

        if (files == null) return;
        
        for (File inputFile : files) {
            if (inputFile.isDirectory()) {
            	writeStepCountInDirectory(inputFile, outputFile);
            } else if (inputFile.isFile()) {
                final String fileName = inputFile.getName();
                final String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim();   
                if (CommentPatternMatchType.containsExtension(extension) && StepCountType.containsExtension(extension)) {
					IfCommentPatternMatch commentPatternMatchObj = CommentPatternMatchFactory.create(extension);
					IfStepCount stepCountObj = StepCountFactory.create(extension, inputFile, outputFile, commentPatternMatchObj);
					logger.logInfo("ファイル名：" + fileName + "ステップカウント処理開始");
					stepCountObj.stepCount();
                } else {
                	logger.logWarn("--> ファイルの拡張子が対応していません。ステップカウント処理をスキップします。 ファイル名：" + fileName);
                }
            }
        }
    }
	/**
	 * <p>ヘルプメッセージ出力メソッド
	 * <p>ヘルプメッセージをコンソールに出力する。
	 */
	private static void printHelp() {
		System.out.println("Usage: java StepCountMain");
		System.out.println("       java StepCountMain [OPTIONS] inputDirectoryPath outputFilePath");
		System.out.println("OPTIONS");
		System.out.println("       -h:このメッセージを表示して終了する。");
		System.out.println("       -s:スクリプトモードで実行する。（オプションを指定しない場合は対話モード）");
	} 
}
