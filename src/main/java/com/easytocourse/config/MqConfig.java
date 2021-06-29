package com.easytocourse.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

	@Value("${spring.message.queuelow}")
	private String queuelow;
	@Value("${spring.message.queuehigh}")
	private String queuehigh;
	@Value("${spring.message.exchange}")
	private String exchange;
	@Value("${spring.message.key}")
	private String key;
	@Value("${spring.message.highkey}")
	private String highkey;

	@Bean
	public Queue lowpriority() {
		return new Queue(this.queuelow);
	}

	@Bean
	public Queue highpriority() {
		return new Queue(this.queuehigh);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}

	@Bean
	public Binding lowbinding(Queue lowpriority, TopicExchange exchange) {
		return BindingBuilder.bind(lowpriority).to(exchange).with(key);
	}

	@Bean
	public Binding highbinding(Queue highpriority, TopicExchange exchange) {
		return BindingBuilder.bind(highpriority).to(exchange).with(highkey);
	}

	@Bean
	public Jackson2JsonMessageConverter convertor() {
		return new Jackson2JsonMessageConverter();
	}

/*	@Bean
	public AmqpTemplate template(ConnectionFactory confactory) {
		RabbitTemplate rabbitemplate = new RabbitTemplate(confactory);
		rabbitemplate.setMessageConverter(convertor());
		return rabbitemplate;
	}
*/
	/*
	 * 1. create a fanout exchange if you want to send to the multiple queue same
	 * message
	 * 
	 * @Bean public FanoutExchange exchange() { return new FanoutExchange(exchange);
	 * }
	 * 
	 * 2. at the time of binding no need to specify the key(with() is not required)
	 * 3. at the time of sending message using convertandsend(exhange,"",message) no
	 * need of key)
	 */

}
