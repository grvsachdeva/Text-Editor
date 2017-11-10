import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.ViewFactory;

//import com.sun.java.util.jar.pack.Package.File;


public class FileBrowser extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JLabel label = new JLabel("File List: ");
	JButton newFile = new JButton("New File ");
	JButton open = new JButton("Open");
	JTextField newFileTF = new JTextField(10);
	ButtonGroup bg;
	File directory;
	
	public FileBrowser(String dir) {
		directory = new File(dir);
		directory.mkdir();
		JPanel fileList = new JPanel(new GridLayout(directory.listFiles().length+3,1));
		fileList.add(label);
		bg = new ButtonGroup();
		
		for(File file: directory.listFiles()){
			JRadioButton radio = new JRadioButton(file.getName());
			radio.setActionCommand(file.getName());
			bg.add(radio);
			fileList.add(radio);
		}
		JPanel newp = new JPanel();
		newp.add(newFileTF);
		newp.add(newFile);
		newFile.addActionListener(this);
		open.addActionListener(this);
		fileList.add(open);
		fileList.add(newp);
		add(fileList);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		Login login = (Login)getParent();
	
		if(e.getSource()==open){
			login.add(new Editor(directory.getName()+"\\"+bg.getSelection().getActionCommand()),"editor"); 
			login.cl.show(login, "editor");
			
		}
		if(e.getSource()==newFile){
			String file = directory.getName()+"\\"+newFileTF.getText()+".txt";
			if(newFileTF.getText().length() > 0 && !(new File(file).exists())){
				login.add(new Editor(file),"editor");
			}
		}
	}

}
