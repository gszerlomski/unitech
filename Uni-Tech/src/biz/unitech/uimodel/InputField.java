package biz.unitech.uimodel;

public class InputField {
	
	private String value;
	
	private boolean disabled = false;
	
	public InputField() {}

	public InputField(String value, boolean disabled) {
		this.value = value;
		this.disabled = disabled;
	}
	
	public InputField(int value, boolean disabled) {
		this(String.valueOf(value), disabled);
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public String getValue() {
		return value == null ? "" : value.trim();
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
