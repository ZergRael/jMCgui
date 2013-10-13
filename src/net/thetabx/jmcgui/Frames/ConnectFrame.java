package net.thetabx.jmcgui.Frames;

import net.thetabx.jmcgui.Utils.IniFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel pan = new JPanel();
    private IniFile iniFile;
    private JTextField txtAddress;
    private JTextField txtPassword;
    private JLabel lblPingresult;

    public ConnectFrame(IniFile iniFile) {
        setResizable(false);
        this.setTitle("ConnectFrame");

        this.setSize(200, 180);

        this.setLocationRelativeTo(null);

        this.setContentPane(pan);
        pan.setLayout(new BorderLayout(0, 0));


        JPanel pTextFields = new JPanel();
        pan.add(pTextFields, BorderLayout.CENTER);
        pTextFields.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblAddress = new JLabel("Address");
        pTextFields.add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setColumns(20);
        pTextFields.add(txtAddress);

        JLabel lblPassword = new JLabel("Password");
        pTextFields.add(lblPassword);

        txtPassword = new JTextField();
        txtPassword.setColumns(20);
        pTextFields.add(txtPassword);

        JLabel lblPing = new JLabel("Ping");
        pTextFields.add(lblPing);

        lblPingresult = new JLabel("");
        pTextFields.add(lblPingresult);


        JPanel pBotButtons = new JPanel();
        pan.add(pBotButtons, BorderLayout.SOUTH);

        JButton btnCheck = new JButton("Check");
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                storeFields();
                pingCheck();
            }
        });
        pBotButtons.add(btnCheck);

        JButton btnConnect = new JButton("Connect");
        btnConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storeFields();
                hideFrame();
            }
        });
        pBotButtons.add(btnConnect);
        this.getRootPane().setDefaultButton(btnConnect);

        this.iniFile = iniFile;
        this.setDefaultFields();
    }

    private void pingCheck() {
        try {
            this.lblPingresult.setText("" + (30 + Math.round(Math.random() * 50)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void storeFields() {
        //iniFile.setServerAddress(this.txtAddress.getText());
        //iniFile.setServerPassword(this.txtPassword.getText());
        iniFile.store();
    }

    private void setDefaultFields() {
        //this.txtAddress.setText(iniFile.getServerAddress());
        //this.txtPassword.setText(iniFile.getServerPassword());
    }

    public void hideFrame() {
        this.setVisible(false);
    }

    /**
     * @return the txtAddress
     */
    public JTextField getTxtAddress() {
        return txtAddress;
    }

    /**
     * @return the txtPassword
     */
    public JTextField getTxtPassword() {
        return txtPassword;
    }
}
