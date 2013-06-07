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
    public Boolean mustWait = true;
    public Boolean finished = false;
    
    public ThreadNews(int sleepTime, int port){
        listeningPort = port;
        receiver = new NetworkStringReceiver(listeningPort);
        mailingList = new Vector<NewsListener>();
        this.sleepTime = sleepTime;
    }
    
    @Override
    public void run(){
        try{
            while(!finished){
                if(!mustWait){
                    String receivedString;
                    String news;
                    String localisation;
                    receivedString = receiver.getMessage();
                    if (!receivedString.equals("RIEN")){
                        String tok[] = receivedString.split(";");
                        localisation = tok[0];
                        news = tok[1];
                        NewsEvent newsEvent = new NewsEvent(this, news, localisation);
                        for(NewsListener n: mailingList){
                            n.newsDetected(newsEvent);
                        }
                    }
                    else
                        sleep(sleepTime);
                }
                sleep(sleepTime);
            }

        }
        catch (InterruptedException e){
        }

    }
    
    public static void AddNewsListener(NewsListener n){
        mailingList.add(n);
    }
}
