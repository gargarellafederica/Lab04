package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;
public class StudenteDAO {
	
	public Studente getStudentebymatricola(int matricola) {

		Studente s=null;
		final String sql = "SELECT * FROM studente " + 
				"WHERE matricola= ? ";		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");
				

				s= new Studente(matricola,cognome, nome, cds);

			}
			return s;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}

	public boolean studenteiscrittoalcorso(int matricola, Corso corso) {
		boolean iscritto= false;
		final String sql = "SELECT * FROM iscrizione where codins=? and matricola=?";

		try {

			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			st.setInt(2, matricola);
			
			ResultSet rs = st.executeQuery();

			if (rs.next())
				iscritto = true;

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		return iscritto;
		
	}
public List<Corso> getCorsibystudente(Studente studente) {
		
		List<Corso> corsi=new LinkedList<>();
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd " + 
				"FROM studente s, corso c, iscrizione i " + 
				"WHERE c.codins=i.codins AND i.matricola= s.matricola " + 
				"AND  s.matricola = ? ";		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, studente.getMatricola());
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				String codins = rs.getString("matricola");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				

				Corso c= new Corso(codins, crediti, nome, pd);
				corsi.add(c);

			}
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}


}

