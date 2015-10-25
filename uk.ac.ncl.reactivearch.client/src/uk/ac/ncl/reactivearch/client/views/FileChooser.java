package uk.ac.ncl.reactivearch.client.views;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class FileChooser {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private static String fname;
	private static File fis;
	private static String hash;
	
	private static String openMess, saveMess;

	private static void createAndShowGUI() {

		// Create and set up the window.
		final JFrame frame = new JFrame("Centered");

		// Display the window.
		frame.setSize(300, 300);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		
		// set flow layout for the frame
		frame.getContentPane().setLayout(new FlowLayout());

		createFileChooser(frame);
	}
	
	//Setters
	public static void setFileName(String file){
		fname = file;
	}
	
	public static void setFile(File file){
		fis = file;
	}
	
	//Getters
	public static File getFile(){
		return fis;
	}
	
	public static String getFileName(){
		return fname;
	}
	
	public static String getAppendMess(){
		return openMess+"\n"+saveMess;
	}
	
	//Hash of File
	public static String getFileHash(){
		return hash;
	}
	
	/*//Developer ID [Performed in the Server]
	public static String getDevID(){
		String uniqueID = UUID.randomUUID().toString();
		
		String[] parts = uniqueID.split("-");
		String part1 = parts[0];
		String part2 = parts[1]; 
		//String part3 = parts[2]; 
		//String part4 = parts[3]; 
		//String part5 = parts[4]; 
		
		String dev = "Dev"+part1+part2;
		
		System.out.println("Developer ID: "+dev);
		return dev;
	}
	*/
	
	private static void createFileChooser(final JFrame frame) {

		String filename = File.separator+"tmp";
		JFileChooser fileChooser = new JFileChooser(new File(filename));

		// pop up an "Open File" file chooser dialog
		fileChooser.showOpenDialog(frame);
		openMess = "[INFO] File to open: " + fileChooser.getSelectedFile();
		ResponseTextFrame.setTestInTextArea(openMess);
		System.out.println(openMess);
		
				
		//Filename
		filename = fileChooser.getSelectedFile().getName();
		setFileName(filename);
		
		//Send File to JSON method
		setFile(fileChooser.getSelectedFile());
		
		
		//HashCode
		//fileChooser.getSelectedFile().hashCode()+filename.hashCode();
		try {
			String filehash = generateBufferedHash(fileChooser.getSelectedFile());
			hash = filehash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// pop up an "Save File" file chooser dialog
		fileChooser.showSaveDialog(frame);
		saveMess = "[INFO] File to save: " + fileChooser.getSelectedFile()+"\n";
		
    			ResponseTextFrame.setTestInTextArea(saveMess);
		System.out.println(saveMess);
	}
	
	@SuppressWarnings("resource")
	public static String generateBufferedHash(File file)
			throws NoSuchAlgorithmException,
			FileNotFoundException, IOException {
		 
			MessageDigest md = MessageDigest.getInstance("MD5");
		 
			InputStream is= new FileInputStream(file);
		 
			byte[] buffer=new byte[8192];
		        int read=0;
		 
		        while( (read = is.read(buffer)) > 0)
		                md.update(buffer, 0, read);
		 
		        byte[] md5 = md.digest();
		        BigInteger bi=new BigInteger(1, md5);
		 
		        return bi.toString(16);
		}
	
	public static void show(){
		/*ResponseTextFrame.setTestInTextArea(Utility.getCurrentDate());
		System.out.println("\n***********************************************************");
    	System.out.println("[INFO] Initiating Artefact(s) Selection Operation");
    	System.out.println("***********************************************************\n");*/
    	
    	//Append
    	String message = "\n***********************************************************"
    			+ "[INFO] Initiating Artefact(s) Selection Operation"
    			+"***********************************************************\n";
    			ResponseTextFrame.setTestInTextArea(message);
    			
    	//Schedule a job for the event-dispatching thread:
    			//creating and showing this application's GUI.
		createAndShowGUI();
	}
	
	public static void runFileChooser() {
		ResponseTextFrame.setTestInTextArea(Utility.getCurrentDate());
		System.out.println("\n***********************************************************");
    	System.out.println("[INFO] Initiating Artefact(s) Selection Operation");
    	System.out.println("***********************************************************\n");
    	
    	//Append
    	String message = "\n***********************************************************"
    			+ "[INFO] Initiating Artefact(s) Selection Operation"
    			+"***********************************************************\n";
    			ResponseTextFrame.setTestInTextArea(message);
    			
		//Schedule a job for the event-dispatching thread:
			//creating and showing this application's GUI.
		createAndShowGUI(); 
 
    }
	
	public static void main(String[] args) {
		ResponseTextFrame.setTestInTextArea(Utility.getCurrentDate());
		System.out.println("\n***********************************************************");
    	System.out.println("[INFO] Initiating Artefact(s) Selection Operation");
    	System.out.println("***********************************************************\n");
    	
    	//Append
    	String message = "\n***********************************************************"
    			+ "[INFO] Initiating Artefact(s) Selection Operation"
    			+"***********************************************************\n";
    			ResponseTextFrame.setTestInTextArea(message);
    			
		  //Schedule a job for the event-dispatching thread:
				//creating and showing this application's GUI.
			createAndShowGUI(); 
	    }
}