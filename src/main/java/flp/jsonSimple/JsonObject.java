package flp.jsonSimple;

import java.util.*;

public class JsonObject implements Expression {

	private Map<String, Object> values;

	public JsonObject() {
		values = new HashMap<String, Object>();
	}

	public JsonObject interprete(Context context) {
		if (context.getChar() == '{') {
			boolean firstElement = true;
			context.stepForward();
			context.skipBlanks();
			while (context.getChar() != '}') {
				if (!firstElement) {
					if (context.getChar() != ',') {
						throw new JsonException(context, "JsonObject: kein Trenner(,) zwischen Value und Key erkannt.");
					}
					context.stepForward();
					context.skipBlanks();
				}
				JsonString key = new JsonString();
				key.interprete(context);
				if (context.getChar() != ':') {
					throw new JsonException(context, "JsonObject: kein Trenner(:) zwischen Key und Value erkannt.");
				}
				context.stepForward();
				context.skipBlanks();
				Expression value = new JsonValue();
				value = value.interprete(context);
				values.put(key.getValue(), value.getValue());
				firstElement = false;
			}
			if (context.getChar() == '}') {
				return this;
			}
		}
		throw new JsonException(context, "kein JsonObjekt erkannt");
	}

	public Map<String, Object> getValue() {
		return values;
	}

	public Object get(String key) {
		return values.get(key);
	}

	public boolean confirm(Context context) {
		return context.getChar() == '{';
	}

}
