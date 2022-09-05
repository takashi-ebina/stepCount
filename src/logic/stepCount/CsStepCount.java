package logic.stepCount;

import java.util.Objects;

import data.StepCountData;
import logic.commentPatternMatch.IfCommentPatternMatch;

public class CsStepCount extends AbsStepCount {

	public CsStepCount(final IfCommentPatternMatch commentPatternMatch) {
		super(commentPatternMatch);
	}

	@Override
	protected StepCountData fileReadStepCount(final StepCountData stepCountData) {
		// FIXME Csファイル用のステップ数カウント処理は未実装
		return null;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof CsStepCount)) return false;
		
		CsStepCount test = (CsStepCount)obj;
		if (!(Objects.equals(this.commentPatternMatch, test.commentPatternMatch))) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash();
	}
}
