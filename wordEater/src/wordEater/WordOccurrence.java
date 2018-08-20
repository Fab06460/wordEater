package wordEater;

import wordEater.SimpleList.ISimpleListElement;

/**
 * This class stores a word and its associated number of occurrence.
 * @author fabricebelloncle
 *
 */
public class WordOccurrence implements ISimpleListElement<WordOccurrence> {
	private String word;
	private int occurrences;

	public WordOccurrence(String word) {
		this.word = (word==null?"":word);
		this.occurrences = 1;
	}
	
	/**
	 * 
	 * @return returns the word
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * 
	 * @return returns the occurrence
	 */
	public int getOccurrences() {
		return occurrences;
	}
	
	/**
	 * Increments the number of occurrence
	 */
	public void addOccurrence() {
		this.occurrences++;
	}

	/**
	 * Compares the word contained in the local object with the word given in parameter. First comparison
	 * criteria is the size of the word. For words of same size, compare ASCII values.
	 * @param w1
	 * @return Returns less than 0 if local word is lesser than w1, 0 if equal, more than 0 if greater than w1
	 */
	public int compareTo(WordOccurrence wo) {
		// shorter words are always smaller
		if ( word.length() < wo.getWord().length() )
			return -1;			
		// longer words are always bigger
		if ( word.length() > wo.getWord().length() ) 
			return 1;			
		// compare words of same length
		return word.compareTo(wo.getWord());
	}
	
	/**
	 * 
	 * @return object content as string (number of occurrence + white space + word)
	 */
	public String toString() {
		return occurrences + " " + word;
	}
}
