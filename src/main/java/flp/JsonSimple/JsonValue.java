package flp.JsonSimple;

/**
 * This Class parses all Values and returns the parsed Values or Objects
 * @author Flip
 *
 */
public class JsonValue implements Expression {

	private Expression[] types = { new JsonString(), new JsonNumber(), new JsonObject(), new JsonBoolean(),
			new JsonNull(), new JsonArray() };


	public Expression interprete(Context context) {
		Expression value = null;
		for (Expression e : types) {
			if (e.confirm(context)) {
				value = e;
			}
		}
		if(value == null){
			throw new JsonException(context, "JsonValue: kein Value erkannt");
		}
		value.interprete(context);
		return value;
	}

	public Object getValue() {
		return null;
	}

	public boolean confirm(Context context) {
		for (Expression e : types) {
			if (e.confirm(context)) {
				return true;
			}
		}
		return false;
	}

}
