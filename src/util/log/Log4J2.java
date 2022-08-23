
package util.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
* Log4J2 ロギング処理を行う
*/
public class Log4J2 {

	/**
	 * 唯一のLog4J2インスタンス
	 */
	private static Log4J2 thisinstance  = null;

	public synchronized static Log4J2 getInstance() {
		if (Log4J2.thisinstance == null) {
			Log4J2.thisinstance = new Log4J2();
		}
		return Log4J2.thisinstance;
	}

	/** 
	 * <p>コンストラクタ
	 * <p>Singleton実装の為、外部からのインスタンス化は不可とする。
	 */
	private Log4J2() {}

	private String getMessage(String msg) {
		//自分のクラスを取得(Log4J2)
		Class<?> c = this.getClass();
		//クラス名を取得
		String thisClassName = c.getName();
		//カレントスレッドを取得
		Thread t = Thread.currentThread();
		//StackTraceElementの配列を取得
		StackTraceElement[] stackTraceElements = t.getStackTrace();
		int pos = 0;
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			//クラス名比較
			if(thisClassName.equals(stackTraceElement.getClassName())) {
				break;	//stackTraceElementsから自分と同じクラス名だったら終了
			}
			pos++;
		}
		pos += 2;		//出力したいクラス名/メソッド名は自分(MyLog4J)の2個次の位置にいる
		StackTraceElement m = stackTraceElements[pos];
		//ログ出力対象のクラス名:[メソッド名] + log message
		String log_str = m.getClassName() + ":" + m.getMethodName()+ "() " +msg;
		return log_str;
	}

	/**
	 * Log4J2でデバッグレベルの情報をロギングする
	 * @param obj : ログが出力される Class Object
	 * @param msg : デバッグメッセージ
	 */
	public void logDebug(String msg) {
		Logger logger = LogManager.getLogger(this.getClass());
		logger.debug("{}", this.getMessage(msg));
	}

	/**
	 * Log4J2でinfoレベルの情報をロギングする
	 * @param msg : 出力メッセージ
	 */
	public void logInfo(String msg) {
		Logger logger = LogManager.getLogger(this.getClass());
		logger.info("{}", this.getMessage(msg));
	}

	/**
	 * Log4J2でinfoレベルの情報をロギングする
	 * @param obj : ログが出力される Class Object
	 * @param msg : 出力メッセージ
	 */
	public void logInfo(Object obj, String msg) {
		Logger logger = LogManager.getLogger(obj.getClass());
		logger.info("{}", msg);
	}

	/**
	 * Log4J2で警告レベルの情報をロギングする
	 * @param obj : ログが出力される Class Object
	 * @param msg : 警告メッセージ
	 */
	public void logWarn(String msg) {
		Logger logger = LogManager.getLogger(this.getClass());
		logger.warn("{}", this.getMessage(msg));
	}

	/**
	 * Log4J2でエラーレベル情報ををロギングする
	 * @param e    : 例外情報
	 */
	public void logError(Exception e) {
		String msg = e.getMessage();
		Class<? extends Object> clss = e.getClass();		//Exceptionのクラス
		String clsname = e.getClass().getName();			//Exceptionのクラス名
		StackTraceElement[] st = e.getStackTrace();
		String logMsg = "";
		if ( st != null && st.length > 0 ) {
			logMsg += "Class:" + clsname+ "¥n";
			logMsg += "Detail:" + msg + "¥n";
			for(int i=0; i<st.length ; i++) {
				String err   = st[i].toString();
				logMsg += err + "¥n";
			}
			Logger logger = LogManager.getLogger(clss);
			logger.error("{}", logMsg);
		}
	}
}
		