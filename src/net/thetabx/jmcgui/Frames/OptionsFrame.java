package net.thetabx.jmcgui.Frames;

import net.thetabx.jmcgui.Utils.IniFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OptionsFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel pan = new JPanel();
    private JTextField txtFPS;
    private JButton btnSave;

    private IniFile iniFile;

    public OptionsFrame(final IniFile iniFile) {
        this.iniFile = iniFile;

        setType(Type.UTILITY);
        this.setTitle("Options");

        this.setSize(160, 99);

        this.setLocationRelativeTo(null);

        this.setContentPane(pan);
        pan.setLayout(new BorderLayout(0, 0));

        JPanel pButtons = new JPanel();
        pan.add(pButtons, BorderLayout.SOUTH);

        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                saveOptions();
                hideFrame();
            }
        });
        pButtons.add(btnSave);
        this.getRootPane().setDefaultButton(btnSave);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideFrame();
            }
        });
        pButtons.add(btnCancel);

        JPanel pOptions = new JPanel();
        pan.add(pOptions, BorderLayout.CENTER);

        JLabel lblNewLabel = new JLabel("FPS");
        pOptions.add(lblNewLabel);

        txtFPS = new JTextField();
        txtFPS.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent arg0) {
                if (isFPSValid(txtFPS.getText()))
                    setSaveButton(true);
                else
                    setSaveButton(false);
            }
        });
        pOptions.add(txtFPS);
        txtFPS.setColumns(3);
        txtFPS.setText("" + iniFile.getFPS());
    }

    public boolean isFPSValid(String str) {
        if (str.matches("\\d{1,3}") && !str.matches("0*"))
            return true;

        return false;
    }

    public void setSaveButton(boolean set) {
        btnSave.setEnabled(set);
    }

    public void saveOptions() {
        if (isFPSValid(txtFPS.getText()))
            iniFile.setFPS(txtFPS.getText());
        iniFile.store();
    }

    public void hideFrame() {
        this.setVisible(false);
    }
}
