package sample1.character;

/*【抽象クラス】implements CharacterIf */
public abstract class Character  {

	/*
	 * 汎用攻撃メソッド
	 * Main.java上で、各サブクラスのインスタンスから呼び出される
	 * サブクラス共通の攻撃処理は抽象クラスのattackメソッドに集約し、
	 * サブクラス固有の処理は、characterAttackメソッドで実装してもらう
	 */
	public void attack() {
		System.out.println("----攻撃を開始します----");
		// 各サブクラス（Mario.javaなど）で実装されたcharacterAttackメソッドを実行
		characterAttack();
		System.out.println("----攻撃を終了します----");
	}
	
	/*
	 * 【抽象メソッド】
	 * キャラクター固有の攻撃メソッド
	 * 
	 * 固有の処理(characterAttackメソッド)は、
	 * 外部（Main.java）から直接呼び出して欲しくない
	 * （attackメソッドを実行して、各キャラクタークラスの共通処理を実施した後に、キャラクター固有の攻撃を行いたい）
	 * 
	 * 上記理由から、アクセス修飾子を「protected」
	 * ※アクセス修飾子がpublicで良いなら、基本的に抽象メソッドはインターフェース側（CharacterIf.java）
	 * に定義する形で良いとおもいます。（使い分けが難しい・・）
	 */
	protected abstract void characterAttack(); 
	
}
