package br.com.cotiinformatica.components;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.dtos.EnviarEmailDto;
import br.com.cotiinformatica.entities.Contato;

@Component
public class RabbitMQConsumerComponent {

	@Autowired
	private MailSenderComponent mailSenderComponent;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	// Método para ler cada mensagem gravada na fila
	@RabbitListener(queues = { "${queue.name}" })
	public void receive(@Payload String message) {
		
		try {
			
			// Ler e deserializar os dados do contato contido na fila
			Contato contato = objectMapper.readValue(message, Contato.class);

			// Criando o conteúdo do email
			EnviarEmailDto dto = new EnviarEmailDto();
			dto.setEmailDest(contato.getEmail());
			dto.setAssunto("Parabéns, " + contato.getNome() + "! Seu cadastro foi realizado com sucesso.");
			dto.setMensagem("Olá, " + contato.getNome() + "\n\nSeu cadastro foi feito com sucesso!");
			
			// Enviando o email de boas vindas
			mailSenderComponent.enviarEmail(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
