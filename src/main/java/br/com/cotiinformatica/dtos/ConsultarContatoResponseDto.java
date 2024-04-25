package br.com.cotiinformatica.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ConsultarContatoResponseDto {

	private UUID id;
	private String nome;
	private String email;
	private String telefone;
}
