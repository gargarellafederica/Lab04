package it.polito.tdp.lab04.model;

public class Corso {

	private String nomeCorso;
	private int crediti;
	
	public Corso(String nomeCorso, int crediti) {
		super();
		this.nomeCorso = nomeCorso;
		this.crediti = crediti;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public int getCrediti() {
		return crediti;
	}
	
	
}
