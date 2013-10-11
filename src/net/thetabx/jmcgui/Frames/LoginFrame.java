package net.thetabx.jmcgui.Frames;

import javax.swing.*;

import net.thetabx.jmcgui.McLauncherLogin;
import net.thetabx.jmcgui.Utils.IniFile;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame{

	private IniFile iniFile;
	
	private static final long serialVersionUID = 1L;
	private JPanel pan = new JPanel();
	private JTextField txtNickname;
	private JPasswordField txtPassword;
	
	private Object[] nicknameSessionId;
	private boolean isLoggedIn = false;
	
	public LoginFrame(final IniFile iniFile)
	{
		setResizable(false);
		this.setTitle("Log In");
		
		this.setSize(180, 160);
		
		this.setLocationRelativeTo(null);

		this.setContentPane(pan);
		pan.setLayout(new BorderLayout(0, 0));
		
		JPanel pTextFields = new JPanel();
		pan.add(pTextFields, BorderLayout.CENTER);
		
		JLabel lblNickname = new JLabel("Nickname");
		pTextFields.add(lblNickname);
		
		txtNickname = new JTextField();
		txtNickname.setColumns(18);
		pTextFields.add(txtNickname);
		
		JLabel lblPassword = new JLabel("Password");
		pTextFields.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(18);
		pTextFields.add(txtPassword);
		
		this.iniFile = iniFile;
		
		this.txtNickname.setText(iniFile.getLoginNickname());
		this.txtPassword.setText(iniFile.getLoginPassword());
		
		JPanel panel = new JPanel();
		pan.add(panel, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeFields();
				hideFrame();
			}
		});
		panel.add(btnSave);
		
		JButton btnConnect = new JButton("Connect");
		panel.add(btnConnect);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeFields();
				if(!launcherLogin()) {
					JOptionPane.showMessageDialog(null, "Incorrect Login/Password", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				hideFrame();
			}
		});
		this.getRootPane().setDefaultButton(btnConnect);
	}
	
	public boolean launcherLogin()
	{
		nicknameSessionId = McLauncherLogin.request(iniFile.getLoginNickname(), iniFile.getLoginPassword());
		if(nicknameSessionId == null)
			return false;
		
		isLoggedIn = true;
		return true;
	}
	
	private void storeFields()
	{
		iniFile.setLoginNickname(this.txtNickname.getText());
		iniFile.setLoginPassword(this.txtPassword.getPassword());
		iniFile.store();
	}
	
	public void hideFrame() {
		this.setVisible(false);
	}
	
	public String getRealNickname() {
		return (String) nicknameSessionId[0];
	}
	
	public String getSessionId() {
		return (String) nicknameSessionId[1];
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
}
