package uk.ac.ncl.reactivearch.client.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

@SuppressWarnings("serial")
public class ClientMenu extends JFrame{
	public static JTextArea area = new JTextArea();
	
	public static void clientForm() {
		 		
		//Create GUI
		final JFrame win = new JFrame();
		JPanel but = new JPanel();
		
		Font font = new Font("Courier", Font.PLAIN, 11);
		
		JLabel devLabel = new JLabel("	MENU:");
		JLabel devIDLabel = new JLabel();
						
		//Wrapping the TextArea
		area.setWrapStyleWord(true);
		area.setLineWrap(true); 
		area.setEditable(false);
		area.setFont(font);
		area.setPreferredSize(new Dimension(440, 380));
		
		JScrollPane scroll = new JScrollPane (area,
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		but.setLayout(new GridLayout(0,1));
		win.add(scroll);
		//Container contentPane = win.getContentPane();
		
		//contentPane.add(
		//		new JScrollPane(
		//				area,
		//				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		//				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED),
		//		BorderLayout.CENTER);
		
		//win.setContentPane(panel);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        win.setLocation(dimension.width/2-win.getSize().width/2, dimension.height/2-win.getSize().height/2);
		
		win.setLocationRelativeTo(null);
		win.setPreferredSize(new Dimension(650,450));
		win.setResizable(false);
		win.setVisible(true);
		win.setLayout(new FlowLayout());
		win.setTitle("Reactive Architecture Operations [CLIENT]");
		win.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
       //Heading
       String info = "[STATUS: "+Utility.getCurrentDate()+"]  DATABASE OPERATIONS: \n";
       area.append(info);
       
       
     //Register Button            
       JButton buttonReg = new JButton("Register");
       buttonReg.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
        
        	   RegisterDeveloper.display();
        	   
			   String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Registration of Developer";
				area.append(info);
				//if(!RegisterDeveloper.getDevID().equals(null)) devIDLabel.setText(RegisterDeveloper.getDevID());
				//else{devIDLabel.setText("DefaultDevID");}
				//devIDLabel.setText("	"+RegisterDeveloper.getDevID());
				//area.append("\n		"+RegisterDeveloper.getDevID()+" is assigned to Developer!");
           }
       });
       
       
       
       //Upload Row
       JButton buttonUp = new JButton("Upload Artefact(s)");
       buttonUp.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
           		try {
						String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Uploading Artefact(s)";
						area.append("\n"+info);
						ArtefactsUpload.main(null);
						//area.append("\n		"+FileChooser.getFileName()+" has been uploaded!");
					} catch (Exception e1) {
						e1.printStackTrace();
						String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Artefact(s) is/are NOT uploaded";
						area.append("\n"+info);	
					}
           }
       });
       
     //Delete Row [TO DO]
       JButton buttonDel = new JButton("Delete Artefact(s)");
       buttonDel.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
           	try {
           		//JSONObject json = null;
           		//DBOperations.deleteProj(json);
           		String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Artefact(s) Deletion";
           		area.append("\n"+info);
				} catch (Exception e3) {
					e3.printStackTrace();
					String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Artefact(s) is/are NOT deleted";
					area.append("\n"+info);
				}
           }
       });
       
     //Team Creation           
       JButton buttonTeam = new JButton("Create Team");
       buttonTeam.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
        	   try {
        		   
				String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Team Creation";
				TeamCreation.display();
           		area.append("\n"+info);
           		//area.append("\n		"+TeamCreation.getProjName()+" has been created!");
			} catch (Exception e1) {
				e1.printStackTrace();
				String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Team is NOT created";
				area.append("\n"+info);
			}
           }
       });
       
     //Download Artefact(s)
       JButton buttonDown = new JButton("Download Artefact(s)");
       buttonDown.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
           	try {
           		
           		ArtefactsDownload.display();
           		String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Project is being downloaded";
           		area.append("\n"+info);
           		//area.append("\n		"+ArtefactsDownload.getProjName()+" has been downloaded!");
				} catch (Exception e3) {
					e3.printStackTrace();
					String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Project NOT downloaded";//TO BE REWRITTEN
					area.append("\n"+info);
					//area.append(ArtefactsDownload.getProjName()+" has been downloaded ["+ArtefactsDownload.getPermission()+"]");
				}
           }
       });
       
    // ActionListener for CheckBox ---> Notification Widgets
       JCheckBox checkBox = new JCheckBox("Notify Me"); 
       
	       checkBox.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   
	        	   try {
	              		
	              		String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Notification Widget";
	              		area.append("\n"+info);
	              		
	              		String mess = "Developer ["+RegisterDeveloper.getDevID()+"] has changed artefact(s) for Project ["+TeamCreation.getProjName()+"]!";
	              		ClientMenu.notify("\n"+mess);
	              		area.append("\n"+mess);
	   				} catch (Exception e3) {
	   					e3.printStackTrace();
	   					String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> No Notification Widget";//TO BE REWRITTEN
	   					area.append("\n"+info);
	   				
	   				}
	           }
	         
	       	});
        
       
     //Notification Widgets ---> UNUSED
       JButton buttonNotify = new JButton("Notify Me");
       buttonNotify.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
           	try {
           		String mess = "Developer ["+RegisterDeveloper.getDevID()+"] has changed artefact(s) for Project ["+TeamCreation.getProjName()+"]!";
        		
           		ClientMenu.notify(mess);
           		//Tester.main(null);
           		
           		String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Notification Widget";
           		area.append(info);
				} catch (Exception e3) {
					e3.printStackTrace();
					String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> No Notification Widget";//TO BE REWRITTEN
					area.append(info);
				}
           }
       });
       
       
     //Notifications
       JButton buttonNote = new JButton("Notifications");
       buttonNote.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
           	try {
           		String mess = ArtefactsUpload.getNotify();
           		String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Notifications";
           		area.append("\n"+info);
           		area.append("\n"+mess);
				} catch (Exception e3) {
					e3.printStackTrace();
					String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> No Notifications";//TO BE REWRITTEN
					area.append("\n"+info);
				}
           }
       });
       
       
       //Print Log
       JButton buttonPrint = new JButton("Print Log");
       buttonPrint.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
           	try {
           		print(area);
           		String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> Printing Notifications Log";
           		area.append("\n"+info);
				} catch (Exception e3) {
					e3.printStackTrace();
					String info = "\n[STATUS: "+Utility.getCurrentDate()+"]--> No Notifications Printed";//TO BE REWRITTEN
					area.append("\n"+info);
				}
           }
       });
       
     //Exit Button            
       JButton buttonExit = new JButton("Exit");
       buttonExit.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               win.dispose();//Closes just the JFrame and not the entire system (as System.out() will do)
           }
       });
       			
       //area.append(areaHead);
       but.add(devLabel);
       but.add(devIDLabel);
       
       but.add(buttonReg);
       but.add(buttonTeam);
       but.add(buttonUp);
       but.add(buttonDown);
       but.add(buttonDel);
       but.add(checkBox);//Change to Check Box
       but.add(buttonNote);
       
       but.add(buttonPrint);
       but.add(buttonExit);
      
       win.getContentPane().add(BorderLayout.CENTER, but);
       win.add(but);
       win.getGlassPane(); 
       win.setAlwaysOnTop(false); //Sets the verification report in the JFrame on top of all opened windows
       win.pack();	
	}
	
	
	//Time Converter Method
			public static String getDate(long milliSeconds, String dateFormat)
			{
			    // Creates a DateFormatter object for displaying date in specified format.
			    DateFormat formatter = new SimpleDateFormat(dateFormat);

			    // Creates a calendar object that will convert the date and time value in milliseconds to date. 
			     Calendar calendar = Calendar.getInstance();
			     calendar.setTimeInMillis(milliSeconds);
			     
			     return formatter.format(calendar.getTime());
			}
	
	
	protected static void print(JTextArea area) {
	 // Print all text
	if (area.getSelectedText() == null)
	{
	    area.getText();
	}
	else // Print selected text
	{
	    area.getSelectedText();
	}
	try
	{
	    // This will show the print dialog.
	    area.print();
	}
	catch (PrinterException e)
	{
	    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	}
	}
	
	//Notification Pop-up
    public static void notify(String mess) {
    	    	
        final String message = mess;
        
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
                
                
                //NotifierDialog.notify("Hi ["+RegisterDeveloper.getDevID()+"], notification widget!", message, NotificationType.values()[toUse]);
                NotifierDialog.notify("Hi ["+RegisterDeveloper.getDevID()+"]!",ArtefactsUpload.getNotify(), NotificationType.values()[toUse]);
            }
            
        });
        shell.open();        
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();

    }
    
	

	public static void main(String[] args) throws Exception {
		clientForm();
		//System.out.println("Client Form Running.....");	
	}

}
