package com.senai.paola.PrjGame.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.paola.PrjGame.entities.Jogo;
import com.senai.paola.PrjGame.services.JogoService;

@RestController
@RequestMapping("/jogos")
public class JogoController {

	private final JogoService jogoService;

	@GetMapping("/home")
	public String paginaInicial() {
		return "index";
	}

	@Autowired
	public JogoController(JogoService jogoService) {
		this.jogoService = jogoService;
	}

	@PostMapping
	public Jogo createJogo(@RequestBody Jogo jogo) {
		return jogoService.saveJogo(jogo);
	}
	
	@GetMapping
	public ResponseEntity<List<Jogo>> getAllJogos(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Jogo> jogos = jogoService.getAllJogos();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(jogos);
	}
	
	@PutMapping("/{Id}")
	public Jogo updateJogo(@PathVariable Long Id, @RequestBody Jogo jogo) {
	    return jogoService.updateJogo(Id, jogo);
	}


	@DeleteMapping("/{Id}")
	public void deleteJogo(@PathVariable Long Id) {
		jogoService.deleteJogo(Id);
	}

}
