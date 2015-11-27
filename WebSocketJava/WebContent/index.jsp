<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
     <textarea rows="10" cols="50" id="messages" readonly="readonly"></textarea><br/>
     <input type="text" id="forsend" size="45"/><input type="button" value="Send" onclick="sendMessage()"/>
     <script type="text/javascript">
     
     var websocket=new WebSocket("ws://localhost:8080/WebSocketJava/chating"); //ws://localhost:8080/ProjectName/annotation.  @ServerEndpoint("/chating")
     
     var messages=document.getElementById("messages");
     var message=document.getElementById("forsend");

     websocket.onopen=function(message){
    	 messages.value="connection to server\n";
     }
     websocket.onclose=function(message){
    	 messages.value="onClose connection\n";
     }
     websocket.onmessage=function(message){
    	 messages.value+=message.data+"\n";
    	 getAutoplay();
     }
     function sendMessage(){
    	 websocket.send(message.value);
    	 message.value="";
     }
     function getAutoplay() {
 		var snd = new Audio('alert2.mp3');
 		snd.play();
 	}
     </script>
</body>
</html>