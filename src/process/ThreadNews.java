/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import network.NetworkStringReceiver;
import libNews.*;
import java.util.*;

/**
 *
 * @author ekym
 */
public class ThreadNews extends Thread{
    protected NetworkStringReceiver receiver;
    protected int listeningPort;
    protected int sleepTime;
    static protected Vector<NewsListener> mailingList;
    
    public ThreadNews(int sleepTime, int port){
        receiver = new NetworkStringReceiver(listeningPort);
        listeningPort = port;
        mailingList = new Vector<NewsListener>();
        this.sleepTime = sleepTime;
    }
    
    @Override
    public void run(){
        try{
            while(true){
                String receivedString;
                String news;
                String localisation;
                do{
                    receivedString = receiver.getMessage();
                    sleep(sleepTime);
                }while(receivedString.equals("RIEN"));
                StringTokenizer tok = new StringTokenizer(receivedString, ";");
                localisation = tok.nextToken();
                news = tok.nextToken();
                NewsEvent newsEvent = new NewsEvent(this, localisation, news);
                for(NewsListener n: mailingList){
                    n.newsDetected(newsEvent);
                }
            }
        }
        catch (InterruptedException e){ 
            return; 
        }
    }
    
    public void AddNewsListener(NewsListener n){
        mailingList.add(n);
    }
        
        
        
}
