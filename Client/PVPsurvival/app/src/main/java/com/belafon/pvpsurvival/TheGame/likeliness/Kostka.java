package com.belafon.pvpsurvival.TheGame.likeliness;

import java.util.Random;

public class Kostka {
	private int pocetSten;
	private Random random; 
	
	public Kostka(int pocetSten){
		this.pocetSten = pocetSten;
		random = new Random();
	}
	
	public int hod() {
		return(random.nextInt(this.pocetSten) + 1);
	}

	public int[] hod(int pocet) {
		int[] pole = new int[pocet];
		int i = pocet;
		while(i > 0) {
			pole[i - 1] = random.nextInt(this.pocetSten) + 1;
			i--;
		}
		return pole;

	}
}
