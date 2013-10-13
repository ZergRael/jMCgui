package net.thetabx.jmcgui;

import net.thetabx.jmcgui.Utils.HTTPRequest;

public class McLauncherLogin {

    public static Object[] request(String nickname, String password) {
        HTTPRequest httpR = new HTTPRequest("https://login.minecraft.net/?user=" + nickname + "&password=" + password + "&version=" + McConstants.LAUNCHERVERSION);
        httpR.send(true);
        String loginResponse = httpR.read();
        System.out.println("LoginResponse: " + loginResponse);

        int indexOf = loginResponse.indexOf(':');
        if (indexOf == -1)
            return null;

        String[] loginFields = loginResponse.split(":");
        return new Object[]{loginFields[2], loginFields[3]};
    }
}
