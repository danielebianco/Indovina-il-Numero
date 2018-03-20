package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

import javafx.beans.property.*;

public class Model {
	
	private int NMAX;
	private int TMAX;
	
	private int segreto;  // numero da indovinare
//	private int tentativi;  // tentativi effettuati
	private IntegerProperty tentativi = new SimpleIntegerProperty();
	
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
    	this.tentativi.set(0);;
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
		
		if(!valoreValido(t)) {
			throw new InvalidParameterException("Tentativo fuori range!");
		}
		
		this.tentativi.set(tentativi.get()+1);
		
		if(this.tentativi.get() == this.TMAX) {
			this.inGame = false;
		}
		
		if (t==segreto) {
			this.inGame = false;
			return 0;
		}
			
		if (t<segreto)
			return -1;
		return +1;
	}
	
	public boolean valoreValido(int tentativo) {
		return tentativo>=1 && tentativo<=this.NMAX;
	}
	
	public boolean isInGame() {
		return inGame;
	}
	
	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	public int getSegreto() {
		return segreto;
	}

	public final IntegerProperty tentativiProperty() {
		return this.tentativi;
	}
 	
	public final int getTentativi() {
		return this.tentativiProperty().get();
	}
		
}
