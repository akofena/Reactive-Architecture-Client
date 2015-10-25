package uk.ac.ncl.reactivearch.client.views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;


public class ArtefactsDownload {

	
	private static String devID;
	private static String projName, artName;
	private static String permit;
	private static JLabel idLabel, tl, ldevid, ldevperm;
	private static JLabel notice;
	private final static Dimension PANEL_SIZE = new Dimension(600,500);
	
	private static JButton downButton;
	private static JMenuItem useIDInfo;
	private static JLabel proj, developer, permission;
		
	
	@SuppressWarnings("rawtypes")
	private static JComboBox comboBox, artComboBox;
	
	private static Map<String,String> map;
	

	static final String REST_URI = "http://10.66.66.155:8088/uk.ac.ncl.repository.main";  //10.66.66.155 localhost
	static ClientResponse response;
    
	//Constructor
	/*public RegisterDeveloper(String fname, String surname){
		fName = fname;
		sName = surname;
	}*/
	
	//Setter
	private static void setProjName(String projN){
		if(projN.equals("")) {projName = "[Default]";}
		else{projName = projN;}
		
	}
	
	private static void setArtName(String artN){
		if(artN.equals("")) {artName = "[DefaultArtefactName]";}
		else{artName = artN;}
		
	}
		
	private static void setPermission(String perm){
		if(perm.equals("")) {permit = "[Default]";}
		else{permit = perm;}
	}
	
	private static void setDevID(String dev){
		if(dev.equals("")) {devID = "Null ID";}
		else{devID = dev;}
	}
	
	//Getters
	public static String getDevID(){
		return devID;
	}
	
	public static String getProjName(){
		return projName;
	}
	
	public static String getArtName(){
		return artName;
	}
	
	public static String getPermission(){
		return permit;
	}
	
	
	public static String toStringMap(){
		return getProjName()+" "+getPermission();
	}
	
	
	public static void getMapDetails(){
		//adding values to map
		map=new HashMap<String,String>();
	    map.put(getProjName(), getDevID());
	    System.out.println("\n[TEST Download]: "+ map.size());
	    
	    // The HashMap is currently empty.
		if (map.isEmpty()) {
		    System.out.println("It is empty");
		    
		    System.out.println("Trying Again:");
		    map=new HashMap<String,String>();
		    map.put(getProjName(), getDevID());
		    
		    System.out.println(map.isEmpty());
		}
		            
		
		//retrieving values from map
	    Set<String> keys= map.keySet();
	    for(String key : keys){
	    	//System.out.println(key);
	        System.out.println(key + ": " + map.get(key));
	    }
	    
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void showFrame() {
		JPanel panel = new JPanel(new GridLayout(6,2));
	    
	    
        panel.setOpaque(true);
 
        final JFrame frame = new JFrame("Download Artefact");
        frame.setPreferredSize(PANEL_SIZE);
        
     // Just create menubar
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // Add an JMenu
        JMenu file = new JMenu("File");
        menubar.add(file);
        
        // Add an JMenuItem
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();						
				}});
        file.add(exit);

        // Add an JMenu
        JMenu help = new JMenu("Help");
        menubar.add(help);
        // Add an JMenuItem
        JMenuItem about = new JMenuItem("About");
        help.add(about);
        
        // Add an JMenu
        JMenu useID = new JMenu("Developer ID");
        menubar.add(useID);
        // Add an JMenuItem
        useIDInfo = new JMenuItem("Click for UserID");
        useIDInfo.addActionListener(new userID());
        useID.add(useIDInfo);
        
        
        //Teamwork Form 
            
        //initialise
        tl = new JLabel("Select Saved Artefact(s):");
        
       	//teamLeader = new JLabel("Choose Team Leader");
       
        proj = new JLabel("Project Name:");
        developer = new JLabel("Developer ID");
        permission = new JLabel("Show Developer Permission:");
       
       
        ldevid = new JLabel("Developer ID");
        ldevid.setText(RegisterDeveloper.getDevID());
        ldevperm = new JLabel("Developer Permission");
        ldevperm.setText(TeamCreation.getPermission());
        
        //setbounds
        proj.setBounds(100, 30, 400, 30);
        ldevid.setBounds(300, 70, 200, 30);
        developer.setBounds(80, 70, 200, 30);
        ldevperm.setBounds(300, 110, 200, 30);
        permission.setBounds(80, 110, 200, 30);       
        //p1.setBounds(300, 150, 200, 30);         

        DefaultComboBoxModel artCMB = new DefaultComboBoxModel();
        
        for(int i = 1; i == 1; i++){ //TeamCreation.projMapSize
        	artCMB.addElement(FileChooser.getFileName());
          }
        
       
        artCMB.addElement("Artefacts");
        artComboBox = new JComboBox(artCMB);
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        for(int i = 1; i == 1; i++){ //TeamCreation.projMapSize
            model.addElement(TeamCreation.getProjNameMap());
          }
                
        model.addElement("AirLock");
        model.addElement("Other...");
        comboBox = new JComboBox(model);
        
        
        
        //add
        panel.add(tl);
        panel.add(artComboBox);
        
        panel.add(proj);
        panel.add(comboBox);
        panel.add(developer);
        panel.add(ldevid);
        panel.add(permission);
        panel.add(ldevperm);
        
      //Show the New ID of Registered Developer
        idLabel = new JLabel();
        panel.add(idLabel);
        
        //Notice        
        notice = new JLabel();
        panel.add(notice);
        
        //Add Register Button
        downButton= new JButton("Download");
        downButton.addActionListener(new downloadArt());
        downButton.setBounds(50, 350, 100, 30);
        panel.add(downButton);
        
        
        // Add an Exit JButton
        JButton exitButton= new JButton("Exit");
        panel.add(exitButton);
        exitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();						
				}});
        
        frame.setContentPane(panel);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dimension.width/2-frame.getSize().width/2, dimension.height/2-frame.getSize().height/2);
               
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
 // Exit
    static class exitApp implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	System.exit(0);
        }
    }
    
 // User ID Information [from 'Developer Info' Menu]
    static class userID implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	if (e.getSource() == useIDInfo) {
        		JOptionPane.showConfirmDialog(null, "Your ID is "+ RegisterDeveloper.getDevID(), "Download", JOptionPane.PLAIN_MESSAGE);
        	}
        }
    }
    
 
 //  [TO DO] Download Artefact(s) of Project
    static class downloadArt implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	ClientConfig config = new DefaultClientConfig();		
    		Client clientUp = Client.create(config);
    		clientUp.addFilter(new LoggingFilter());
    		
    		WebResource service = clientUp.resource(REST_URI);
    		
        	if (e.getSource() == downButton) {
        		//System.out.println("\n[INFO] Initiating Artefact(s) Download...\n");
        		//get Info
                String s1 = ldevid.getText();
                //System.out.println("[TEST] "+s1);
                setDevID(s1);
                
                String s2 = ldevperm.getText();
                //System.out.println("[TEST] "+s2);
                setPermission(s2);
                
                String sArt = (String) artComboBox.getSelectedItem();//Load all artefacts of the Developer 
                
                setArtName(sArt);
                
                String s3 = (String) comboBox.getSelectedItem();//Load all projects of the Developer
                
                setProjName(s3);
                
              //Saving files with properties 
                JSONObject saveJsonObj = new JSONObject();
          	        try {          	        	
          	        	saveJsonObj.put("fileperm", s2);
          	        	saveJsonObj.put("filename", FileChooser.getFileName());
          	        	saveJsonObj.put("projName", s3);
          	        	saveJsonObj.put("devID", s1);
          			} catch (JSONException jex) {
          				jex.printStackTrace();
          			}
            
                
                //teamLeader.setText(RegisterDeveloper.getDevID());
                
            	idLabel.setText("DOWNLOAD STATUS: ");
            	
            	notice.setText("\n"+getDevID()+" has downloaded: ["+FileChooser.getFileName()+"] of project "+getProjName()+"]");
            	ClientMenu.area.append("\n"+getDevID()+" has downloaded: ["+FileChooser.getFileName()+"] of project "+getProjName()+"]");
            	//System.out.println("Welcome "+getFirstName()+"! \nYour ID is: "+getDevID());
            	
            	//JOptionPane.showConfirmDialog(null, "Developer ["+getDevID()+"] has downloaded: ["+getProjName()+"] \nPermission: "+TeamCreation.getPermission(), "Download", JOptionPane.PLAIN_MESSAGE);
            	
              
          	      
            //Displays Response from Server on a JFrame
            //ResponseTextFrame.displayTextPane();
           
            	response = service.path("rest").path("ArtefactsService").path("download").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, saveJsonObj);
            	System.out.println("JSON_ Response as JSON: " + response); //<----CORRECT
            
        		//response = service.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, saveJsonObj);
        		
        		String output = response.getEntity(String.class);
        		
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
        	
        }
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	ResponseTextFrame.setTestInTextArea(Utility.getCurrentDate());
            	System.out.println("\n***********************************************************");
            	System.out.println("[INFO] Initiating Artefact(s) Download Operation");
            	System.out.println("***********************************************************\n");
            	
            	//Append
            	String message = "\n***********************************************************"
            			+ "[INFO] Initiating Artefact(s) Download Operation"
            			+"***********************************************************\n";
            			ResponseTextFrame.setTestInTextArea(message);
            			
            	ArtefactsDownload.showFrame();      
            	
            }
        });
    }
    
    public static void display() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	ResponseTextFrame.setTestInTextArea(Utility.getCurrentDate());
            	System.out.println("\n***********************************************************");
            	System.out.println("[INFO] Initiating Artefact(s) Download Operation");
            	System.out.println("***********************************************************\n");
            	
            	//Append
            	String message = "\n***********************************************************"
            			+ "[INFO] Initiating Artefact(s) Download Operation"
            			+"***********************************************************\n";
            			ResponseTextFrame.setTestInTextArea(message);
            			
            	ArtefactsDownload.showFrame();          	
            }
        });
    }

}