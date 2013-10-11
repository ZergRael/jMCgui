package net.thetabx.jmcgui.MPWPackets;

public class McMetadata {

	private short index;
	private short type;
	private Object value;
	private Object[] values;
	
	public McMetadata(short index, short type, Object value)
	{
		this.setIndex(index);
		this.setType(type);
		this.setValue(value);
	}
	
	public McMetadata(short index, short type, Object[] values)
	{
		this.setIndex(index);
		this.setType(type);
		this.setValues(values);
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	/**
	 * @return the index
	 */
	public short getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(short index) {
		this.index = index;
	}
}
