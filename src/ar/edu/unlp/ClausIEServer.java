package ar.edu.unlp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

import de.mpii.clausie.ClausIE;

/**
 * 
 * @author Juan M. Rodríguez
 *
 */
public class ClausIEServer {
	
	static final int PORT = 9999;
	static final boolean verbose = true;
	protected boolean exit = false;
	protected ClausIE clausIE  = null;
	
	public static void main(String[] args) {
				
			ClausIEServer clausIEServer = new ClausIEServer();
			clausIEServer.initClausIE();
			clausIEServer.startServer();
		
	}

	public void initClausIE() {
		this.clausIE = new ClausIE();		
		clausIE.initParser();
		
	}
	
	public void startServer(){
		ServerSocket serverConnect = null;
		try {
			serverConnect = new ServerSocket(PORT);
			System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
			
			// we listen until user halts server execution
			while (!this.exit) {
				JavaHTTPServer myServer = new JavaHTTPServer(serverConnect.accept(), this, this.clausIE);
				myServer.setVerbose(verbose);
				
				if (verbose) {
					System.out.println("Connecton opened. (" + new Date() + ")");
				}
				
				// create dedicated thread to manage the client connection
				Thread thread = new Thread(myServer);
				thread.start();
			}
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		}
	}
	
	public void setExit(boolean b) {
		this.exit = b;
		
	}
}