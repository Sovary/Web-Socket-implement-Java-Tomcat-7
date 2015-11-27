package serverside;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chating")
public class ChatRoom {
	//static Set<Session> chater=Collections.synchronizedSet(new HashSet<Session>());
	static Map<String,Session> chater=Collections.synchronizedMap(new HashMap<String, Session>());
   
   @OnOpen
   public void handleOpen(Session usersession){
	   System.out.println("Client connected...!");   
	   System.out.println(usersession.toString());
   }
   @OnClose
   public void handleClose(Session usersession) throws IOException{
	 //  chater.remove(usersession);
	   System.out.println("Client disconnected...!");  
	   usersession.close();
   }
   
   @OnMessage
   public void handleMessage(String msg,Session usersession) throws IOException{
	   String username=(String) usersession.getUserProperties().get("username");
	   System.out.println(username);
	   if(username==null){
		   usersession.getUserProperties().put("username",msg);
		   chater.put(msg, usersession);
		   usersession.getBasicRemote().sendText("Your name is :"+msg);
		   
	   }else{
		   
	   Set<String> set=chater.keySet();
	   Iterator<String> it=set.iterator();
		   while(it.hasNext()){
			   String key=it.next();
			 
			 //  if(!key.equals(username)){
				   chater.get(key).getBasicRemote().sendText(username+" : "+msg);
			 //  }
				  
		   } 
	   }
   }
}
