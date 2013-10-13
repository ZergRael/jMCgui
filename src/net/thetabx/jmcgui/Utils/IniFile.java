package net.thetabx.jmcgui.Utils;

import net.thetabx.jmcgui.McConstants;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;


public class IniFile {

    private Wini iniFile;
    private final String filename = "settings.ini";

    public IniFile() {
        try {
            File file = new File(filename);
            if (!file.exists())
                file.createNewFile();
            iniFile = new Wini(file);
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Launcher
    public String getLoginNickname() {
        if (iniFile.get("Login", "nickname") == null)
            return null;
        return iniFile.get("Login", "nickname");
    }

    public void setLoginNickname(String nickname) {
        iniFile.put("Login", "nickname", nickname);
    }

    public String getLoginPassword() {
        if (iniFile.get("Login", "password") == null)
            return null;
        return Encrypter.decrypt(iniFile.get("Login", "password"));
    }

    public void setLoginPassword(char[] passwordA) {
        iniFile.put("Login", "password", Encrypter.encrypt(new String(passwordA)));
    }

    // Servers
    public int getNbFavServers() {
        String nServers = iniFile.get("Servers", "count");
        return (nServers == null ? 0 : Integer.parseInt(nServers));
    }

    public void setNbFavServers(int n) {
        iniFile.put("Servers", "count", n);
    }

    public int getLastConnectedServer() {
        String nServers = iniFile.get("Servers", "last");
        return (nServers == null ? -1 : Integer.parseInt(nServers));
    }

    public void setLastConnectedServer(int n) {
        iniFile.put("Servers", "last", n);
    }

    public String getServerName(int favServerN) {
        return iniFile.get("Server" + favServerN, "name");
    }

    public void setServerName(int favServerN, String name) {
        iniFile.put("Server" + favServerN, "name", name);
    }

    public String getServerAddress(int favServerN) {
        return iniFile.get("Server" + favServerN, "address");
    }

    public void setServerAddress(int favServerN, String address) {
        iniFile.put("Server" + favServerN, "address", address);
    }

    public void removeServer(int favServerN) {
        int newNServers = this.getNbFavServers() - 1;
        for (int i = favServerN; i < newNServers; i++) {
            this.setServerName(i, this.getServerName(i + 1));
            this.setServerAddress(i, this.getServerAddress(i + 1));
        }
        this.setNbFavServers(newNServers);
        iniFile.remove("Server" + newNServers);
        this.store();

    }

    public int getFPS() {
        String fps = iniFile.get("Options", "FPS");
        return (fps != null ? Integer.parseInt(fps) : McConstants.DEFAULTFPS);
    }

    public void setFPS(String fps) {
        iniFile.put("Options", "FPS", fps);
    }

    public int getZoom() {
        String zoom = iniFile.get("Options", "Zoom");
        return (zoom != null ? Integer.parseInt(zoom) : McConstants.DEFAULTZOOM);
    }

    public void setZoom(int zoom) {
        iniFile.put("Options", "Zoom", zoom);
    }

    public void store() {
        try {
            iniFile.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
