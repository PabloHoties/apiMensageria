package br.com.cotiinformatica.entities;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "contatos")
public class Contato {

	@Id
	private UUID id;
	private String nome;
	private String email;
	private String telefone;
}
