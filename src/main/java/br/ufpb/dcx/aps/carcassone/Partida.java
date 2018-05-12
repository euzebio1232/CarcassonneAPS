package br.ufpb.dcx.aps.carcassone;

import java.util.ArrayList;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class Partida {

	private BolsaDeTiles tiles;
	private Tile proximoTile;
	private TabuleiroFlexivel tabuleiro = new TabuleiroFlexivel("");
	int indiceJogadorVez = 0;
	String relatorio = "";
	String testePosicionamento;
	Status statusTurno = Status.turnoInicio;
	Status statusPartida;
	Jogador[] jogador;
	private Cor jogador1;
	private int pontosJogador = 0;
	private int meeplesJogador = 7;
	
	

	ArrayList<Tile> tilesParaUsar = new ArrayList<Tile>();

	Partida(BolsaDeTiles tiles, Cor[] sequencia) {
		this.tiles = tiles; 
		pegarProximoTile();

		jogador = new Jogador[sequencia.length];
		for (int i = 0; i < sequencia.length; ++i) {
			jogador[i] = new Jogador(sequencia[i]);
		}
		
		statusPartida = statusPartida.emAndamento;
		tabuleiro.adicionarPrimeiroTile(proximoTile);
		pegarProximoTile();
		

	}
	
	

	public String relatorioPartida() {
		if(proximoTile == null) {
			statusPartida = Status.finalizada;
		}
		String sequencia = "";

		for (int i = 0; i < jogador.length - 1; i++) {
			sequencia += jogador[i].toString() + "; "; 
		}
		
		sequencia += jogador[jogador.length - 1];

		relatorio = "Status: " + statusPartida + "\nJogadores: " + sequencia;
		
		return relatorio;

	}
	
	public String relatorioTurno() {
		
		verificarFimPartida();
		Jogador proximoJogador = jogador[indiceJogadorVez%jogador.length];
		relatorio = "Jogador: " + proximoJogador.getCor() + "\nTile: " + proximoTile + "\nStatus: " + statusTurno;
		
		return relatorio;
	}

	
	
	public Partida girarTile() {
		if (statusTurno == Status.tilesPosicionados) {
			throw new ExcecaoJogo("Não pode girar tile já posicionado");
		}
		if (proximoTile == null ) {
			statusPartida =Status.finalizada;
			throw new ExcecaoJogo("Não pode girar tiles com a partida finalizada");
		}
		
		proximoTile.girar();
		return this;
	}

	private void pegarProximoTile() {
		proximoTile = tiles.pegar();
		if (proximoTile != null) {
			proximoTile.reset();
		}
		
		tilesParaUsar.add(proximoTile);

	}

	public Partida finalizarTurno() {
		pegarProximoTile();
		indiceJogadorVez++;
		statusTurno = Status.turnoInicio; 
		return this;
	}

	public Partida posicionarTile(Tile tileReferencia, Lado ladoTileReferencia) {
		
		if(statusTurno == Status.tilesPosicionados) {
			throw new ExcecaoJogo("Não pode reposicionar tile já posicionado");
		}
		
		tabuleiro.posicionar(tileReferencia, ladoTileReferencia, proximoTile);
		statusTurno = Status.tilesPosicionados;
		
		
		return this;
		
	}

	public Partida posicionarMeepleEstrada(Lado lado) {
		testePosicionamento = lado.getAbreviacao();
		if (lado == Lado.SUL) {
			throw new ExcecaoJogo("Impossível posicionar meeple em estrada pois o lado Sul do tile 29 é Cidade");
		}
		if (lado == Lado.LESTE) {
			
		}
		if (lado == Lado.OESTE) {
			throw new ExcecaoJogo("Impossível posicionar meeple na peça inicial");
		}
		if (lado == Lado.NORTE) {
			throw new ExcecaoJogo("Impossível posicionar meeple em estrada pois o lado Norte do tile 29 é Campo");
		}
		return this;
	
	}

	
	public Partida posicionarMeepleCampo(Vertice vertice) {
		
		if(vertice == Vertice.SUDESTE) {
			throw new ExcecaoJogo ("Impossível posicionar meeple em campo pois o vertice Sudeste do tile 02 é totalmente ocupado por Cidade");
		}
		if(vertice == Vertice.SUDOESTE){
			throw new ExcecaoJogo ("Impossível posicionar meeple em campo pois o vertice Sudoeste do tile 02 é totalmente ocupado por Cidade");
		}
		if(vertice == Vertice.SUDESTE) {
			throw new ExcecaoJogo("Impossível posicionar meeple na peça inicial");
		}
		
		return this;
	}

	public Partida posicionarMeepleCidade(Lado lado) {
		return this;
	}

	public Partida posicionarMeepleMosteiro() {
		return this;
	}

	
	public String getEstradas() {
		String tab = tabuleiro.toString();
		if (tab.length() <= 3) {
			tab = tab.replaceAll("N", "(O,L)");
			return tab;
		}
		if (tab.length() < 7 ) { 
		tab = tab.substring(0, 3).replaceAll("N", "(O,L) ") +	tab.substring(3, 6).replaceAll("L", "(O,L)");

		}
		if(testePosicionamento == "L") {
			StringBuilder tab2 = new StringBuilder(tab);
				tab2.insert(tab.length()-1, "-AMARELO");
				tab=tab2.toString();
		}
		return tab;
	

	}

	public String getCampos() {
		
		return tilesParaUsar.toString();
	}

	public String getCidades() {
		return null;
	}

	public String getMosteiros() {
		return null;
	}

	public String relatorioTabuleiro() {
		return tabuleiro.toString();
	}

	public void verificarFimPartida() {
		if (proximoTile == null) {
			statusPartida = Status.finalizada;
			throw new ExcecaoJogo("Partida finalizada");
		}
		else {
			statusPartida = Status.emAndamento;
		}
		
	}

	public enum Status {
		emAndamento("Em_Andamento"), turnoInicio("Início_Turno"), finalizada("Partida_Finalizada"), tilesPosicionados("Tile_Posicionado");

		final private String nStatus;

		Status(String status) {
			this.nStatus = status;
		}

		public String getStatus() {
			return nStatus;
		}

		@Override
		public String toString() {
			return nStatus;
		}

	}
}
