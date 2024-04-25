package br.com.cotiinformatica.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CriarContatoResponseDto {

	private UUID id;
	private String nome;
	private String email;
	private String telefone;
	private Date dataHoraCadastro;
}
