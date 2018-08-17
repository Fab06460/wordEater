package wordEater.SimpleList;

/**
 * This interface must be implemented by the SimpleList element type
 * @author fabricebelloncle
 *
 * @param <T>
 */
public interface ISimpleListElement<T> {
	/**
	 * Compares value hold in this element with t
	 * @param t element to compare with the one hold in this.
	 * @return
	 */
	public int compareTo(T t);
}
