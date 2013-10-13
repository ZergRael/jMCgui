package net.thetabx.jmcgui.Utils;

public class ChatColors {

    public static String removeColorTags(String str) {
        return str.replaceAll("�[0-9a-f]", "");
    }
}
