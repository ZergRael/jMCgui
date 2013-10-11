package net.thetabx.jmcgui.Utils;

import net.thetabx.jmcgui.McConstants;

public class AddressSpliter {

    private String host;
    private int port = McConstants.DEFAULTPORT;

	public boolean split(String address)
	{
		if(address.lastIndexOf(':') != -1)
		{
			String[] addressSplit = address.split(":");
			host = addressSplit[0];
			
			if(!addressSplit[1].matches("\\d{1,5}"))
				return false;
			
			port = Integer.parseInt(addressSplit[1]);
		}
		else
			host = address;

        return host.matches("(\\d{1,3}\\.){3}\\d{1,3}") || host.matches("(\\w+\\.)+\\w{1,5}") || host.matches("localhost");

    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
