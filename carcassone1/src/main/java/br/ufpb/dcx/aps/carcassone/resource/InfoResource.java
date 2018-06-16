package br.ufpb.dcx.aps.carcassone.resource;

import br.ufpb.dcx.aps.carcassone.Partida.Status;;

public class InfoResource {
	Status status;
	
	public InfoResource(Status status) {
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
}
