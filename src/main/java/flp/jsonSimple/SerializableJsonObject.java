package flp.jsonSimple;

public interface SerializableJsonObject {
	
	public String toJson();
//	public boolean equals(Object obj);
	public SerializableJsonObject newInstance(String json) ;
}
