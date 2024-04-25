package br.com.cotiinformatica.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	/*
	 * Capturando o nome da fila que foi configurado
	 * no arquivo /application.properties
	 */
	@Value("${queue.name}")
	private String queueName;
	
	/*
	 * Configurando a fila que será acessada
	 * no servidor do RabbitMQ
	 */
	@Bean
	public Queue queue() {
		return new Queue(queueName, true);
	}
}
