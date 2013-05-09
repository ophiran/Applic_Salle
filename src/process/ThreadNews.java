/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import network.NetworkStringReceiver;
import libNews.NewsEvent;
import java.util.*;

/**
 *
 * @author ekym
 */
public class ThreadNews extends Thread{
    protected NetworkStringReceiver receiver;
    protected int listeningPort = 25678;
    
    public ThreadNews(){
        receiver = new NetworkStringReceiver(listeningPort);
    }
    public void run(){
        String receivedString;
        String news;
        String localisation;
        do{
            receivedString = receiver.getMessage();
        }while(receivedString.equals("RIEN"));
        StringTokenizer tok = new StringTokenizer(receivedString);
        while (tok.hasMoreElements()){
            
        }
        news.equals(receivedString.);
        NewsEvent newsEvent = new newsEvent(this, "", "");
        
    }
        
        
        
}
