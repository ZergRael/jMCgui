package net.thetabx.jmcgui.Frames;

import net.thetabx.jmcgui.Utils.IniFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FavServersAddFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel pan = new JPanel();
    private JTextField txtAddress;
    private JTextField txtName;

    public FavServersAddFrame(final IniFile iniFile) {
        this.setTitle("Favorite servers");

        this.setSize(200, 170);

        this.setLocationRelativeTo(null);

        this.setContentPane(pan);
        pan.setLayout(new BorderLayout(0, 0));

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniFile.setServerName(iniFile.getNbFavServers(), txtName.getText());
                iniFile.setServerAddress(iniFile.getNbFavServers(), txtAddress.getText());
                iniFile.setNbFavServers(iniFile.getNbFavServers() + 1);
                iniFile.store();
                hideFrame();
            }
        });
        pan.add(btnAdd, BorderLayout.SOUTH);
        this.getRootPane().setDefaultButton(btnAdd);

        JPanel panel = new JPanel();
        pan.add(panel, BorderLayout.CENTER);

        JLabel lblName = new JLabel("Name");
        panel.add(lblName);

        txtName = new JTextField();
        panel.add(txtName);
        txtName.setColumns(18);

        JLabel lblAddress = new JLabel("Address");
        panel.add(lblAddress);

        txtAddress = new JTextField();
        panel.add(txtAddress);
        txtAddress.setColumns(18);
    }

    public void hideFrame() {
        this.setVisible(false);
    }
}
