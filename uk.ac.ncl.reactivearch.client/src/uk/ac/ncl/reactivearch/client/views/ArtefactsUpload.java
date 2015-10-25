package uk.ac.ncl.reactivearch.client.views;

import java.io.IOException;
import java.net.URI;
import java.security.KeyStoreException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

public class ArtefactsUpload {

	//private static JSONObject saveJsonObj;
	static ClientResponse response;
	static long requestTime;
	static String server;
	private static String notify;
	
	static final String REST_URI = "http://10.66.66.155:8088/uk.ac.ncl.repository.main";  //10.66.66.155 localhost
	
	public static void setNotify(String str){
		notify = str;
	}
	
	public static String getNotify(){
		return notify;
	}

	public static void main(String[] args) {
		try {
			ResponseTextFrame.setTestInTextArea(Utility.getCurrentDate());
			System.out.println("\n***********************************************************");
	    	System.out.println("[INFO] Initiating Artefact(s) Upload Operation");
	    	System.out.println("***********************************************************\n");
	    	
	    	//Append
	    	String message = "\n***********************************************************"
	    			+ "[INFO] Initiating Artefact(s) Upload Operation"
	    			+"***********************************************************\n";
	    			ResponseTextFrame.setTestInTextArea(message);
	    			
			sendFileJSON();
		} catch (KeyStoreException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void artUpload(){
		try {
			
			System.out.println("\n***********************************************************");
	    	System.out.println("[INFO] Initiating Artefact(s) Upload Operation");
	    	System.out.println("***********************************************************\n");
	    	
	    	//Append
	    	String message = "\n***********************************************************"
	    			+ "[INFO] Initiating Artefact(s) Upload Operation"
	    			+"***********************************************************\n";
	    			ResponseTextFrame.setTestInTextArea(message);
	    			
			sendFileJSON();
		} catch (KeyStoreException | IOException e) {
			e.printStackTrace();
		}
	}
	
private static void sendFileJSON() throws IOException, KeyStoreException{
		
		ClientConfig config = new DefaultClientConfig();		
		Client clientUp = Client.create(config);
		clientUp.addFilter(new LoggingFilter());
		
		WebResource service = clientUp.resource(REST_URI);
								
		//File Chooser JFrame
        FileChooser.show();
        
        
      //Saving files with properties 
        JSONObject saveJsonObj = new JSONObject();
  	        try {
  	        	saveJsonObj.put("filehash", FileChooser.getFileHash());
  	        	saveJsonObj.put("filename", FileChooser.getFileName());
  	        	saveJsonObj.put("file", FileChooser.getFile());
  	        	saveJsonObj.put("projName", TeamCreation.getProjName());
  	        	saveJsonObj.put("DeveloperID", RegisterDeveloper.getDevID());
  			} catch (JSONException e) {
  				e.printStackTrace();
  			}
    
  	      
    //Displays Response from Server on a JFrame
    //ResponseTextFrame.displayTextPane();
   
    	response = service.path("rest").path("ArtefactsService").path("upload").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, saveJsonObj);
    	System.out.println("JSON_ Response as JSON: " + response); //<----CORRECT
    
		//response = service.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, saveJsonObj);
    	ClientMenu.area.append("\n"+RegisterDeveloper.getDevID()+" has uploaded element: ["+FileChooser.getFileName()+"] for project ["+TeamCreation.getProjName()+"]");
    	
		String output = response.getEntity(String.class);
		
		setNotify(output);

		Utility.writeToFile(output);
		
		
        //setDevID(output);
		System.out.println("[Output]: \n"
				+ "Artefact(s) Status: " + output+" ["+ response.getStatus()+"]\n"
				+ "FileHash: "+FileChooser.getFileHash()+"\n"
				+ "FileName: "+FileChooser.getFileName()+"\n"
				+ "File: "+FileChooser.getFile()+"\n"
				+ "Project Name: "+TeamCreation.getProjName()+"\n"
				+ "DeveloperID: " + RegisterDeveloper.getDevID());
		
						
		clientUp.destroy();
	}
		
	@SuppressWarnings("unused")
	private static URI getBaseURI(){
		//String path = "http://10.66.67.45:8080/ProBCloud/rest/verify/run";
		
		final String path = "http://192.168.0.4:8077/uk.ac.ncl.repository.main/rest/ArtefactsService";  //10.66.66.155 localhost
		
		return UriBuilder.fromUri(path).build();
		
	}

}
