package net.thetabx.jmcgui.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class Pinger {

    public static long ping(String address) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process ping = runtime.exec("ping " + address + " -n 1");
        BufferedReader result = new BufferedReader(new InputStreamReader(ping.getInputStream()));
        if (result.readLine() == null)
            return -1;
        result.readLine(); // Sending
        String response = result.readLine();
        String[] splitted = response.split(" ");
        return Long.parseLong(splitted[splitted.length - 3].split("=")[1]);
    }
}
