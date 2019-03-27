package it.polito.tdp.lab04.model;

public class Studente {
	private String nome;
	private String cognome;
	private int matricola;
	
	public Studente(String nome, String cognome, int matricola) {
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public int getMatricola() {
		return matricola;
	}
		
}
