package libNews;

import java.io.*;

import javax.swing.JTextArea;

/**
 *
 * @author ekym
 */
public class FichierLog {

	private JTextArea textArea;
	private String path;
	
	/**
     *
     */
    public FichierLog() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     *
     * @param textArea
     */
    public void setJTextArea(JTextArea textArea) {
		this.textArea = textArea;
		if(path != null)
			readLog();
	}
	
	/**
     *
     * @param path
     */
    public void setLogPath(String path){
		this.path = path;
		if(textArea != null)
			readLog();
	}
	
	private void readLog()
	{
		try{
			File tmpFile = new File(path);
			if(tmpFile.exists()){
				FileReader file = new FileReader(path);
				textArea.read(file, null);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     *
     * @param log
     */
    public void addLine(String log) {
		if(textArea != null)
		{
			textArea.append(log + "\n");
		}
		try {
			FileWriter file = new FileWriter(path, true);
			BufferedWriter buffer = new BufferedWriter(file);
			buffer.write(log);
			buffer.newLine();
			buffer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
