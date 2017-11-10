import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.swing.*;


public class Register extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel userL = new JLabel("Choose a Username : ");
	JTextField userTF = new JTextField();
	JLabel passL = new JLabel("Password: ");
	JPasswordField passTF = new JPasswordField();
	JLabel passcL = new JLabel("Confirm Password: ");
	JPasswordField passcTF = new JPasswordField();
	JPanel panel = new JPanel();
	JButton register = new JButton("Register");
	JButton back = new JButton("Go back");
	
	Register(){
		JPanel loginP = new JPanel(new GridLayout(4,2));
		loginP.add(userL);
		loginP.add(userTF);
		loginP.add(passL);
		loginP.add(passTF);
		loginP.add(passcL);
		loginP.add(passcTF);
		back.addActionListener(this);
		register.addActionListener(this);
		loginP.add(register);
		loginP.add(back);
		add(loginP);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == register && userTF.getText().length() > 0 && passTF.getPassword().length > 0 ){
			String pass = new String(passTF.getPassword());
			String confirm = new String(passcTF.getPassword());
			if(pass.equals(confirm)){
				try {
					BufferedReader input = new BufferedReader(new FileReader("passwords.txt"));
					String line = input.readLine();
					
					while(line!=null){
						StringTokenizer st = new StringTokenizer(line);
						if(userTF.getText().equals(st.nextToken())){
							System.out.println("User Already Exists");
							return;
						}
						line = input.readLine();
					
					}
					input.close();
					MessageDigest md = MessageDigest.getInstance("SHA-256");
					md.update(pass.getBytes());
					byte byteData[] = md.digest();
					StringBuffer sb = new StringBuffer();
					for (int i=0;i < byteData.length;i++)
						sb.append(Integer.toString((byteData[i] & 0xFF)+ 0x100,16).substring(i));
						
					BufferedWriter output = new BufferedWriter(new FileWriter("passwords.txt"));
					output.write(userTF.getText()+" "+sb.toString()+"\n");
					output.close();
					Login login = (Login)getParent();
					login.cl.show(login, "login");

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getSource()==back){
			Login login = (Login)getParent();
			login.cl.show(login, "login");
		}
	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("Text Editor");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(500,500);
//		Login login = new Login();
//		frame.add(login);
//		frame.setVisible(true);
//	}

}
