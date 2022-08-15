package logic.stepCount;

import java.io.File;

import logic.CommentPatternMatch.IfCommentPatternMatch;

public class JavaStepCount extends AbsStepCount {

	public JavaStepCount(File inputFile, File outputFile, IfCommentPatternMatch commentPatternMatch) {
		super(inputFile, outputFile, commentPatternMatch);
	}

}
