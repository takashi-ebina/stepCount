package data;

public class stepCountLineData {
	
	private int totalStepCountLine = 0;
	private int execStepCountLine = 0;
	private int commentStepCountLine = 0;
	private int emptyStepCountLine = 0;
	/**
	 * @return totalStepCountLine
	 */
	public int getTotalStepCountLine() {
		return totalStepCountLine;
	}
	/**
	 * @param totalStepCountLine セットする totalStepCountLine
	 */
	public void incrementTotalStepCountLine() {
		this.totalStepCountLine++;
	}
	/**
	 * @return execStepCountLine
	 */
	public int getExecStepCountLine() {
		return execStepCountLine;
	}
	/**
	 * @param execStepCountLine セットする execStepCountLine
	 */
	public void incrementExecStepCountLine() {
		this.execStepCountLine++;
	}
	/**
	 * @return commentStepCountLine
	 */
	public int getCommentStepCountLine() {
		return commentStepCountLine;
	}
	/**
	 * @param commentStepCountLine セットする commentStepCountLine
	 */
	public void incrementCommentStepCountLine() {
		this.commentStepCountLine++;
	}
	/**
	 * @return emptyStepCountLine
	 */
	public int getEmptyStepCountLine() {
		return emptyStepCountLine;
	}
	/**
	 * @param emptyStepCountLine セットする emptyStepCountLine
	 */
	public void incrementEmptyStepCountLine() {
		this.emptyStepCountLine++;
	}
	@Override
	public String toString() {
		return "stepCountLineData [";
	}
}
