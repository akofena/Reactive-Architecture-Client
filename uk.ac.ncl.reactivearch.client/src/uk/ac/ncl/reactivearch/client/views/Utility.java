package uk.ac.ncl.reactivearch.client.views;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	
	//Constructor
	public Utility() {
		
	}
	
	public static void writeToFile(String str){		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("notify"));
			writer.append("\n"+str);
		} catch (IOException e) {
			
		}finally{
			try{
				if(writer != null) writer.close();
			} catch (IOException e) {
				
			}
		}
	
	}
	
	// Save uploaded file to new location
	public void writeToFile(InputStream uploadedInputStream,
            String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

       }
	
	//Get Current Date
	public static String getCurrentDate() {

		   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   //get current date time with Date()
		   Date date = new Date();
		   //System.out.println(dateFormat.format(date));
		  
		   //get current date time with Calendar()
		   //Calendar cal = Calendar.getInstance();
		   //System.out.println(dateFormat.format(cal.getTime()));
		   
		   return dateFormat.format(date).toString();

	  }
}
