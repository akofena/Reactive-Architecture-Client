package uk.ac.ncl.reactivearch.client.views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

public class RegisterDeveloper {
	
	private static String devID;
	private static String fName;
	private static String sName;
	private static String pw;
	private static JLabel idLabel;
	private static JLabel notice;
	private final static Dimension PANEL_SIZE = new Dimension(600,500);
	
	private static JButton regButton;
	private static JMenuItem useIDInfo;
	private static JLabel fn, sn, pass;
	private static JTextField tffn, tfsn;
	private static JPasswordField p1;
	
	private static Map<String,String> map;
	
	static final String REST_URI = "http://10.66.66.155:8088/uk.ac.ncl.repository.main";  //10.66.66.155 localhost Library: 10.54.2.248

	private static ClientResponse response;
    
	//Constructor
	/*public RegisterDeveloper(String fname, String surname){
		fName = fname;
		sName = surname;
	}*/
	
	//Setter
	private static void setFirstName(String fname){
		if(fname.equals("")) {fName = "[Default]";}
		else{fName = fname;}
		
	}
		
	private static void setPass(char[] pass){
		String str = String.valueOf(pass);
		if(str.equals("")) {pw = "[Default]";}
		else{pw = str;}
	}
	private static void setSurname(String surname){
		if(surname.equals("")) {sName = "[Default]";}
		else{sName = surname;}
	}
		
	private static void setDevID(String dev){
		if(dev.equals("")) {devID = "[Default]";}
		else{devID = dev;}
	}
	
	//Getters
	public static String getDevID(){
		return devID;
	}
	
	public static String getFirstName(){
		//System.out.println("FN: "+fName);
		return fName;
	}
	
	public static String getSurname(){
		//System.out.println("SN: "+sName);
		return sName;
	}
	
	public static String getDevPass(){
		return pw;
	}
	
	public static String toStringMap(){
		return getFirstName()+" "+getSurname();
	}
	
	//Developer ID [Performed in the Server]
	@SuppressWarnings("unused")
	private static String computeDevID(){
			String uniqueID = UUID.randomUUID().toString();
			
			String[] parts = uniqueID.split("-");
			String part1 = parts[0];
			String part2 = parts[1]; 
			
			String dev = "DEV"+part1+part2;
			setDevID(dev);
			
					    
			return dev;
	}
	
	public static void getMapDetails(){
		//adding values to map
		map=new HashMap<String,String>();
	    map.put(toStringMap(), getDevID());
	    System.out.println("\n[TEST Register]: "+ map.size());
	    
	    // The HashMap is currently empty.
		if (map.isEmpty()) {
		    System.out.println("It is empty");
		    
		    System.out.println("Trying Again:");
		    map=new HashMap<String,String>();
		    map.put(toStringMap(), getDevID());
		    
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
	    System.out.println("Map Value: "+map.get(toStringMap()));
	    
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
	
	public static void showFrame() {
		JPanel panel = new JPanel(new GridLayout(5,2));
	    
	    
        panel.setOpaque(true);
 
        final JFrame frame = new JFrame("Register Developer");
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
        
        
        //Registration Form 
        
       
        //initialise
        fn = new JLabel("First Name:");
        sn = new JLabel("Surname:");
        pass = new JLabel("Create Password:");
       
        tffn = new JTextField();
        tfsn = new JTextField();

        p1 = new JPasswordField();

        //setbounds
        fn.setBounds(100, 30, 400, 30);
        tffn.setBounds(300, 70, 200, 30);
        sn.setBounds(80, 70, 200, 30);
        tfsn.setBounds(300, 110, 200, 30);
        pass.setBounds(80, 110, 200, 30);       
        p1.setBounds(300, 150, 200, 30);         

        //add
        panel.add(fn);
        panel.add(tffn);
        panel.add(sn);        
        panel.add(tfsn);
        panel.add(pass);
        panel.add(p1);
        
        
      //Show the New ID of Registered Developer
        idLabel = new JLabel();
        panel.add(idLabel);
        
        //Notice        
        notice = new JLabel();
        panel.add(notice);
        
        //Add Register Button
        regButton= new JButton("Register");
        regButton.addActionListener(new registerDev());
        regButton.setBounds(50, 350, 100, 30);
        panel.add(regButton);
        
        
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
        		JOptionPane.showConfirmDialog(null, "Your ID is "+ getDevID(), "Registered", JOptionPane.PLAIN_MESSAGE);
        	}
        }
    }
    
 
 // Register Developer
    static class registerDev implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	if (e.getSource() == regButton) {
        		//get Info
                String s1 = tffn.getText();
                //System.out.println("[TEST] "+s1);
                setFirstName(s1);
                
                String s2 = tfsn.getText();
                //System.out.println("[TEST] "+s2);
                setSurname(s2);
                
                char[] s3 = p1.getPassword();
                setPass(s3);
                
                try {
					runClient();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
               
            	idLabel.setText("REGISTRATION STATUS: ");
            	ClientMenu.area.append("\n"+"Welcome "+getFirstName()+"! Your ID is: ["+getDevID()+"]");
            	notice.setText("\nWelcome "+getFirstName()+"! Your ID is: ["+getDevID()+"]");
            	
            	//System.out.println("Welcome "+getFirstName()+"! \nYour ID is: "+getDevID());
            	
            	JOptionPane.showConfirmDialog(null, "Welcome "+getFirstName()+", "+getSurname()+"!"+" \nYour ID is "+ getDevID(), "Registered", JOptionPane.PLAIN_MESSAGE);
            	
            		
            	//getMapDetails();
        	}
        	
        }
    }
    
    public static void runClient() throws UnsupportedEncodingException { 
	       
        ClientConfig config = new DefaultClientConfig();  
        Client clientReg = Client.create(config);  
        WebResource service = clientReg.resource(REST_URI);  
  
        clientReg.addFilter(new LoggingFilter());
        
        
        JSONObject inputJsonObj = new JSONObject();
        try {
        	inputJsonObj.put("fname", getFirstName());
        	inputJsonObj.put("sname", getSurname());
			inputJsonObj.put("password", getDevPass());
			//inputJsonObj.put("filename", FileChooserClient.getFileName());
			//inputJsonObj.put("file", FileChooserClient.getFile());
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        //JSON_SEND MULTI-PART FORM DATA
        //ResponseTextFrame.setTestInTextArea("JSON_SEND MULTI-PART FORM DATA:");
        response = service.path("rest").path("ArtefactsService").path("register").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, inputJsonObj);
        System.out.println("JSON_ Response as JSON: " + response); //<----CORRECT
        
        
        String output = response.getEntity(String.class);
        setDevID(output);
		System.out.println("Output: \n"
				+ "Username: "+getFirstName()+" "+getSurname()+"\n"
				+ "Developer ID: " + output);
		
		//Append information of ResponseTextFrame
		String message = "Output: \n"
				+ "Username: "+getFirstName()+" "+getSurname()+"\n"
				+ "Developer ID: " + output;
    			ResponseTextFrame.setTestInTextArea(message);
		
		System.out.println("Status of File Transfer: " + response.getStatus());
		
		clientReg.destroy();
}
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	ResponseTextFrame.setTestInTextArea(Utility.getCurrentDate());
            	System.out.println("\n***********************************************************");
            	System.out.println("[INFO] Initiating Developer Registration Operation");
            	System.out.println("***********************************************************\n");
            	
            	//Append
            	String message = "\n***********************************************************"
            			+ "[INFO] Initiating Developer Registration Operation"
            			+"***********************************************************\n";
            			ResponseTextFrame.setTestInTextArea(message);
            	
            	RegisterDeveloper.showFrame();
            }
        });
    }
    
    public static void display() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	ResponseTextFrame.setTestInTextArea(Utility.getCurrentDate());
            	System.out.println("\n***********************************************************");
            	System.out.println("[INFO] Initiating Developer Registration Operation");
            	System.out.println("***********************************************************\n");
            	
            	//Append
            	String message = "\n***********************************************************"
            			+ "[INFO] Initiating Developer Registration Operation"
            			+"***********************************************************\n";
            			ResponseTextFrame.setTestInTextArea(message);
            	RegisterDeveloper.showFrame();
            	
            }
        });
    }

}