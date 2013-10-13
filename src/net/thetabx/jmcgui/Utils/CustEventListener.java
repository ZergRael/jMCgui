package net.thetabx.jmcgui.Utils;

import java.util.EventListener;

//Declare the listener class. It must extend EventListener.
//A class must implement this interface to get MyEvents.
public interface CustEventListener extends EventListener {
    public void custEventOccurred(CustEvent evt);
}