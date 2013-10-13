package net.thetabx.jmcgui.MPWObjects;

public class Metadata {

    private short index;
    private short type;
    private Object value;
    private Object[] values;

    public Metadata(short index, short type, Object value) {
        this.setIndex(index);
        this.setType(type);
        this.setValue(value);
    }

    public Metadata(short index, short type, Object[] values) {
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

    public short getIndex() {
        return index;
    }

    public void setIndex(short index) {
        this.index = index;
    }
}
