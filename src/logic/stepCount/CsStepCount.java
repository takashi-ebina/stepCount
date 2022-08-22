package logic.stepCount;

import java.io.File;

import data.StepCountData;
import logic.commentPatternMatch.IfCommentPatternMatch;

public class CsStepCount extends AbsStepCount {

	public CsStepCount(File inputFile, File outputFile, IfCommentPatternMatch commentPatternMatch) {
		super(inputFile, outputFile, commentPatternMatch);
	}

	@Override
	protected StepCountData fileReadStepCount(StepCountData sclData) {
		// FIXME Csファイル用のステップ数カウント処理は未実装
		return null;
	}

}
