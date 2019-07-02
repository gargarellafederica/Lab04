package it.polito.tdp.lab04.DAO;

public class TestDB {

	public static void main(String[] args) {
		
		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();

		System.out.println(cdao.getTuttiICorsi().toString());
		
		StudenteDAO sdao = new StudenteDAO();
		sdao.getStudentebymatricola(154817);
		System.out.println(sdao.getStudentebymatricola(154817).toString());

	}

}
