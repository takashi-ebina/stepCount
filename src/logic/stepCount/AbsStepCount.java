package logic.stepCount;

import java.io.File;

import logic.CommentPatternMatch.IfCommentPatternMatch;

public abstract class AbsStepCount implements IfstepCount {

	protected final File inputFile;
	protected final File outputFile;
	protected final IfCommentPatternMatch commentPatternMatch;
	
	public AbsStepCount(File inputFile, File outputFile, IfCommentPatternMatch commentPatternMatch) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		this.commentPatternMatch = commentPatternMatch;
	}
	@Override
	public void stepCount() {
	}

}
