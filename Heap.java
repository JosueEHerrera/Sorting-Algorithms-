
public interface Heap<V extends Comparable<V>> {
	
	public static enum MODE {MAX, MIN};

	/**
	 * @param value
	 */
	public void add(V value);

	/**
	 * @return
	 */
	public V[] toArray(V[] array);

	/**
	 * @return
	 */
	public V remove();
	
	/**
	 * @param array
	 */
	public void fromArray(V[] array);

	/**
	 * @return
	 */
	public V[] getSortedContents(V[] array);
	
	/**
	 * @return
	 */
	public MODE getMode();
	
	/**
	 * @param mode
	 */
	public void setMode(MODE mode);

}
