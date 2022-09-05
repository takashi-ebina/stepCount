///**
// * 
// */
//package factory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.File;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//import logic.commentPatternMatch.CsCommentPatternMatch;
//import logic.commentPatternMatch.JavaCommentPatternMatch;
//import logic.stepCount.CsStepCount;
//import logic.stepCount.IfStepCount;
//import logic.stepCount.JavaStepCount;
//
//class TestStepCountFactory {
//	private File inputFolder;
//	private File outputFile;
//	private JavaCommentPatternMatch jcpm;
//	private CsCommentPatternMatch ccpm;
//    @BeforeEach
//    void setUp() {
//    	inputFolder = new File("/work/input");
//    	outputFile = new File("/work/output/result.csv");
//    	jcpm = new JavaCommentPatternMatch();
//    	ccpm = new CsCommentPatternMatch();
//    }
//    @Nested
//    class Create {
//    	@Nested
//        @DisplayName("正常系")
//        class HappyCases {
//            @DisplayName("Javaステップカウントオブジェクト生成成功")
//        	@Test
//        	void success1() throws Exception {
//        		// 【事前準備】
//        		// 【実行】
//            	IfStepCount stepCountObj = StepCountFactory.create("java", inputFolder, outputFile, jcpm);
//        		// 【検証】
//        		assertEquals(new JavaStepCount(inputFolder, outputFile, jcpm), stepCountObj);
//        		// 【後処理】
//        	}
//            @DisplayName("Csステップカウントオブジェクト生成成功")
//        	@Test
//        	void success2() throws Exception {
//        		// 【事前準備】
//        		// 【実行】
//            	IfStepCount stepCountObj = StepCountFactory.create("cs", inputFolder, outputFile, ccpm);
//        		// 【検証】
//        		assertEquals(new CsStepCount(inputFolder, outputFile, ccpm), stepCountObj);
//        		// 【後処理】
//        	}
//        }
//       	@Nested
//        @DisplayName("異常系")
//    	class UnhappyCases {
//            @DisplayName("Enum(StepCountType)に定義されていない拡張子を引数に指定し、IllegalArgumentExceptionが発生")
//        	@Test
//        	void Exception1() throws Exception {
//        		// 【事前準備
//        		// 【実行】
//        		// 【検証】
//        		assertThrows(IllegalArgumentException.class,() -> StepCountFactory.create("Java", inputFolder, outputFile, jcpm));
//        		// 【後処理】
//        	}
//    	}
//    }
//
//}
