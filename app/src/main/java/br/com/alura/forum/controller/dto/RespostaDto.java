package br.com.alura.forum.controller.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.alura.forum.modelo.Resposta;

public class RespostaDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private final Long id;
	private final String mensagem;
	private final LocalDateTime dataCriacao;
	private final String nomeAutor;
	
	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}
	
}
