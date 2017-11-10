import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

//import com.sun.java.util.jar.pack.Package.File;


public class FileBrowser extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JLabel label = new JLabel("File List: ");
	JButton newFile = new JButton("New File ");
	JButton open = new JButton("Open");
	JTextField newFileTF = new JTextField();
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
		fileList.add(open);
		fileList.add(newp);
		add(fileList);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
