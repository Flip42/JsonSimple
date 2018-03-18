package flp.jsonSimple.parser;

/**
 * This Class encapsulates the String with the Json to the Json Parser
 * @author Flip
 *
 */
public class Context {
	
	private  final String context;
	private int position;

	public Context(String context) {
		this.context = context;
		position = 0;
	}
	public char getChar(){
        if ((position >= 0) && (position < context.length())) {
		return context.charAt(position);
        }
//        throw new JsonException("Context.getChar() reached End of String");
        return '*';
	}
	public String eat(String s){
    	if (s == null) {
    		throw new JsonException("Context.eat(null) is not allowed");
    	}
        for(char c : s.toCharArray()){
        	if (this.getChar() != c) {
        		throw new JsonException(this,s,"Context.eat(..)");
        	}
        	this.nextChar();
        }
        return "";
	}
	public String eatIgnoreCase(String s){
    	if (s == null) {
    		throw new JsonException("Context.eat(null) is not allowed");
    	}
        for(char c : s.toCharArray()){
        	if (Character.toLowerCase(this.getChar()) != c) {
        		throw new JsonException(this,s,"Context.eat(..)");
        	}
        	this.nextChar();
        }
        return "";
	}
	public char nextChar(){
		stepForward();
		return getChar();
	}
	public void stepForward(){
		position++;
	}
	public void skipBlanks(){
		while(getChar() == ' ' || getChar() == '\t' || getChar() == '\n'){
			stepForward();
		}
	}
	protected String getDebugInfo() {
		int line = context.substring(0, position).split("\n").length;
		String debug = "at Line " + line + " ("+getChar()+")";
		
		return debug;
	}

}
