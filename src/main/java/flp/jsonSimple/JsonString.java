package flp.jsonSimple;

/**
 * This Class parses a String Value
 * @author Flip
 *
 */
public class JsonString implements Expression {

	private String value;

	public Expression interprete(Context context) {
		value = "";
		if (context.getChar() == '"') {
			context.stepForward();
		}else{
			throw new RuntimeException("JsonString kein Hochkommata gefunden");
		}
		while (context.getChar() != '"') {
			value += context.getChar();
			if (context.getChar() == '\\') {
				context.stepForward();
				if (context.getChar() == '"') {
					value += context.getChar();
					context.stepForward();
				}
			} else {
				context.stepForward();
			}
		}
		context.stepForward();
		context.skipBlanks();
		return this;
	}

	public String getValue() {
		return value;
	}

	public boolean confirm(Context context) {
		return context.getChar() =='"';
	}

}
