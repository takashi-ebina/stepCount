/**
 * 
 */
package logic.commentPatternMatch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestCommentPatternMatchFactory {

    @Nested
    class Create {
    	@Nested
        @DisplayName("正常系")
        class HappyCases {
            @DisplayName("Javaコメント判定用オブジェクト生成成功")
        	@Test
        	void success1() throws Exception {
        		// 【事前準備】
        		// 【実行】
            	IfCommentPatternMatch commentPatternMatchObj = CommentPatternMatchFactory.CommentPatternMatchType.of("java");
        		// 【検証】
        		assertEquals(new JavaCommentPatternMatch(), commentPatternMatchObj);
        		// 【後処理】
        	}
            @DisplayName("Csコメント判定用オブジェクト生成成功")
        	@Test
        	void success2() throws Exception {
        		// 【事前準備】
        		// 【実行】
            	IfCommentPatternMatch commentPatternMatchObj = CommentPatternMatchFactory.CommentPatternMatchType.of("cs");
        		// 【検証】
        		assertEquals(new CsCommentPatternMatch(), commentPatternMatchObj);
        		// 【後処理】
        	}
        }
       	@Nested
        @DisplayName("異常系")
    	class UnhappyCases {
            @DisplayName("Enum(CommentPatternMatchType)に定義されていない拡張子を引数に指定し、IllegalArgumentExceptionが発生")
        	@Test
        	void Exception1() throws Exception {
        		// 【事前準備】
        		// 【実行】
        		// 【検証】
        		assertThrows(IllegalArgumentException.class,() -> CommentPatternMatchFactory.CommentPatternMatchType.of("sql"));
        		// 【後処理】
        	}
    	}
    }
}
