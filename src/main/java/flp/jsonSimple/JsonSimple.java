package flp.jsonSimple;

public class JsonSimple {
	/**
	 *  This Method parses a Json String and returns an Object
	 * @param json String
	 * @return Object
	 */
	public static JsonObject parse(String json){
		JsonObject jObject = new JsonObject();
		JsonObject parsedObject = jObject.interprete(new Context(json));
		return parsedObject;
	}
}
