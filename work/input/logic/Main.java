package sample1.logic;

import java.util.Scanner;

import sample1.character.CharacterIf;
import sample1.constant.Constant;

public class Main {

	/*
	 * メイン処理（ポリモーフィズムの利用あり）
	 * 
	 * 【処理の流れについて】
	 * １．実行したいキャラクター名を入力
	 * ２．入力した文字列に紐づくキャラクターのインスタンスを取得
	 * ３．取得したインスタンスからattackメソッドを実施
	 * 
	 *【実装のポイント】
	 *　このソースのポイントは、他のキャラクターを追加する際に、
	 *　mainメソッドの処理を修正する必要がないということです
	 *
	 *　例）クッパを追加したい場合の改修内容
	 * １．クッパクラス（Kuppa.java）を作成(Mario.javaなどを参考に)
	 * ２．Constant.javaのCHARACTER_MAPにクッパに関す定義を追加
	 *
	 * ※コメントは少し過剰に記載しています
	 */
	public static void main(String[] args) {

		System.out.println("動かしたいキャラクターを入力してください。（入力例:Mario)");
		
		int i = 1;
		for (String key : Constant.CHARACTER_MAP.keySet()) {
			// Constant.java内のlinkedHashMapから、Key値を取り出して出力
			// 「Java入門：P.635 - P.637」参照
			System.out.println("キャラクター" + i + ":" + key);
			i++;
		}
		
		Scanner scanner = new Scanner(System.in);
		
		// try-catch文
		// 「Java入門：P.666 - P.679」参照
		try {
			// 文字列入力
			// 「Java入門：P.92 - P.93」参照
			String characterName = scanner.nextLine();
			
			// インスタンスの取得
			// CharacterIf型の変数で取得することで、ポリモーフィズムのメリットである条件分岐の削減が可能
			CharacterIf c = Constant.CHARACTER_MAP.get(characterName);
			
			if (c instanceof CharacterIf) {
				// Character.javaのattackメソッドを実行
				c.attack();	
			} else {
				// CharacterIf型のインスタンスを取得できなかった場合はメッセージを出力
				System.out.println(characterName + "は存在しないキャラクター名です！！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (scanner != null) {
					scanner.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
