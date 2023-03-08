
public class Property {

	//========================== Properties
	private String fieldName;
	private String dataType;
	
	//========================== Constructors
	public Property(String fieldName, String dataType) {
		setFieldName(fieldName);
		setDataType(dataType);
	}
	
	//========================== Getters/Setters
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
