package wordEater.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wordEater.WordOccurrence;

class WordOccurrenceTest {
	WordOccurrence wo1;
	WordOccurrence wo2;
	
	@BeforeEach
	void setUp() throws Exception {
		wo1 = new WordOccurrence("Word1");
		wo2 = new WordOccurrence("Word2");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_occurrences() {
		int occ1 = wo1.getOccurrences();
		wo1.addOccurrence();
		assertEquals(occ1+1, wo1.getOccurrences());
	}
	
	@Test
	void test_compareTo() {
		// compare length of words
		WordOccurrence wshort = new WordOccurrence("abc");
		WordOccurrence wlong = new WordOccurrence("abcd");
		assertTrue(wshort.compareTo(wlong) < 0);
		
		WordOccurrence wbefore = new WordOccurrence("abcdef");
		WordOccurrence wafter = new WordOccurrence("abcdeg");
		assertTrue(wbefore.compareTo(wafter) < 0);
		
		WordOccurrence wweird = new WordOccurrence("&é'(§!");		
		assertTrue(wweird.compareTo(wafter) < 0);
	}

}
