package br.ufpb.dcx.aps.carcassone.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.ufpb.dcx.aps.carcassone.resource.InfoPartidaResource;
import br.ufpb.dcx.aps.carcassone.BolsaDeTiles;
import br.ufpb.dcx.aps.carcassone.Tiles;
import br.ufpb.dcx.aps.carcassone.Cor;
import br.ufpb.dcx.aps.carcassone.Jogador;
import br.ufpb.dcx.aps.carcassone.Lado;
import br.ufpb.dcx.aps.carcassone.Partida;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

@RestController
@RequestMapping("/partida")

public class JogoResource {
	
	SistemaResource servicos = new SistemaResource();
	
	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> criarPartida(@RequestBody Cor... sequencia){
    	return servicos.criarPartida(sequencia);
    }
    
	@RequestMapping("/relatorio")
	public ResponseEntity<InfoPartidaResource> relatorioPartida() {
		return servicos.relatorioPartida();
	}

	@RequestMapping("relatorio/turno")
	public ResponseEntity<InfoTurnoResource> relatorioTurno() {
		return servicos.relatorioTurno();
	}
	
	@RequestMapping(value = "/tile", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Tile> posicionarTile(@RequestBody Tile tile) {
		return new ResponseEntity<Tile>(tile, HttpStatus.OK);
	}
	
	@RequestMapping("/jogador")
	public ResponseEntity<Jogador[]> recuperarJogadores() {
		return servicos.recuperarPecas();
	}

	@RequestMapping(value = "jogador/{cor}", method = RequestMethod.GET)
	public ResponseEntity<Jogador> recuperarJogador(@PathVariable(value = "cor") Cor cor) {
		return servicos.pegarPecasJogador(cor);
	}

	@RequestMapping("/tile")
	public ResponseEntity<Tile> pegarTile() {
		return servicos.pegarTile();
	}
	
	@RequestMapping(value = "/girartile", method = RequestMethod.PUT)
	public ResponseEntity<Tile> girarTile() {
		return servicos.girarTile();
}
	
	
}
