package flp.JsonSimple;

public interface Expression {
	public Expression interprete(Context context);
	/**
	 * @param context the actual Context to be parsed
	 * @return true if the first Char belongs to the Object to be parsed.
	 * Otherwise false
	 */
	public boolean confirm(Context context);
	/**
	 * should only be called after interprete(..)
	 * @return the parsed Object
	 */
	public Object getValue();
}
