package com.easytocourse.service;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.easytocourse.dao.EmployeeRepository;
import com.easytocourse.model.ActionItems;

@Service
public class Employeeservice {

	@Autowired
	EmployeeRepository emprepo;
	
	public static final String queue="MYQUEUE";
	@Value("${spring.message.exchange}")
	private String exchange;
	@Value("${spring.message.key}")
	private String key;
	
	@Autowired
	private AmqpTemplate template;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public List<ActionItems> getallactionitems()
	{
		return emprepo.findAll();
	}

	public ActionItems saveaction(ActionItems item) {
		ActionItems saveditem=emprepo.save(item);
		
		template.convertAndSend(exchange, key, saveditem);
		return saveditem;
	}
	
	@RabbitListener(queues= {queue})
	public void consumemessage(ActionItems action)
	{
	
	  /*	SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(action.getEmail());
		message.setSubject("Pending Tasks");
		message.setText(action.getName() + " your action: " + '"' + action.getAction() + '"'
				+ "  is pending due date is:" + action.getDuedate());
		mailSender.send(message);
      */
		System.out.println(action);
	}

}
