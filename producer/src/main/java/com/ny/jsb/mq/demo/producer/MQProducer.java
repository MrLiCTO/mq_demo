
package com.ny.jsb.mq.demo.producer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ny.jsb.mq.demo.params.MailParam;


@Service("mqProducer")
public class MQProducer {
	
	@Autowired
	private JmsTemplate activeMqJmsTemplate;
	@Autowired
	private Destination sessionAwareQueue;

	/**
	 * 发送消息.
	 * @param mail 
	 */
	public void sendMessage(final MailParam mail) {
		activeMqJmsTemplate.send(sessionAwareQueue,new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(JSONObject.toJSONString(mail));
			}
		});
		
	}

}
