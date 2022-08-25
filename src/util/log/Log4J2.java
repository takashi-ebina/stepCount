
package util.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>ロギング処理を行うクラス
 * @since 1.0
 * @version 1.0
 * @author takashi.ebina
 */
public class Log4J2 {

	/**
	 * <p>唯一のLog4J2インスタンス
	 */
	private static Log4J2 thisinstance  = null;
	
	/**
	 * <p>インスタンス返却メソッド
	 * @return Log4J2インスタンス
	 */
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

	private String getMessage(final String msg) {
		// 自分のクラス名を取得(Log4J2)
		final String thisClassName = this.getClass().getName();
		// StackTraceElementの配列を取得
		final StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		int pos = 0;
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			// クラス名比較
			if(thisClassName.equals(stackTraceElement.getClassName())) {
				break;	// stackTraceElementsから自分と同じクラス名だったら終了
			}
			pos++;
		}
		pos += 2;		// 出力したいクラス名/メソッド名は自分(MyLog4J)の2個次の位置にいる
		final StackTraceElement m = stackTraceElements[pos];
		// ログ出力対象のクラス名:[メソッド名] + log message
		return m.getClassName() + ":" + m.getMethodName()+ "() " + msg;
	}

	/**
	 * <p>Log4J2でデバッグレベルの情報をロギングする
	 * @param msg デバッグメッセージ
	 */
	public void logDebug(final String msg) {
		Logger logger = LogManager.getLogger(this.getClass());
		logger.debug("{}", this.getMessage(msg));
	}

	/**
	 * <p>Log4J2でinfoレベルの情報をロギングする
	 * @param msg 出力メッセージ
	 */
	public void logInfo(final String msg) {
		Logger logger = LogManager.getLogger(this.getClass());
		logger.info("{}", this.getMessage(msg));
	}

	/**
	 * <p>Log4J2でinfoレベルの情報をロギングする
	 * @param obj ログが出力される Class Object
	 * @param msg 出力メッセージ
	 */
	public void logInfo(final Object obj, final String msg) {
		Logger logger = LogManager.getLogger(obj.getClass());
		logger.info("{}", msg);
	}

	/**
	 * <p>Log4J2で警告レベルの情報をロギングする
	 * @param msg 警告メッセージ
	 */
	public void logWarn(final String msg) {
		Logger logger = LogManager.getLogger(this.getClass());
		logger.warn("{}", this.getMessage(msg));
	}

	/**
	 * <p>Log4J2でエラーレベル情報ををロギングする
	 * @param e 例外情報
	 */
	public void logError(final Exception e) {
		final String msg = e.getMessage();
		final Class<? extends Object> clss = e.getClass();		// Exceptionのクラス
		final String clsname = e.getClass().getName();			// Exceptionのクラス名
		final StackTraceElement[] st = e.getStackTrace();
		String logMsg = "";
		if (st != null && st.length > 0) {
			logMsg += "Class:" + clsname+ "¥n";
			logMsg += "Detail:" + msg + "¥n";
			for (int i=0; i<st.length ; i++) {
				String err   = st[i].toString();
				logMsg += err + "¥n";
			}
			Logger logger = LogManager.getLogger(clss);
			logger.error("{}", logMsg);
		}
	}
}
		