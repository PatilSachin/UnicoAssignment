package com.nihilent.sender;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

public class MessageSender {
	
	final static Logger log = Logger.getLogger(MessageSender.class);
	
	private ConnectionFactory cf = null;
	private Connection connection =  null;
	private Session session = null;
	private Queue queue = null;
	

	public void sendMessage(final String businessMessage) throws Exception
    {
		
        String destinationName = "java:/jms/queue/messageQueue";
        
        try {         
        	if (connection == null || session==null) {
        		InitialContext jndi = new InitialContext();
        		cf = (ConnectionFactory)jndi.lookup("java:/ConnectionFactory");
        		queue = (Queue)jndi.lookup(destinationName);
        		connection = cf.createConnection();
        		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);        		
        		connection.start();
        	}
        	MessageProducer publisher = session.createProducer(queue);
            TextMessage message = session.createTextMessage(businessMessage);
            publisher.send(message);
            if (log.isDebugEnabled()) {
    			log.debug("End sendMessage");
    		}
        }
        finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                	log.error(e.getMessage());
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
