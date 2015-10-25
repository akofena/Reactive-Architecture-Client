package uk.ac.ncl.reactivearch.client.views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
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


public class TeamCreation {

	private static String devID;
	private static String projName;
	private static String permit;
	private static JLabel idLabel, teamLeader, tl;
	private static JLabel notice;
	private final static Dimension PANEL_SIZE = new Dimension(600,500);
	
	private static JButton teamButton;
	private static JMenuItem useIDInfo;
	private static JLabel proj, developer, permission;
	private static JTextField tffn, tfsn;
	
	
	@SuppressWarnings("rawtypes")
	private static JComboBox comboBox;
	
	private static Map<String,String> map, projMap;
	
	public static ArrayList<String> list;
	public static int projMapSize;
	
	static final String REST_URI = "http://10.66.66.155:8088/uk.ac.ncl.repository.main";  //10.66.66.155 localhost

    public static ClientResponse response;
    
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
		
	private static void setPermission(String perm){
		if(perm.equals("")) {permit = "[Default]";}
		else{permit = perm;}
	}
	
	private static void setDevID(String dev){
		if(dev.equals("")) {devID = "Null ID";}
		else{devID = dev;}
	}
	
	private static void setProjectMap(){
		projMap = new HashMap<String, String>(); //LinkedHashMap provides ordering and iteration
		
		projMap.put(getDevID(), getProjName());
		
		if(projMap.isEmpty()){
			projMap.put(getDevID(), getProjName());
		}
	}
	
	//Getters
	public static String getDevID(){
		return devID;
	}
	
	public static String getProjName(){
		//System.out.println("FN: "+fName);
		return projName;
	}
	
	public static String getPermission(){
		//System.out.println("SN: "+sName);
		return permit;
	}
	
	
	public static String toStringMap(){
		return getProjName()+" "+getPermission();
	}
	
		
	public static String getProjNameMap(){
		setProjectMap();
		
		String pname = null;
		
		//retrieving values from map
	    Set<String> keys= projMap.keySet();
	    for(String key : keys){
	    	//System.out.println(key);
	    	pname = projMap.get(key);
	        System.out.println(key + ": " + projMap.get(key));
	    }
	    
	    //searching key on map
	    //System.out.println("Map Value: "+map.containsKey(toStringMap()));
	    //System.out.println("Map Value: "+projMap.get(getProjName()));
	    
	    // Put keys into an ArrayList and sort it.
		list = new ArrayList<String>();
		list.addAll(keys);
		Collections.sort(list);
	
		// Display sorted keys and their values.
		for (String key : list) {
			pname = projMap.get(key);
		    System.out.println(key + ": " + projMap.get(key));
		    
		}		
		
		projMapSize = projMap.size();
	    
		return pname;
	}
	
	public static void getMapDetails(){
		//adding values to map
		map=new HashMap<String,String>();
	    map.put(getProjName(), getDevID());
	    //System.out.println("\n[TEST Teamwork]: "+ map.size());
	    
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
	    
	    //searching key on map
	    //System.out.println("Map Value: "+map.containsKey(toStringMap()));
	    System.out.println("Map Value: "+map.get(getProjName()));
	    
	    //searching value on map
	    //System.out.println("Map Key: "+map.containsValue(getDevID()));
	    
					    // Put keys into an ArrayList and sort it.
						//ArrayList<String> list = new ArrayList<String>();
						//list.addAll(keys);
						//Collections.sort(list);
				
						// Display sorted keys and their values.
						//for (String key : list) {
						//    System.out.println(key + ": " + map.get(key));
						//}			    
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void showFrame() {
		JPanel panel = new JPanel(new GridLayout(6,2));
	    
	    
        panel.setOpaque(true);
 
        final JFrame frame = new JFrame("Team Creation");
        frame.setPreferredSize(PANEL_SIZE);
        
     // Just create menubar
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // Add an JMenu
        JMenu file = new JMenu("File");
        menubar.add(file);
        
        // Add an JMenuItem
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new exitApp());
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
        tl = new JLabel("Team Leader:");
        
       	teamLeader = new JLabel("Choose Team Leader");
       
        proj = new JLabel("Project Name:");
        developer = new JLabel("Invite Developer: (use Developer ID)");
        permission = new JLabel("Set Developer Permission:");
       
        tffn = new JTextField();
        tfsn = new JTextField();

        //setbounds
        proj.setBounds(100, 30, 400, 30);
        tffn.setBounds(300, 70, 200, 30);
        developer.setBounds(80, 70, 200, 30);
        tfsn.setBounds(300, 110, 200, 30);
        permission.setBounds(80, 110, 200, 30);       
        //p1.setBounds(300, 150, 200, 30);         

        //add
        panel.add(tl);
        panel.add(teamLeader);
        panel.add(proj);
        panel.add(tffn);
        panel.add(developer);        
        panel.add(tfsn);
        panel.add(permission);
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Full");
        model.addElement("View");
        model.addElement("None");
        comboBox = new JComboBox(model);
        panel.add(comboBox);
        //panel.add(p1);
        
        
      //Show the New ID of Registered Developer
        idLabel = new JLabel();
        panel.add(idLabel);
        
        //Notice        
        notice = new JLabel();
        panel.add(notice);
        
        //Add Register Button
        teamButton= new JButton("Create Team");
        teamButton.addActionListener(new teamWork());
        teamButton.setBounds(50, 350, 100, 30);
        panel.add(teamButton);
        
        
        // Add an Exit JButton
        JButton exitButton= new JButton("Exit");
        //exitButton.setBounds(70, 350, 100, 30);
        panel.add(exitButton);
        exitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();						
				}});
        //exitButton.setSize(40,40);
        
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
    
 // User ID Information
    static class userID implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	if (e.getSource() == useIDInfo) {
        		//JOptionPane.showConfirmDialog(null, "Your ID is "+ RegisterDeveloper.getDevID(), "Teamwork", JOptionPane.PLAIN_MESSAGE);
        	}
        }
    }
    
 
 // Register Developer
    static class teamWork implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	if (e.getSource() == teamButton) {
        		//get Info
                String s1 = tffn.getText();
                //System.out.println("[TEST] "+s1);
                setProjName(s1);
                
                String s2 = tfsn.getText();
                //System.out.println("[TEST] "+s2);
                setDevID(s2);
                
                String s3 = (String) comboBox.getSelectedItem();
                setPermission(s3);
                
                teamLeader.setText(RegisterDeveloper.getDevID());
                
            	idLabel.setText("TEAM STATUS: ");
            	
            	
            	ClientConfig config = new DefaultClientConfig();  
                Client clientReg = Client.create(config);  
                WebResource service = clientReg.resource(REST_URI);  
          
                clientReg.addFilter(new LoggingFilter());
                
                
                JSONObject inputJsonObj = new JSONObject();
                try {
                	inputJsonObj.put("pName", getProjName());
                	inputJsonObj.put("devID", getDevID());
        			inputJsonObj.put("permission", getPermission());
        			
        		} catch (JSONException je) {
        			je.printStackTrace();
        		}
                
                //JSON_SEND MULTI-PART FORM DATA
                //ResponseTextFrame.setTestInTextArea("JSON_SEND MULTI-PART FORM DATA:");
                response = service.path("rest").path("ArtefactsService").path("teamwork").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, inputJsonObj);
                System.out.println("JSON_ Response as JSON: " + response); //<----CORRECT
                
                ClientMenu.area.append("\n"+getDevID()+" is/are added to: ["+getProjName()+"]");
            	notice.setText("\n"+getDevID()+" is/are added to: ["+getProjName()+"]");
            	//System.out.println("Welcome "+getFirstName()+"! \nYour ID is: "+getDevID());
            	
            	//JOptionPane.showConfirmDialog(null, "Developer ["+getDevID()+"] has been added: ["+getProjName()+"] \nPermission: "+getPermission(), "Registered", JOptionPane.PLAIN_MESSAGE);
            	
            	 
            	
            	System.out.println("---------------------------------------------------\n");
                 
                 String output = response.getEntity(String.class);
                 setDevID(output);
         		System.out.println("[Output]: \n"
         				+ "Project Name: "+getProjName()+"\n"
         				+ "Developer ID: " + getDevID()+"\n"
         				+ "Developer Permission: " + getPermission());
         		
         		System.out.println("Status of File Transfer: " + response.getStatus());
         		
            		
            	clientReg.destroy();
        	}
        	
        }
    }
    
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//ResponseTextFrame.setTestInTextArea(Utility.getCurrentDate());
            	System.out.println("\n***********************************************************");
            	System.out.println("[INFO] Initiating Team Creation Operation");
            	System.out.println("***********************************************************\n");
            	
            	//Append
            	String message = "\n***********************************************************"
            			+ "[INFO] Initiating Team Creation Operation"
            			+"***********************************************************\n";
            			ResponseTextFrame.setTestInTextArea(message);
            	
            	TeamCreation.showFrame();
            	
            }
        });
    }
    
    public static void display() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//ResponseTextFrame.setTestInTextArea(Utility.getCurrentDate());
            	System.out.println("\n***********************************************************");
            	System.out.println("[INFO] Initiating Team Creation Operation");
            	System.out.println("***********************************************************\n");
            	
            	//Append
            	String message = "\n***********************************************************"
            			+ "[INFO] Initiating Team Creation Operation"
            			+"***********************************************************\n";
            			ResponseTextFrame.setTestInTextArea(message);
            	
            	TeamCreation.showFrame();
            	
            }
        });
    }

}