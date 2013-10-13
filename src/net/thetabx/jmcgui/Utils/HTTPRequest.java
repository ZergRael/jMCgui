package net.thetabx.jmcgui.Utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPRequest {

    private URL url;
    private HttpURLConnection httpC;
    private BufferedReader in;

    public HTTPRequest(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void send(Boolean secure) {
        try {
            if (secure)
                httpC = (HttpsURLConnection) url.openConnection();
            else
                httpC = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(httpC.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
