package br.com.cotiinformatica.dtos;

import lombok.Data;

@Data
public class CriarContatoRequestDto {

	private String nome;
	private String email;
	private String telefone;
}
