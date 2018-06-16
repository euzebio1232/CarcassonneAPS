package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

import java.util.Collections;
import java.util.ArrayList;;

public class TileReal implements BolsaDeTiles {
	ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public TileReal(Tile primeiro, Tile... tiles) {
		for (int k = 0; k < tiles.length; k++) {
			this.tiles.add(tiles[k]);
		}
		Collections.shuffle(this.tiles);
		this.tiles.add(0,primeiro);
	}

	@Override
	public Tile pegar() {
		if (tiles.isEmpty()) {
			return null;
		}
		return tiles.get(tiles.size()-1);
	}

}
