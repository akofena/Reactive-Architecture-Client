package uk.ac.ncl.reactivearch.client.views;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
  

public class ArtefactsServiceClient {  
    static final String REST_URI = "http://10.66.66.155:8088/uk.ac.ncl.repository.main";  //10.66.66.155 localhost
    static final String INCH_TO_FEET = "/ArtefactsService/InchToFeet/";  
    static final String FEET_TO_INCH = "/ArtefactsService/FeetToInch/";  
    static final String JSON_KEY_VALUE = "/ArtefactsService/KeyValue/";  
    static final String COUNT_XML = "/ArtefactsService/CountArtefacts/";  
    static final String COUNT_ARTEFACTS = "/ArtefactsService/CountArtefacts/";
    
    public static ClientResponse response;
    
    public String getValue(String val){
    	
    	return val;
    }
    
    //TEST
    public static void main(String[] args) throws UnsupportedEncodingException {
    	runClient();
    	
    	
    }
    
    public static void runClient() throws UnsupportedEncodingException { 
       // int inch=12;  
       // int feet=2;  
  
        ClientConfig config = new DefaultClientConfig();  
        Client client = Client.create(config);  
        WebResource service = client.resource(REST_URI);  
  
        client.addFilter(new LoggingFilter());
        
        //File Chooser JFrame
        System.out.println("---------------------------------------------------");
        System.out.println("[INFO] Initiating Artefact(s) Upload...\n");
        FileChooserClient.runFileChooser();
        //System.out.println("---------------------------------------------------\n"); 
      
        
        JSONObject inputJsonObj = new JSONObject();
        try {
        	inputJsonObj.put("filehash", FileChooserClient.getFileName());
        	inputJsonObj.put("filename", FileChooserClient.getFileName());
			inputJsonObj.put("file", FileChooserClient.getFile());
			inputJsonObj.put("filename", FileChooserClient.getFileName());
			inputJsonObj.put("file", FileChooserClient.getFile());
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        
        //Displays Response from Server on a JFrame
        //ResponseTextFrame.displayTextPane();
        
        //TEST for different file formats and operations

		        //JSON_SEND MULTI-PART FORM DATA
		        //ResponseTextFrame.setTestInTextArea("JSON_SEND MULTI-PART FORM DATA:");
		        //response = service.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, FileChooserClient.getJSONObj());
		        response = service.path("rest").path("ArtefactsService").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, FileChooserClient.getJSONObj());
		        System.out.println("JSON_ Response as JSON: " + response); //<----CORRECT
		        //ResponseTextFrame.setTestInTextArea(response.getEntity(String.class));
		        System.out.println("---------------------------------------------------\n");
		        
		        String output = response.getEntity(String.class);
				System.out.println("Output: \n" + output);
    } 
    
    @SuppressWarnings("unused")
	private static String getResponse(WebResource service) { 
    			
        return service.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();  
    }  
  
    @SuppressWarnings("unused")
	private static String getOutputAsXML(WebResource service) { 
        return service.accept(MediaType.TEXT_XML).get(String.class);  
    } 
    
    
	@SuppressWarnings("unused")
	private static JSONObject postResponseJSON(WebResource service){
    	/*String response = service.accept(MediaType.APPLICATION_JSON).post(JSONObject.class).toString();
    	
    	if (response.getStatus() != 200) {	
    		throw new RuntimeException("Failed : HTTP error code : "
    			+ response.getStatus());
    	}*/
			return service.accept(MediaType.APPLICATION_JSON).post(JSONObject.class);
    }
    
    @SuppressWarnings("unused")
	private static String postOutputAsJSON(WebResource service) {    
        return service.accept("application/json").post(String.class);  
    }  
}  
