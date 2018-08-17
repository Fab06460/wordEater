package wordEater;

import java.util.logging.Logger;

import wordEater.SimpleList.SimpleList;

/**
 * This class reads string (with addString() ), split the string in words,
 * and either:
 * - increment the occurrence of the word if it is already known
 * - adds the word with an occurrence of 1 to its ordered list.
 * Word are ordered by: 1) length, 2) ASCII order
 * 
 * @author fabricebelloncle
 */
public class Analyzer {
	private final static Logger log = Logger.getLogger(Analyzer.class.getName());
	
	// Data structure holding the sorted words and their occurrence. 
	private SimpleList<WordOccurrence> sortedWordList;

	/**
	 * Default constructor.
	 */
	public Analyzer() {
		sortedWordList = new SimpleList<WordOccurrence>();
	}
	
	/**
	 * Adds a string to the analyzer. The string is parsed and the words processed.
	 * @param s string to be added
	 */
	public void addString(String s) {
		if (s == null || s.isEmpty()) 
			return;
		
		// Split string into a word array ("'" [quote] is not a word separator)
		String[] words = s.split("[^\\w']");
		for(String word: words) {
			log.finest("word: " + word);
			
			if (!word.isEmpty())
				addWord(word);
		}
	}
		
	/**
	 * Generates a report with this format:
	 * <occurrence> <word>
	 * for all words currently contained in the Analyzer.
	 * words are sorted by 1) length, 2) ASCII value
	 * @return report as a String (empty string if no words in Analyzer)
	 */
	public String generateReport() {
		String report = "";
		for(int i=1; i<=sortedWordList.length(); i++) {
			try {
				report += sortedWordList.getAtIndex(i).toString() + "\n";				
			}
			catch(ArrayIndexOutOfBoundsException e) {
				log.severe("Array index is out of bounds (" + i + "): " + e.toString());
			}
		}
		return report;
	}
	
	/**
	 * Gets the WordOccurrence object for the given index.
	 * @param index
	 * @return WordOccurrence object or null if index is out of bounds.
	 */
	public WordOccurrence getWordOccurrenceAtIndex(int index) {
		WordOccurrence wo=null;
		try {
			wo = sortedWordList.getAtIndex(index);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			log.severe("Array index is out of bounds (" + index + "): " + e.toString());
			wo = null;
		}
		return wo;
	}
	
	/**
	 * Returns the number of words currently stored by the Analyzer object
	 * @return number of words
	 */
	public int numberOfWords() {
		return sortedWordList.length();
	}
	
	/**
	 * Adds a word to the sorted array.
	 * If the word already exists, increment the existing word's occurrence counter.
	 * If the word is a new word, find its position in the sorted array and insert it.
	 * @param aWord
	 */
	private void addWord(String aWord) {
		WordOccurrence wo = new WordOccurrence(aWord);
		int index = sortedWordList.indexOf(wo);
		if ( index != -1 ) {
			// word already exists in list, just increment its number of occurrence
			sortedWordList.getAtIndex(index).addOccurrence();
		}
		else {
			// new word
			int i = 1;
			while ( (i<=sortedWordList.length())
					&& (sortedWordList.getAtIndex(i).compareTo(wo)) < 0) {
				i++;
			}
			try {
				sortedWordList.insertAtIndex(i, wo);				
			}
			catch(ArrayIndexOutOfBoundsException e) {
				log.severe("Array index is out of bounds (" + i + "): " + e.toString());
			}
		}
	}
}
