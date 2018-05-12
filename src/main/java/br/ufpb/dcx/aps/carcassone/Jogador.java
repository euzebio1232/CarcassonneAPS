package br.ufpb.dcx.aps.carcassone;

public class Jogador{

	private Cor jogador;
	private int pontosJogador = 0;
	private int meeplesJogador = 7;
	
	public Jogador(Cor cor) {
		// TODO Auto-generated constructor stub
		jogador =cor;
	}

	public Cor getCor() {
		return jogador;
	}
	
	public int getPontos() {
		return pontosJogador;
	}
	
	public int quantidadeMeeples() {
		return meeplesJogador;
	}
	
	@Override
	public String toString() {
		return jogador+"("+pontosJogador+","+meeplesJogador+")";
	}
}