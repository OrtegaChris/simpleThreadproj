package threadproj;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.time.*;
import java.util.Scanner;

public class WorkerRunnable implements Runnable {
	protected Socket clientSocket = null;
    protected String serverText   = null;
   
    
    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;  
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<Task> t = new ArrayList<Task>();
		taskinterface ti = new taskinterface(t);
		new Thread(ti).run();
	   
	    
		try{
			
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            System.out.println(LocalDateTime.now());
            connectDb conn = new connectDb();
            new Thread(conn).run(); // only get results from db
            String tasklist = conn.conDb(); 
            String time = "<h3>"+String.format("Time: %tH:%tM:%tS", Calendar.getInstance(),Calendar.getInstance(),Calendar.getInstance())+"</h3>";
            output.write(("HTTP/1.1 200 OK\n\n <h1>Today's Events</h1> " +
this.serverText + " - " +
time + "\n" + tasklist +
"").getBytes());
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        } 

	}

}
