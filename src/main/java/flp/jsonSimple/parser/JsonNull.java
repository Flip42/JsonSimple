package flp.jsonSimple.parser;

/**
 * This Class parses a NULL Value
 * @author Flip
 *
 */
public class JsonNull implements Expression {

	private boolean valid = false;

	public Expression interprete(Context context) {
		context.eatIgnoreCase("null");
		context.skipBlanks();
		valid = true;
		return this;
	}

	public boolean confirm(Context context) {
		return Character.toLowerCase(context.getChar()) == 'n';
	}

	public Boolean getValue() {
		if(valid)
		return null;
		else
			throw new JsonException("Null is undefined");
	}

}
