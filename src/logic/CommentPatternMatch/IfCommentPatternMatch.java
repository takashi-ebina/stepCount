package logic.CommentPatternMatch;

public interface IfCommentPatternMatch {

	public boolean isSingleCommentPattern(String target);
	public boolean isStartMultiCommentPattern(String target);
	public boolean isEndMultiCCommentPattern(String target);
}
