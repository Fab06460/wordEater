package wordEater.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wordEater.SimpleList.ISimpleListElement;
import wordEater.SimpleList.SimpleList;

class SimpleListTest {

	class ElementTestType implements ISimpleListElement<ElementTestType> {
		public String str;

		public ElementTestType(String str) {
			this.str = str;
		}
		
		@Override
		public int compareTo(ElementTestType t) {
			return str.compareTo(t.str);
		}
		
		@Override
		public String toString() {
			return str;
		}
		
	}
	protected SimpleList<ElementTestType> list;
	
	@BeforeEach
	void setUp() throws Exception {
		list = new SimpleList<ElementTestType>();
		list.insertAtIndex(1, new ElementTestType("Word1"));
		list.insertAtIndex(2, new ElementTestType("Word2"));
		list.insertAtIndex(3, new ElementTestType("Word3"));
		list.insertAtIndex(4, new ElementTestType("Word4"));
		list.insertAtIndex(5, new ElementTestType("Word5"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_length() {
		assertEquals(list.length(), 5);
		list.insertAtIndex(6, new ElementTestType("Word6"));
		assertEquals(list.length(), 6);
	}

	@Test
	void test_getAtIndex() {
		assertEquals("Word3", list.getAtIndex(3).str);
		assertEquals("Word5", list.getAtIndex(5).str);
		assertEquals("Word1", list.getAtIndex(1).str);
	}
	
	@Test
	void test_contains() {
		assertTrue(list.contains(new ElementTestType("Word4")));
		assertFalse(list.contains(new ElementTestType("Word10")));
	}

	@Test
	void test_insertAtIndex() {
		list.insertAtIndex(1, new ElementTestType("New word1"));
		assertEquals("New word1", list.getAtIndex(1).str);
		assertEquals("Word1", list.getAtIndex(2).str);
		
		// New word1, word1, word2, word3, word4, word5, word6
		// 1          2      3      4      5      6      7
		list.insertAtIndex(3, new ElementTestType("New word2"));
		// New word1, word1, New word2, word2, word3, word4, word5, word6
		// 1          2      3          4      5      6      7      8
		assertEquals("New word2", list.getAtIndex(3).str);
		assertEquals("Word2", list.getAtIndex(4).str);		
	}
	
	// TODO test array index out of bounds exceptions
}
