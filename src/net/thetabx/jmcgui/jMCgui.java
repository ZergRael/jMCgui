package net.thetabx.jmcgui;

import net.thetabx.jmcgui.Frames.MainFrame;

import javax.swing.*;

public class jMCgui {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
