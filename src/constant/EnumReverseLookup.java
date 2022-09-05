package constant;

import java.util.Arrays;
import java.util.function.Function;

/**
 * <p>
 * Enumのクラスとgetterから逆引き検索関数を生成するためのクラス。
 * 
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class EnumReverseLookup<E extends Enum<E>, ATTR> {
	/** 逆引き対象Enumクラス */
	private final Class<E> enumClass;
	/** 逆引きに利用するgetterメソッド */
	private final Function<E, ATTR> getter;

	/**
	 * <p>
	 * コンストラクタ
	 * 
	 * @param enumClass 逆引き対象Enumクラス
	 * @param getter    逆引きに利用するgetterメソッド
	 */
	public EnumReverseLookup(final Class<E> enumClass, final Function<E, ATTR> getter) {
		this.enumClass = enumClass;
		this.getter = getter;
	}

	/**
	 * <p>
	 * 拡張子の存在判定メソッド
	 * 
	 * @param attribute 判定対象の拡張子
	 * @return 対象のEnumが存在する場合はtrueを返却。それ以外の場合はfalseを返却する。
	 */
	public boolean containsExtension(ATTR attribute) {
		return Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> getter.apply(e).equals(attribute));
	}

	/**
	 * <p>
	 * Enum逆引きメソッド
	 * <p>
	 * 引数の拡張子に紐づくEnumを返却する。
	 * 
	 * @param attribute 判定対象の拡張子
	 * @return 対象のEnumを返却する。
	 * @throws IllegalArgumentException 対象のEnumが存在しない場合にthrowする。
	 */
	public E lookup(ATTR attribute) {
		return Arrays.stream(enumClass.getEnumConstants()).filter(e -> getter.apply(e).equals(attribute)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException());
	}
}