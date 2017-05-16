package flp.JsonSimple;

/**
 * This Class parses all Numbers
 * @author Flip
 *
 */
public class JsonNumber implements Expression {
	
	private Number value = null;

	public Expression interprete(Context context) {
		String number="";
		while(confirm(context) || context.getChar() == '.' || context.getChar() == 'e' || context.getChar() == 'E'){
			number += context.getChar();
			context.stepForward();
		}
		context.skipBlanks();
		process(number);
		return this;
	}

	private void process(String number) {
		try{
			value =Integer.valueOf(number);
			return;
		}catch(NumberFormatException i){
			try{
				value =Double.valueOf(number);
				return;
			}catch(NumberFormatException d){
				throw new JsonException("JsonNumber: cannot cast \"" + number + "\" to a Integer or Double.");
			}
		}
	}

	public boolean confirm(Context context) {
		return context.getChar() =='-' || context.getChar() =='+' || (context.getChar() >='0' && context.getChar() <='9');
	}
	
	public Number getValue(){
		return value;
	}

}
