package br.ufpb.dcx.aps.carcassone.resource;

import br.ufpb.dcx.aps.carcassone.Partida.Status;
import br.ufpb.dcx.aps.carcassone.Jogador;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class InfoTurnoResource extends InfoResource {
	private Tile tile;
	private Jogador jogadores;
	
	public InfoTurnoResource(Jogador jogadores, Tile tile, Status status) {
		super(status);
		this.jogadores = jogadores;
		this.tile = tile;
	}
	public Jogador getJogador() {
		return jogadores;
	}
	
	public Tile getTile() {
		return tile;
	}
}
