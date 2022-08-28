/**
 * 
 */
package logic.commentPatternMatch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TestJavaCommentPatternMatch {

	private JavaCommentPatternMatch jcpm;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	@BeforeEach
	void setUp() throws Exception {
		jcpm = new JavaCommentPatternMatch();
	}
	@AfterEach
	void tearDown() throws Exception {
	}

    @Nested
    class isSingleCommentPattern {
    	@Nested
        @DisplayName("正常系")
        class HappyCases {
            @DisplayName("先頭に１行コメントが存在する場合、trueを返却")
            @ParameterizedTest
            @ValueSource(strings = {
                    "// 各サブクラス（Mario.javaなど）で実装されたcharacterAttackメソッドを実行",
                    "// 各サブクラス（Mario.javaなど）で実装されたcharacterAttackメソッドを実行 */",
            })
        	void success1(String target) {
        		// 【事前準備】
        		// 【実行】
        		// 【検証】
        		assertEquals(true, jcpm.isSingleCommentPattern(target));
        		// 【後処理】
            }
            @DisplayName("先頭に１行コメントが存在しない場合、falseを返却")
            @ParameterizedTest
            @ValueSource(strings = {
                    "public boolean isCommentLine = false //コメント行ではなく、実行行として扱います",
                    "/* public boolean isCommentLine = false //１行コメントではありません",
                    "/* public boolean isCommentLine = false //１行コメントではありません */",
                    "* public boolean isCommentLine = false //１行コメントではありません",
                    "public boolean isCommentLine = false //１行コメントではありません */",
                    "public boolean isCommentLine = /* false */ true //１行コメントではありません"
            })
        	void success2(String target) {
        		// 【事前準備】
        		// 【実行】
        		// 【検証】
        		assertEquals(false, jcpm.isSingleCommentPattern(target));
        		// 【後処理】
            }
    	}
    }
//    @Nested
//    class isStartMultiCommentPattern {
//    	@Nested
//        @DisplayName("正常系")
//        class HappyCases {
//            @DisplayName("Javaステップカウントオブジェクト生成成功")
//        	@Test
//        	void success1() throws Exception {
//            	
//            }
//    	}
//    }
//    @Nested
//    class isEndMultiCommentPattern {
//    	@Nested
//        @DisplayName("正常系")
//        class HappyCases {
//            @DisplayName("Javaステップカウントオブジェクト生成成功")
//        	@Test
//        	void success1() throws Exception {
//            	
//            }
//    	}
//    }
}
