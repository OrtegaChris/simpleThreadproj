/**
 * 
 */
package threadproj;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 * @author Chris
 *
 */
public class MultiThreadedServer implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	
	protected int          serverPort   = 800;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    
    public MultiThreadedServer(int port){
    	this.serverPort= port;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            new Thread(
                new WorkerRunnable(
                    clientSocket, "")
            ).run();
        }
        System.out.println("Server Stopped.") ;
	}
	  private synchronized boolean isStopped() {
	        return this.isStopped;
	    }

	    public synchronized void stop(){
	        this.isStopped = true;
	        try {
	            this.serverSocket.close();
	        } catch (IOException e) {
	            throw new RuntimeException("Error closing server", e);
	        }
	    }

	    private void openServerSocket() {
	        try {
	            this.serverSocket = new ServerSocket(this.serverPort);
	        } catch (IOException e) {
	            throw new RuntimeException("Cannot open port "+ serverPort, e);
	        }
	    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiThreadedServer server = new MultiThreadedServer(4000);
		new Thread(server).start();
		System.out.println("Server online");
	
		try{
			// launch server for specified time
			Thread.sleep(120*1000);
		}
		catch (InterruptedException e){
			e.printStackTrace();
	   }
		System.out.println("Stopping Server");
		server.stop();
	}

}
