package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

public class Model {
	
	private int NMAX;
	private int TMAX;
	
	private int segreto;  // numero da indovinare
	private int tentativi;  // tentativi effettuati
	
	private boolean inGame;
	
	public Model() {
		this.NMAX = 100;
		this.TMAX = 7;
		this.inGame = false;
	}
	
	/**
	 * Avvia una nuova partita, generando un nuovo numero segreto.
	 */
	public void newGame() {
		this.segreto = (int)(Math.random()*NMAX) + 1;
    	this.tentativi = 0;
    	this.inGame = true;
	}
		
	/**
	 * fare il nuovo tentativo di indovinare il numero
	 * @param t valore numerico del tentativo
	 * @return 0 se indovinato, +1 se troppo alto, -1 se troppo basso
	 * @throws IllegalAccessException 
	 */
	public int tentativo(int t) {
		
		if(!inGame) {
			throw new IllegalStateException("Partita non attiva!");
		}
		
		if(t<1 || t>this.NMAX) {
			throw new InvalidParameterException("Tentativo fuori range!");
		}
		
		this.tentativi++;
		
		if (t==segreto)
			return 0;
		if (t<segreto)
			return -1;
		return +1;
	}
	
	public boolean isInGame() {
		return inGame;
	}
	
	public int getTentativi() {
		return tentativi;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
}
