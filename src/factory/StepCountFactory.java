package factory;

import java.io.File;

import constant.EnumReverseLookup;
import logic.commentPatternMatch.IfCommentPatternMatch;
import logic.stepCount.AbsStepCount;
import logic.stepCount.CsStepCount;
import logic.stepCount.IfStepCount;
import logic.stepCount.JavaStepCount;
/**
 * <p>ステップカウントクラスの生成に利用するFactoryクラス。
 * <p>{@link IfStepCount}をimplementsしたクラスオブジェクトを生成する場合は、<br>
 * {@link StepCountFactory#create(String, File, File, IfCommentPatternMatch)}の引数に生成するオブジェクトに紐づく拡張子を渡してください。
 * <p>対応する拡張子は{@link StepCountType}に定義されています。
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 * 
 * @see IfStepCount
 * @see AbsStepCount
 * @see JavaStepCount
 * @see CsStepCount
 */
public class StepCountFactory {
	/**
	 * ステップカウントクラスの種別をもつ列挙型クラス
	 */
	public enum StepCountType {
		/** Javaステップカウントオブジェクト */
		Java("java", "logic.stepCount.JavaStepCount"),
		/** Csステップカウントオブジェクト */
		Cs("cs", "logic.stepCount.CsStepCount");
		/** 拡張子 */
		private final String extension;
		/** クラス名（完全修飾子） */
		private final String className;
		/** 
		 * コンストラクタ
		 * @param extension 拡張子
		 * @param className クラス名（完全修飾子）
		 */
		StepCountType(final String extension, final String className) {
			this.extension = extension;
			this.className = className;
		}
	    /**
	     * <p>拡張子の値を返却するメソッド
	     * @return 拡張子
	     */
		public String getExtension() {
			return this.extension;
		}
	    /**
	     * <p>クラス名（完全修飾子）の値を返却するメソッド
	     * @return クラス名（完全修飾子）
	     */
		public String getClassName() {
			return this.className;
		}
	    /**
	     * <p>拡張子に対応するクラス名（完全修飾子）の値を返却するメソッド
	     * <p>拡張子から該当のEnumを取得する処理は{@link EnumReverseLookup#lookup(Object)}に移譲しています。
	     * @param extension 拡張子
	     * @throws IllegalArgumentException 拡張子に対応するクラスが存在しない場合
	     * @return 拡張子に対応するクラス名（完全修飾子）
	     */
		public static String getByClassName(final String extension) throws IllegalArgumentException {
			return new EnumReverseLookup<>(StepCountType.class, StepCountType::getExtension).lookup(extension).getClassName();
		}
	    /**
	     * <p>拡張子に対応するEnumの存在判定メソッド
	     * <p>拡張子から該当のEnumを取得する処理は{@link EnumReverseLookup#containsExtension(Object)}に移譲しています。
	     * @param extension 拡張子
	     * @return 拡張子に対応するEnumが存在する場合trueを返却。それ以外の場合はfalseを返却する。
	     */
		public static boolean containsExtension(final String extension) {
			return new EnumReverseLookup<>(StepCountType.class, StepCountType::getExtension).containsExtension(extension);
		}
	}
    /**
     * <p>拡張子に対応するステップカウントオブジェクトを生成するメソッド
     * @param extension 拡張子
     * @param inputFile ステップカウント対象ファイル
     * @param outputFile ステップカウント結果出力ファイル
     * @param commentPatternMatch コメント判定用クラス
     * @throws Exception {@link IfStepCount}のインスタンス生成時に例外が発生した場合。
     * @return ステップカウントオブジェクト
     */
	public static IfStepCount create (final String extension, final File inputFile, final File outputFile, IfCommentPatternMatch commentPatternMatch) throws Exception {
		IfStepCount stepCountObj = null;
		String className = StepCountType.getByClassName(extension);
		try {
			stepCountObj = (IfStepCount)Class.forName(className)
					.getDeclaredConstructor(File.class, File.class, IfCommentPatternMatch.class)
					.newInstance(inputFile, outputFile, commentPatternMatch);
		} catch (Exception e) {
			System.out.println("--> [ERROR]:インスタンスを生成することができませんでした。 クラス名：" + className);
			throw e;
		}
		return stepCountObj;
	}
//	Enumやリフレクションをわざわざ使わなくても、以下の実装で問題ないはず
//	public static IfStepCount create (final String extension, final File inputFile, final File outputFile, IfCommentPatternMatch commentPatternMatch)　{
//		IfStepCount stepCountObj = null;
//		if ("java".equals(extension)) {
//			stepCountObj = new JavaStepCount(inputFile, outputFile, commentPatternMatch);
//		} else if ("cs".equals(extension)) {
//			stepCountObj = new CsStepCount(inputFile, outputFile, commentPatternMatch);
//		}
//		return stepCountObj;
//	}
}
