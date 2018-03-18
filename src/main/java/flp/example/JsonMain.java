package flp.example;

import java.util.Map;
import java.util.Map.Entry;

import flp.jsonSimple.parser.Context;
import flp.jsonSimple.parser.JsonObject;

public class JsonMain {

	public static void main(String[] args) {
		String json = "{  \"Herausgeber\": \"Xema\",  \"Nummer\": \"1234-5678-9012-3456\","
						+ "\"Deckung\": 2e+6, "
						+ "\"Waehrung\": \"EURO\",  \"Inhaber\":   {"
						+ "\"Name\": \"Mustermann\",    \"Vorname\": \"Max\",    \"maennlich\": true,"
						+ "\"Hobbys\": [ \"Reiten\", \"Golfen\", \"Lesen\" ],"
						+ "\"Alter\": 42, "
						+ "\"Kinder\": [],"
						+ "\"Partner\": null}}";
		

		JsonObject expression = new JsonObject();
		expression.interprete(new Context(json));
		printMap(expression.getValue());
		
		System.out.println("getValue = " + expression.get("Deckung"));
		}

	private static void printMap(Map<String, Object> values) {
		printMap(values, "");
	}
	private static void printMap(Map<String, Object> values, String praefix) {
		for(Entry<String, Object> e : values.entrySet()){
			String key = e.getKey();
			Object value = e.getValue();
			if(!(value instanceof JsonObject)){
				System.out.println(praefix + "Key: " + key + ", value: " + value + " (" + ((value != null)?value.getClass().getSimpleName():"null") + ")");
			}else{
				System.out.println(praefix + "Key: " + key + ", value: {");
				printMap( ((JsonObject)value).getValue() ,praefix + "    ");
				System.out.println(praefix + "}");
			}
			
		}
	}

}
