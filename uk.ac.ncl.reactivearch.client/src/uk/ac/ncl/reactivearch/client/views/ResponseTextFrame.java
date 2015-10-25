package uk.ac.ncl.reactivearch.client.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ResponseTextFrame extends JPanel {
	
	public static JTextArea textArea;
	static JFrame frame; 
	static String append;
	
	public void setText(String str){
		append = str;
	}
		//Append text to TextArea of Panel
		public static void appender(String str){
			textArea.append(str);			
		}

	    public ResponseTextFrame() {
	        initializeUI();
	    }
	 
	    private void initializeUI() {
	        this.setLayout(new BorderLayout());
	        this.setPreferredSize(new Dimension(700, 500));
	 
	        textArea = new JTextArea(9, 40);
	        textArea.append("\n........................................................................");
	        textArea.append("\n[INFO] Response from Server:");
	        textArea.append("\n........................................................................\n");
	       	        
	        //textArea.append(FileChooser.getAppendMess());
	        
	        textArea.append(append);
	        //
	        // By default the JTextAread is editable, calling
	        // setEditable(false) produce uneditable JTextArea.
	        //
	        textArea.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(textArea);
	 
	        this.add(scrollPane, BorderLayout.CENTER);
	    }
	    
	    //Append Text to JTextArea
	    public static void setTestInTextArea(String str){
	    	//[set date and time for action]
	    	append = str;
	    }
	 
	    public static void showFrame() {
	        JPanel panel = new ResponseTextFrame();
	        panel.setOpaque(true);
	 
	        frame = new JFrame("Response From Reactive Achitecture");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
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

	        // Add an JButton
	        JButton exitButton= new JButton("Exit!");
	        frame.add(exitButton);
	        exitButton.addActionListener(new exitApp());
	        exitButton.setSize(40,40);
	        //file.add(exitButton);
	        
	        frame.setContentPane(panel);
	        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	        frame.setLocation((dimension.width  - frame.getSize().width) / 2, (dimension.height - frame.getSize().height) / 2);
	        
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
	        	frame.dispose();
	        }
	    }
	 
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	
	            	ResponseTextFrame.showFrame();
	            }
	        });
	    }
	    
	    public static void displayTextPane() {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	
	            	ResponseTextFrame.showFrame();
	            }
	        });
	    }
}
