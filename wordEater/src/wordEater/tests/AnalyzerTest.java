package wordEater.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wordEater.Analyzer;

class AnalyzerTest {

	Analyzer analyzer;
	
	@BeforeEach
	void setUp() throws Exception {
		analyzer = new Analyzer();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_addString() {
		analyzer.addString("The quick brown fox jumped over the lazy brown dog's back");
		// 1 The, 1 fox, 1 the, 1 back, 1 lazy, 1 over, 2 brown, 1 dog's, 1 quick, 1 jumped
		// 1      2      3      4       5       6       7        8        9        10
		assertEquals(analyzer.getWordOccurrenceAtIndex(4).getWord(), "back");
		assertEquals(analyzer.getWordOccurrenceAtIndex(7).getWord(), "brown");
		assertEquals(analyzer.getWordOccurrenceAtIndex(7).getOccurrences(), 2);
		
		assertTrue(!analyzer.generateReport().isEmpty());
	}

}
