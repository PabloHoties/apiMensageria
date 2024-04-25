package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.components.RabbitMQSenderComponent;
import br.com.cotiinformatica.dtos.ConsultarContatoResponseDto;
import br.com.cotiinformatica.dtos.CriarContatoRequestDto;
import br.com.cotiinformatica.dtos.CriarContatoResponseDto;
import br.com.cotiinformatica.entities.Contato;
import br.com.cotiinformatica.repositories.ContatoRepository;

@RestController
@RequestMapping(value = "/api/contatos")
public class ContatosController {

	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private RabbitMQSenderComponent component;

	@PostMapping("criar")
	public ResponseEntity<CriarContatoResponseDto> criar(@RequestBody CriarContatoRequestDto dto) throws Exception {

		Contato contato = new Contato();
		contato.setId(UUID.randomUUID());
		contato.setNome(dto.getNome());
		contato.setEmail(dto.getEmail());
		contato.setTelefone(dto.getTelefone());

		contatoRepository.save(contato); // Gravar no banco de dados
		component.sendMessage(contato); // Enviar para a fila

		CriarContatoResponseDto response = new CriarContatoResponseDto();
		response.setId(contato.getId());
		response.setNome(contato.getNome());
		response.setEmail(contato.getEmail());
		response.setTelefone(contato.getTelefone());
		response.setDataHoraCadastro(new Date());

		return ResponseEntity.status(201).body(response);
	}

	@GetMapping("consultar")
	public ResponseEntity<List<ConsultarContatoResponseDto>> consultar() {

		List<Contato> contatos = contatoRepository.findAll();
		
		List<ConsultarContatoResponseDto> lista = new ArrayList<ConsultarContatoResponseDto>();
		for(Contato contato : contatos) {
			
			ConsultarContatoResponseDto response = new ConsultarContatoResponseDto();
			response.setId(contato.getId());
			response.setNome(contato.getNome());
			response.setEmail(contato.getEmail());
			response.setTelefone(contato.getTelefone());
			
			lista.add(response);
		}
		
		return ResponseEntity.status(200).body(lista);
	}
}
