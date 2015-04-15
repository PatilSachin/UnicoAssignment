package com.nihilent.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.nihilent.consumer.QueueListener;
import com.nihilent.sender.MessageSender;

@Path("/json/product")
public class JSONService {
	final static Logger log = Logger.getLogger(JSONService.class);
	
	@POST
	@Path("/pushmsg")
	@Consumes("application/json")
	public Response pushmsg() {
		
		MessageSender ms=new MessageSender();
		String msg="10-20";
		try {
			ms.sendMessage(msg);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		
		return Response.status(201).entity(msg).build();
		
	}
	
	@GET
	@Path("/showmsg")	
	@Produces("application/json")
	public String showQueueData() throws Exception {
		
		QueueListener listener = new QueueListener();
		List<String> messageList = listener.listenMessage();
		StringBuilder message = new StringBuilder();
		for (String string : messageList) {
			message.append(string);
			message.append("\n");
						
		}
		
		return message.toString();
	}
	

	
}
