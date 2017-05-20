package flp.jsonSimple;

public class JsonException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JsonException(Context context, String expected, String message) {
		super(message + " expected '" + expected + "' but is '" + context.getChar() + "' " + context.getDebugInfo());
	}

	public JsonException(Context context, String message) {
		super(message + " " + context.getDebugInfo());
	}

	public JsonException(String message) {
		super(message);
	}

	public JsonException(Context context) {
		super("JsonException " + context.getDebugInfo());
	}

}
