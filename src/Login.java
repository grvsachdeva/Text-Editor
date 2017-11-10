import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Login extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel userL = new JLabel("UserName : ");
	JTextField userTF = new JTextField();
	JLabel passL = new JLabel("Password: ");
	JPasswordField passTF = new JPasswordField();
	JPanel loginP = new JPanel(new GridLayout(3,2));
	JPanel panel = new JPanel();
	JButton registerb = new JButton("Register");
	JButton loginb = new JButton("Login");
	CardLayout cl;
	
	Login(){
		setLayout(new CardLayout());
		loginP.add(userL);
		loginP.add(userTF);
		loginP.add(passL);
		loginP.add(passTF);
		loginb.addActionListener(this);
		registerb.addActionListener(this);
		loginP.add(loginb);
		loginP.add(registerb);
		panel.add(loginP);
		add(panel,"login");
		cl = (CardLayout)getLayout();
	}
	public void actionPerformed(ActionEvent arg0) {
		add(new Register(),"register");
		cl.show(this, "register");
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Text Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		Login login = new Login();
		frame.add(login);
		frame.setVisible(true);
	}

}
