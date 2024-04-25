package br.com.cotiinformatica.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import br.com.cotiinformatica.dtos.EnviarEmailDto;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailSenderComponent {
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String userName;

	public void enviarEmail(EnviarEmailDto dto) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setFrom(userName);
		helper.setTo(dto.getEmailDest());
		helper.setSubject(dto.getAssunto());
		helper.setText(dto.getMensagem());
		javaMailSender.send(mimeMessage);
	}
}
