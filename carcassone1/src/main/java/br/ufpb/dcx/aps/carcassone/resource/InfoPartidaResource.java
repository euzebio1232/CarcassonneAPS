package br.ufpb.dcx.aps.carcassone.resource;

import br.ufpb.dcx.aps.carcassone.Partida.Status;
import br.ufpb.dcx.aps.carcassone.Jogador;

public class InfoPartidaResource {
	Jogador[] jogadores = null;
	Status status;
	
	public InfoPartidaResource(Status status, Jogador...jogadores) {
		this.status = status;
		this.jogadores = jogadores;
	}
	
	public Jogador[] getJogadores() {
		return jogadores;
	}
}
