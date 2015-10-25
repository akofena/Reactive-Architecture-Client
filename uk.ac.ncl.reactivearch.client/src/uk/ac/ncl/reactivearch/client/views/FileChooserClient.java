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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class FileChooserClient {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private static String fname;
	private static File fis;
	private static JSONObject saveJsonObj;

	private static void createAndShowGUI() {

		// Create and set up the window.
		final JFrame frame = new JFrame("Centered");

		// Display the window.
		frame.setSize(300, 300);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
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
	
	public static JSONObject getJSONObj(){
		if(saveJsonObj.equals(null)){
			System.err.println("JSON Object does not exist!");
		}
		return saveJsonObj;
	}

	private static void createFileChooser(final JFrame frame) {

		String filename = File.separator+"tmp";
		JFileChooser fileChooser = new JFileChooser(new File(filename));

		// pop up an "Open File" file chooser dialog
		fileChooser.showOpenDialog(frame);
		System.out.println("File to open: " + fileChooser.getSelectedFile());
		
		//Developer
		String dev = "Dev0001";
		
		//Filename
		filename = fileChooser.getSelectedFile().getName();
		setFileName(filename);
		
		//Send File to JSON method
		setFile(fileChooser.getSelectedFile());
		
		//Send selected file to Multipart Encoded POST
		/*try {
			ArtefactsServiceClient.postMultiPart(fileChooser.getSelectedFile());} 
		catch (ParseException e1) { e1.printStackTrace();}
		catch (IOException e1) { e1.printStackTrace();}*/
		
		//HashCode
		String filehash = null;	//fileChooser.getSelectedFile().hashCode()+filename.hashCode();
		try {
			filehash = generateBufferedHash(fileChooser.getSelectedFile());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// pop up an "Save File" file chooser dialog
		fileChooser.showSaveDialog(frame);
		System.out.println("File to save: " + fileChooser.getSelectedFile()+"\n");
		
		//Saving files with properties 
		saveJsonObj = new JSONObject();
	        try {
	        	saveJsonObj.put("filehash", filehash);
	        	saveJsonObj.put("filename", getFileName());
	        	saveJsonObj.put("file", getFile());
	        	saveJsonObj.put("fileRawPath", fileChooser.getSelectedFile().toString());
	        	saveJsonObj.put("DeveloperID", dev);
			} catch (JSONException e) {
				e.printStackTrace();
			}
	        
		//MenuOperations menuOp = new MenuOperations();
		//menuOp.saveArtefact(filehash, filename, fileChooser.getSelectedFile(), fileChooser.getSelectedFile().toString(), dev);
		//menuOp.findDocumentProps(filehash, filename, dev);
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
	
	
	public static void runFileChooser() {
		//Schedule a job for the event-dispatching thread:
			//creating and showing this application's GUI.
		createAndShowGUI(); 
 
    }
	
	public static void main(String[] args) {
		  //Schedule a job for the event-dispatching thread:
				//creating and showing this application's GUI.
			createAndShowGUI(); 
	    }
}