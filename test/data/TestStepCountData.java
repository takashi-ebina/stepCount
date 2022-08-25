package data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestStepCountData {
	private StepCountData stepCountData;
    @BeforeEach
    void setUp() {
    	stepCountData = new StepCountData();
    }
    @Nested
    @DisplayName("incrementTotalStepCount")
    class IncrementTotalStepCount {
        @DisplayName("総行数インクリメントテスト")
    	@Test
    	void success1() {
    		// 【事前準備】
    		// 【実行】
    		stepCountData.incrementTotalStepCount();
    		// 【検証】
    		Assertions.assertEquals(1, stepCountData.getTotalStepCount());
    		Assertions.assertEquals(0, stepCountData.getExecStepCount());
    		Assertions.assertEquals(0, stepCountData.getCommentStepCount());
    		Assertions.assertEquals(0, stepCountData.getEmptyStepCount());
    		// 【後処理】
    	}
    }
    @Nested
    @DisplayName("incrementExecStepCount")
    class IncrementExecStepCount {
    	@DisplayName("実行行数インクリメントテスト")
    	@Test
    	void success1() {
    		// 【事前準備】
    		// 【実行】
    		stepCountData.incrementExecStepCount();
    		// 【検証】
    		Assertions.assertEquals(0, stepCountData.getTotalStepCount());
    		Assertions.assertEquals(1, stepCountData.getExecStepCount());
    		Assertions.assertEquals(0, stepCountData.getCommentStepCount());
    		Assertions.assertEquals(0, stepCountData.getEmptyStepCount());
    		// 【後処理】
    	}
    }
    @Nested
    @DisplayName("incrementCommentStepCount")
    class IncrementCommentStepCount {
        @DisplayName("コメント行数インクリメントテスト")
    	@Test
    	void success1() {
    		// 【事前準備】
    		// 【実行】
    		stepCountData.incrementCommentStepCount();
    		// 【検証】
    		Assertions.assertEquals(0, stepCountData.getTotalStepCount());
    		Assertions.assertEquals(0, stepCountData.getExecStepCount());
    		Assertions.assertEquals(1, stepCountData.getCommentStepCount());
    		Assertions.assertEquals(0, stepCountData.getEmptyStepCount());
    		// 【後処理】
    	}
    }
    @Nested
    @DisplayName("incrementEmptyStepCount")
    class IncrementEmptyStepCount {
    	@DisplayName("空行数インクリメントテスト")
    	@Test
    	void success1() {
    		// 【事前準備】
    		// 【実行】
    		stepCountData.incrementEmptyStepCount();
    		// 【検証】
    		Assertions.assertEquals(0, stepCountData.getTotalStepCount());
    		Assertions.assertEquals(0, stepCountData.getExecStepCount());
    		Assertions.assertEquals(0, stepCountData.getCommentStepCount());
    		Assertions.assertEquals(1, stepCountData.getEmptyStepCount());
    		// 【後処理】
    	}
    }
    @Nested
    @DisplayName("toString")
    class ToString {
    	@DisplayName("StepCountDataで定義されている文字列表現のテスト（初期値）")
    	@Test
    	void success1() {
    		// 【事前準備】
    		// 【実行】		
    		// 【検証】
    		Assertions.assertEquals(
    				"stepCountData [totalStepCount=0, execStepCount=0, commentStepCount=0, emptyStepCount=0]", stepCountData.toString());
    		// 【後処理】
    	}
    	@DisplayName("StepCountDataで定義されている文字列表現のテスト（インクリメント実施後）")
    	@Test
    	void success2() {
    		// 【事前準備】
    		stepCountData.incrementTotalStepCount();
    		stepCountData.incrementExecStepCount();
    		stepCountData.incrementCommentStepCount();
    		stepCountData.incrementEmptyStepCount();
    		// 【実行】		
    		// 【検証】
    		Assertions.assertEquals(
    				"stepCountData [totalStepCount=1, execStepCount=1, commentStepCount=1, emptyStepCount=1]", stepCountData.toString());
    		// 【後処理】
    	}
    }
    @Nested
    @DisplayName("outputDataCommaDelimited")
    class OutputDataCommaDelimited {
    	@DisplayName("1ファイル単位の総行数／実行行数／コメント行数／空行数をカンマ区切りにした文字列のテスト（初期値）")
    	@Test
    	void success1() {
    		// 【事前準備】
    		// 【実行】		
    		// 【検証】
    		Assertions.assertEquals("0,0,0,0", stepCountData.outputDataCommaDelimited());
    		// 【後処理】
    	}
    	@DisplayName("1ファイル単位の総行数／実行行数／コメント行数／空行数をカンマ区切りにした文字列のテスト（インクリメント実施後）")
    	@Test
    	void success2() {
    		// 【事前準備】
    		stepCountData.incrementTotalStepCount();
    		stepCountData.incrementExecStepCount();
    		stepCountData.incrementCommentStepCount();
    		stepCountData.incrementEmptyStepCount();
    		// 【実行】		
    		// 【検証】
    		Assertions.assertEquals("1,1,1,1", stepCountData.outputDataCommaDelimited());
    		// 【後処理】
    	}
    }
}
