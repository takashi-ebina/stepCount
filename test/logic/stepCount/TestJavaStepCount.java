//package logic.stepCount;
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
//import data.StepCountData;
//import factory.StepCountFactory;
//import logic.commentPatternMatch.JavaCommentPatternMatch;
//
//class TestJavaStepCount {
//	private File inputFile;
//	private File outputFile;
//	private JavaCommentPatternMatch jcpm;
//	private JavaStepCount stepCountObj;
//    @BeforeEach
//    void setUp() throws Exception {
//    	inputFile = new File("/Applications/Eclipse_2021-06.app/Contents/workspace/stepCount_ver2/work/input/character/Characte.java");
//    	outputFile = new File("/Applications/Eclipse_2021-06.app/Contents/workspace/stepCount_ver2/work/output/result.csv");
//    	jcpm = new JavaCommentPatternMatch();
//    	stepCountObj = (JavaStepCount)StepCountFactory.create("java", inputFile, outputFile, jcpm);
//    }
//    @Nested
//    class fileReadStepCount {
//    	@Nested
//        @DisplayName("正常系")
//        class HappyCases {
//            @DisplayName("Javaステップ数集計")
//        	@Test
//        	void success1() throws Exception {
//        		// 【事前準備】
//        		// 【実行】
//            	StepCountData stepCountData = stepCountObj.fileReadStepCount(new StepCountData());
//            	System.out.println(stepCountData.toString());
//        		// 【検証】
//        		assertEquals(33, stepCountData.getTotalStepCount());
//        		assertEquals(9, stepCountData.getExecStepCount());
//        		assertEquals(20, stepCountData.getCommentStepCount());
//        		assertEquals(4, stepCountData.getEmptyStepCount());
//        		// 【後処理】
//        	}
//        }
//        @DisplayName("集計対象ファイル：空ファイル")
//    	@Test
//    	void success2() throws Exception {
//    		// 【事前準備】
//    		// 【実行】
//        	StepCountData stepCountData = stepCountObj.fileReadStepCount(new StepCountData());
//        	System.out.println(stepCountData.toString());
//    		// 【検証】
//    		assertEquals(33, stepCountData.getTotalStepCount());
//    		assertEquals(9, stepCountData.getExecStepCount());
//    		assertEquals(20, stepCountData.getCommentStepCount());
//    		assertEquals(4, stepCountData.getEmptyStepCount());
//    		// 【後処理】
//    	}
//    }
//}
