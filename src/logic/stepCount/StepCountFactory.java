package logic.stepCount;

import constant.EnumReverseLookup;
import logic.commentPatternMatch.IfCommentPatternMatch;

/**
 * <p>
 * ステップカウントクラスの生成に利用するFactoryクラス
 * <p>
 * {@link IfStepCount}をimplementsしたクラスオブジェクトを生成する場合は、<br>
 * {@link StepCountType#of(String, IfCommentPatternMatch)}の引数に生成するオブジェクトに紐づく拡張子を渡してください。
 * <p>
 * 対応する拡張子は{@link StepCountType}に定義されています。
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
	 * <p>
	 * ステップカウントクラスの種別をもつ列挙型クラス
	 */
	public enum StepCountType {
		/** Javaステップカウントオブジェクト */
		Java("java", new JavaStepCount()),
		/** Csステップカウントオブジェクト */
		Cs("cs", new CsStepCount());

		/** 拡張子 */
		private final String extension;
		/** ステップカウントオブジェクト */
		private final IfStepCount stepCount;

		/**
		 * <p>
		 * コンストラクタ
		 * 
		 * @param extension    拡張子
		 * @param stepCount ステップカウントオブジェクト
		 */
		StepCountType(final String extension, final IfStepCount stepCount) {
			this.extension = extension;
			this.stepCount = stepCount;
		}

		/**
		 * <p>
		 * 拡張子の値を返却するメソッド
		 * 
		 * @return 拡張子
		 */
		public String getExtension() {
			return this.extension;
		}

		/**
		 * <p>
		 * ステップカウントオブジェクトを返却するメソッド
		 * 
		 * @return ステップカウントオブジェクト
		 */
		public IfStepCount getStepCount() {
			return this.stepCount;
		}

		/**
		 * <p>
		 * 拡張子に対応するステップカウントオブジェクトを返却するメソッド
		 * <p>
		 * 拡張子から該当のEnumを取得する処理は{@link EnumReverseLookup#lookup(Object)}に移譲しています。
		 * 
		 * @param extension 拡張子
		 * @throws IllegalArgumentException 拡張子に対応するクラスが存在しない場合
		 * @return 拡張子に対応するステップカウントオブジェクト
		 */
		public static IfStepCount getByStepCount(final String extension) throws IllegalArgumentException {
			return new EnumReverseLookup<>(StepCountType.class, StepCountType::getExtension)
					.lookup(extension).getStepCount();
		}
		
		/**
		 * <p>
		 * 拡張子に対応するEnumの存在判定メソッド
		 * <p>
		 * 拡張子から該当のEnumを取得する処理は{@link EnumReverseLookup#containsAttribute(Object)}に移譲しています。
		 * 
		 * @param extension 拡張子
		 * @return 拡張子に対応するEnumが存在する場合trueを返却。それ以外の場合はfalseを返却する。
		 */
		public static boolean containsExtension(final String extension) {
			return new EnumReverseLookup<>(StepCountType.class, StepCountType::getExtension)
					.containsAttribute(extension);
		}

		/**
		 * <p>
		 * 引数の拡張子に対応するステップカウントオブジェクトを返却するメソッド
		 * 
		 * @param extension           拡張子
		 * @param commentPatternMatch コメントパターン判定用オブジェクト
		 * 
		 * @throws IllegalArgumentException 引数の拡張子に対応するステップカウントオブジェクトが存在しない場合
		 * @return ステップカウントオブジェクト
		 */
		public static IfStepCount of(final String extension, final IfCommentPatternMatch commentPatternMatch) {
			return getByStepCount(extension).create(commentPatternMatch);
		}
	}
}
