package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private StudenteDAO Studentedao;
	private CorsoDAO Corsodao;
	
	public Model() {
		Studentedao= new StudenteDAO();
		Corsodao= new CorsoDAO();
	}


	public Studente getStudentebymatricola(int matricola) {
		return Studentedao.getStudentebymatricola(matricola);
	}


	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return Corsodao.getStudentiIscrittiAlCorso(corso);
	}


	public List<Corso> getCorsibystudente(Studente studente) {
		return Studentedao.getCorsibystudente(studente);
	}


	public List<Corso> getTuttiICorsi() {
		return Corsodao.getTuttiICorsi();
	}


	public boolean studenteiscrittoalcorso(int matricola, Corso corso) {
		return Studentedao.studenteiscrittoalcorso( matricola, corso);
	}
	
	

}
