package flp.jsonSimple;

import flp.jsonSimple.builder.JsonBuilder;
import flp.jsonSimple.parser.Context;
import flp.jsonSimple.parser.JsonObject;

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
	
	//TODO Testing and Docu
	public static JsonObject getObject(String json){
		JsonObject jObject = new JsonObject();
		JsonObject parsedObject = jObject.interprete(new Context(json));
		return parsedObject;
	}

	//TODO Testing and Docu
	public static String parse(Object obj) {
		JsonBuilder builder = new JsonBuilder();
		return builder.parse(obj);
	}
}
