// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;
import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
    clientUI.display(msg.toString());
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
    try {
    	message = message.toLowerCase();
    	if (message.startsWith("#")) {
    		// Switch statement based on command given.
    		switch(message.substring(1)) {
    			case("quit"):
    				this.quit();
    				break;
    			case("logoff"):
    				this.closeConnection(); // Close connection but don't quit
    				break;
    			case("login"):
    				if (this.isConnected()) {
    					throw new IllegalStateException("Client already connected to server.");
    				}
    				this.openConnection();
    				break;
    			case("gethost"):
    				clientUI.display(this.getHost());
    				break;
    			case("getport"):
    				clientUI.display(String.valueOf(this.getPort()));
    				break;
    			default:
    				if (message.contains("sethost")) {
    					//Try to parse the host.
    					String host = message.substring(9);
    					clientUI.display(host);
    				} else if (message.contains("setport")) {
    					//Try to parse the port.
    					String portString = message.substring(9);
    					clientUI.display(portString);
    				}
    			}
    	}
    	sendToServer(message);
    } catch(IOException e) {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /**
   * E49a JLC
   * Called when the connection to the server has been closed.
   */
  @Override
  public void connectionClosed() {
	  clientUI.display("Connection to the server has been closed.");
	  clientUI.display("Goodbye...");
	  System.exit(0);
  }
  
  /**
   * Called when the client's thread encounters an exception.
   */
  @Override
  public void connectionException(Exception e) {
	  System.out.println("Connection Exception");
	  //e.printStackTrace();
	  this.quit();
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) { clientUI.display("Error closing connection.");}
    System.exit(0);
  }
}
//End of ChatClient class
