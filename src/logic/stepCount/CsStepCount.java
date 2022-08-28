package logic.stepCount;

import java.io.File;
import java.util.Objects;

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
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof CsStepCount)) return false;
		
		CsStepCount test = (CsStepCount)obj;
		if (!(Objects.equals(this.inputFile, test.inputFile))) return false;
		if (!(Objects.equals(this.outputFile, test.outputFile))) return false;
		if (!(Objects.equals(this.commentPatternMatch, test.commentPatternMatch))) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash();
	}
}
