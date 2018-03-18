package flp.jsonSimple.parser;

/**
 * This Class parses a Boolean Value
 * @author Flip
 *
 */
public class JsonBoolean implements Expression {

	private Boolean value = null;

	public Expression interprete(Context context) {
		if (Character.toLowerCase(context.getChar()) == 'f') {
			context.eatIgnoreCase("false");
			context.skipBlanks();
			value = new Boolean(false);
			return this;
		} else if (Character.toLowerCase(context.getChar()) == 't') {
			context.eatIgnoreCase("true");
			context.skipBlanks();
			value = new Boolean(true);
			return this;
		}
		throw new JsonException(context,"true' or 'false","Context.eat(..)");
	}

	public boolean confirm(Context context) {
		return (Character.toLowerCase(context.getChar()) == 't' || Character.toLowerCase(context.getChar()) == 'f');
	}

	public Boolean getValue() {
		return value;
	}

}
