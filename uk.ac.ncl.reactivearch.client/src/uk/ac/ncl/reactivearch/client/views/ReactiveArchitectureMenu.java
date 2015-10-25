package uk.ac.ncl.reactivearch.client.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import uk.ac.ncl.reactivearch.client.notify.notifier.NotificationType;
import uk.ac.ncl.reactivearch.client.notify.notifier.NotifierDialog;

public class ReactiveArchitectureMenu {

	private final int X = 100; 
	private final int Y = 100;
	private final Dimension PANEL_SIZE = new Dimension(400,400);
	
	private JPanel textPanel;		
	private JPanel buttonPanel;
	private JButton button;
	private final Font font = new Font("Arial", Font.PLAIN, 22);
	
	public static JFrame mainFrame;
	public static String message;
	
	public static void main(String[] args) {
		new ReactiveArchitectureMenu();
	}
	
	
	public ReactiveArchitectureMenu(){
		

		final JTextArea area = new JTextArea();
		Font font = new Font("Courier", Font.PLAIN, 12);
		
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setEditable(false);
		area.setFont(font);
		area.setPreferredSize(new Dimension(440,380));
		
		
	    mainFrame = new JFrame();

	    // make sure the program exits when the frame close
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setTitle("Reactive Architecture Menu");
	    mainFrame.setLocation(X,Y);
	    mainFrame.setPreferredSize(PANEL_SIZE);

	    // ensure an elastic layout
	    mainFrame.setLayout(new GridLayout(6, 2));

	    mainFrame.setLocationByPlatform(true);

	    mainFrame.add(getButtonPanel("Register"));
	    mainFrame.add(getButtonPanel("Teamwork"));
	    mainFrame.add(getButtonPanel("Upload Artefact"));
	    mainFrame.add(getButtonPanel("Download Artefact"));
	    mainFrame.add(getButtonPanel("Notification"));
	    
	    textPanel = new JPanel(new GridLayout(1,2));
	    textPanel.add(getButtonPanel("Exit"));
	    textPanel.add(getButtonPanel("Notify_Log"));
	   
	    mainFrame.add(textPanel);
	    
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    mainFrame.setLocation((dimension.width  - mainFrame.getSize().width) / 2, (dimension.height - mainFrame.getSize().height) / 2);
        
	    mainFrame.pack();
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setResizable(false);
	    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    mainFrame.setVisible(true);
	}

	
	public JPanel getButtonPanel(String label){
	
		 buttonPanel = new JPanel();
		    button = new JButton(label);
		    
		    if(label.equalsIgnoreCase("Register")){
		    	button.addActionListener(new registerDev());
		    	button.setSize(60,60);
		    }
		    else if(label.equalsIgnoreCase("Teamwork")){
		    	button.addActionListener(new teamworkDev());
		    	button.setSize(60,60);
		    }
		    else if(label.equalsIgnoreCase("Upload Artefact")){
		    	button.addActionListener(new uploadArt());
		    	button.setSize(60,60);
		    }
		    else if(label.equalsIgnoreCase("Download Artefact")){
		    	button.addActionListener(new downloadArt());
		    	button.setSize(60,60);
		    }
		    else if(label.equalsIgnoreCase("Notification")){
		    	button.addActionListener(new notifyDeveloper());
		    	button.setSize(60,60);
		    }	    
		    else if(label.equalsIgnoreCase("Exit")){
		    	button.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						mainFrame.dispose();						
						}});
		    	button.setSize(40,40);
		    }
		    else if(label.equalsIgnoreCase("Notify_Log")){
		    	button.addActionListener(new notifyLog());
		    	button.setSize(40,40);
		    }

		    button.setFont(font);

		    buttonPanel.add(button);

	    return buttonPanel;

	}

	
	//**************************
	//OPERATIONS
	//**************************
	
	// Register
    static class registerDev implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	//ResponseTextFrame.textArea.append("\n[INFO] Initiating Developer Registration...\n");
        	RegisterDeveloper.display();
        	
        	//JOptionPane.showConfirmDialog(null, "Developer is Registered!", "Information", JOptionPane.PLAIN_MESSAGE);
        	//try {
			//	runClient();
			//} catch (UnsupportedEncodingException e1) {
			//	e1.printStackTrace();
			//}
        	
        	//Displays Response from Server on a JFrame
            //ResponseTextFrame.displayTextPane();
        }
        
    }
    
 // Teamwork
    static class teamworkDev implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	//ResponseTextFrame.textArea.append("\n[INFO] Project Team Creation...\n");
        	TeamCreation.display();
        	
        	//Displays Response from Server on a JFrame
            //ResponseTextFrame.displayTextPane();
        }
    }
    
 // Upload Artefacts
    static class uploadArt implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	//ResponseTextFrame.textArea.append("\n[INFO] Project Artefact(s) Upload...\n");
        	//ArtefactsUpload.artUpload();
        	ArtefactsUpload.main(null);
        	JOptionPane.showConfirmDialog(null, "Artefact(s) is/are Uploaded!", "Information", JOptionPane.PLAIN_MESSAGE);
        	
        	//Displays Response from Server on a JFrame
            //ResponseTextFrame.displayTextPane();
        }
        
    }
    
 // Download Artefacts
    static class downloadArt implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	//ResponseTextFrame.textArea.append("\n[INFO] Project Artefact(s) Download...\n");
        	ArtefactsDownload.display();
        	JOptionPane.showConfirmDialog(null, "Artefact(s) is/are Downloaded!", "Information", JOptionPane.PLAIN_MESSAGE);
        	
        	//Displays Response from Server on a JFrame
            //ResponseTextFrame.displayTextPane();
        }
    }
    
   //Notification
    static class notifyDeveloper implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		String mess = "Developer ["+RegisterDeveloper.getDevID()+"] has changed artefact(s) [art123456] for Project ["+TeamCreation.getProjName()+"]!";
    		ReactiveArchitectureMenu.notify(mess);
    	}
    }
	
	// Exit
    static class exitFrame implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	//ResponseTextFrame.textArea.append("\n[INFO] Exiting Reactive Architecture Client...\n");
        	//exitButton
        	mainFrame.dispose();
        	System.exit(0);
        }
    }
    
 // Notification Log
    static class notifyLog implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	ResponseTextFrame.displayTextPane();
        	
        	//JOptionPane.showConfirmDialog(null, "Cleared!", "Information", JOptionPane.PLAIN_MESSAGE);
        	
        	//Displays Response from Server on a JFrame
           // ResponseTextFrame.displayTextPane();
        }
    }
    
    //Notification Pop-up
    public static void notify(String mess) {
    	    	
        message = mess;
        
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Notify!");
        shell.setSize(400, 100);
        shell.setLocation(760, 200);
        shell.setLayout(new FillLayout());
      
        Button tester = new Button(shell, SWT.PUSH);
        tester.setText("Check Notification!");
        tester.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                int max = NotificationType.values().length;
                Random r = new Random();
                int toUse = r.nextInt(max);
                
                
                NotifierDialog.notify("Hi [Developer], notification widget!", message, NotificationType.values()[toUse]);
            }
            
        });
        shell.open();        
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();

    }
    
  //**************************
   /* mainFrame = new JFrame();

    // make sure the program exits when the frame close
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setTitle("Reactive Architecture Menu");
    mainFrame.setLocation(X,Y);
    mainFrame.setPreferredSize(PANEL_SIZE);

    // ensure an elastic layout
    mainFrame.setLayout(new GridLayout(6, 2));

    mainFrame.setLocationByPlatform(true);

    mainFrame.add(getButtonPanel("Register"));
    mainFrame.add(getButtonPanel("Teamwork"));
    mainFrame.add(getButtonPanel("Upload Artefact"));
    mainFrame.add(getButtonPanel("Download Artefact"));
    mainFrame.add(getButtonPanel("Notification"));
    
    textPanel = new JPanel(new GridLayout(1,2));
    textPanel.add(getButtonPanel("Exit"));
    textPanel.add(getButtonPanel("Notify_Log"));
   
    mainFrame.add(textPanel);
    
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    mainFrame.setLocation((dimension.width  - mainFrame.getSize().width) / 2, (dimension.height - mainFrame.getSize().height) / 2);
    
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    mainFrame.setVisible(true);*/
}
