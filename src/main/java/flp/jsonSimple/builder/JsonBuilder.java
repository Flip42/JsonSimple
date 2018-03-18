package flp.jsonSimple.builder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonBuilder {

	private Map<String, String> builder = new HashMap<String, String>();

	public String parse(Object obj) {
		builder.put("_class","\"" + obj.getClass().getName() + "\"");

		Method[] methods = obj.getClass().getMethods();
		for (Method method : methods) {
			String name = method.getName();
			if (name.startsWith("get")) {
				String variableName = getVariableName(name);
				Class<?> returnType = method.getReturnType();
//				returnType.asSubclass(Number.class);
//				List<Class<?>> numbers = Arrays.asList(Byte.class, Short.class); 
				if (returnType.isPrimitive()) {
					String returnTypeName = returnType.getTypeName();
					String value = "";
					switch (returnTypeName) {
					case "byte":
					case "short":
					case "int":
					case "char":
					case "double":
						value = getNumberValue(obj, method);
						builder.put(variableName, value);
//						System.out.println("Primitive detected: " + variableName + " (" + returnTypeName
//								+ ") with value: " + value);
						break;
					default:
						throw new RuntimeException("Primitive " + returnTypeName + " could not be read");
					}
				} else if (String.class.equals(returnType)) {
					String value = "";
					value = getStringValue(obj, method);
					builder.put(variableName, value);
//					System.out.println("String detected: " + variableName + " with value: " + value);
				}
			} else if (name.startsWith("is")) {
				String variableName = isVariableName(name);
				if ("boolean".equals(method.getReturnType().getTypeName())) {
					String value = getBooleanValue(obj, method);
					builder.put(variableName, value);
//					System.out.println("Boolean Getter detected: " + variableName + " with value " + value);
				}
			}
		}
		String json = build();

		return json;
	}

	private String build() {
		List<String> sortedList = new LinkedList<String>();
		sortedList.addAll(builder.keySet());
		Collections.sort(sortedList);
		StringBuilder json = new StringBuilder("{");
		boolean first = true;
		for (String key : sortedList) {
			if(!first){
				json.append(",");
			}
			first = false;
			json.append("\"" + key + "\":" + builder.get(key));
		}
		json.append("}");
		return json.toString();
	}

	private String getBooleanValue(Object obj, Method method) {
		String value = getPlainStringValue(obj, method);
		return value;
	}

	private String getNumberValue(Object obj, Method method) {
		String value = getPlainStringValue(obj, method);
		return value;
	}

	// get and format a StringValue for Json
	private String getStringValue(Object obj, Method method) {
		String value = getPlainStringValue(obj, method);
		return "\"" + value.replace("\"", "\\\"") + "\"";
	}

	private String getPlainStringValue(Object obj, Method method) {
		String value = "";
		try {
			value = method.invoke(obj).toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return value;
	}

	/**
	 * removes the first Three Letters and lowers the new first Letter. <br>
	 * Example: 'getName' becomes 'name'
	 */
	private String getVariableName(String methodName) {
		return Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
	}

	/**
	 * removes the first Two Letters and lowers the new first Letter. <br>
	 * Example: 'isValid' becomes 'valid'
	 */
	private String isVariableName(String methodName) {
		return Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
	}

}
