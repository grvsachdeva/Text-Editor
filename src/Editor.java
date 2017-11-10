import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Editor extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	File file;
	JButton save = new JButton("Save");
	JButton savec = new JButton("Save and Close");
	JTextArea text = new JTextArea(20,40);
	public Editor(String s) {
		file  = new File(s);
		save.addActionListener(this);
		savec.addActionListener(this);
		if(file.exists()){
			
			try {
				BufferedReader input = new BufferedReader(new FileReader(file));
				String line = input.readLine();
				while(line!=null){
					text.append(line+"\n");
					line = input.readLine();
				}
				input.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		add(save);
		add(savec);
		add(text);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			FileWriter out = new FileWriter(file);
			out.write(text.getText());
			out.close();
			if(arg0.getSource()==savec){
				Login login = (Login)getParent();
				login.cl.show(login,"fb");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
