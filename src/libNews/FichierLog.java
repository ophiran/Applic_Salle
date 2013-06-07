package libNews;

import java.io.*;

import javax.swing.JTextArea;

public class FichierLog {

	private JTextArea textArea;
	private String path;
	
	public FichierLog() {
		// TODO Auto-generated constructor stub
	}
	
	public void setJTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public void addLine(String log) {
		if(textArea != null)
			textArea.append(log);
		try
		{
			FileWriter file = new FileWriter(path, true);
			BufferedWriter buffer = new BufferedWriter(file);
			buffer.write(log);
			buffer.newLine();
			buffer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
