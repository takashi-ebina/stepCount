package sample1.constant;

import java.util.LinkedHashMap;
import java.util.Map;

import sample1.character.CharacterIf;
import sample1.character.HammerBros;
import sample1.character.Luigi;
import sample1.character.Mario;

public class Constant {
	/*
	 * Map型の定数
	 * key:キャラクター名
	 * value:キャラクターのインスタンス
	 */
	public static final Map<String, CharacterIf> CHARACTER_MAP = new LinkedHashMap<>();
	// 以下はクラスのロード時、クラス変数を初期化する処理（コンストラクタのstatic版みたいなもの）
	// 詳細は「スタティックイニシャライザ」で検索してください
	static {
		CHARACTER_MAP.put("Mario", new Mario());
		CHARACTER_MAP.put("Luigi", new Luigi());
		CHARACTER_MAP.put("HammerBros", new HammerBros());
	}
}
