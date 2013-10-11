package net.thetabx.jmcgui.Utils;

import java.util.EventObject;

//Declare the event. It must extend EventObject.
public class CustEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private Object data;

	public CustEvent(Object source, String title) {
		super(source);
		this.title = title;
	}
	
	public CustEvent(Object source, String title, Object data) {
		super(source);
		this.title = title;
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	
	public String getTitle() {
		return title;
	}
}
