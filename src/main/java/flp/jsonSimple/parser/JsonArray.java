package flp.jsonSimple.parser;

import java.util.ArrayList;
import java.util.List;

public class JsonArray implements Expression {

	private final List<Object> values;

	public JsonArray() {
		values = new ArrayList<Object>();
	}

	public Expression interprete(Context context) {
		if (context.getChar() == '[') {
			boolean firstElement = true;
			context.stepForward();
			context.skipBlanks();
			while (context.getChar() != ']') {
				if (!firstElement) {
					if (context.getChar() != ',') {
						throw new JsonException(context, ",", "JsonArray: kein Trenner zwischen Value und Key erkannt.");
					}
					context.stepForward();
					context.skipBlanks();
				}
				Expression value = new JsonValue();
				value = value.interprete(context);
				values.add(value.getValue());
				firstElement = false;
			}
			if (context.getChar() == ']') {
				context.stepForward();
				context.skipBlanks();
				return this;
			}
		}
		throw new JsonException(context, "kein JsonObjekt erkannt");
	}

	public List<Object> getValue() {
		return values;
	}

	public boolean confirm(Context context) {
		return context.getChar() == '[';
	}

}
