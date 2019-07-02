/**
 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
 */

package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	private List<Corso> corsi;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboCorsi"
    private ComboBox<Corso> comboCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	txtResult.clear();

		try {
    	int matricola= Integer.parseInt(txtMatricola.getText());
    	
    	Studente stud= model.getStudentebymatricola(matricola);
    	if (stud==null)
    		txtResult.appendText("Matricola inesistente!! \n");
    		
    		List<Corso> corsi= this.model.getCorsibystudente(stud);
    		
    		StringBuilder sb = new StringBuilder();

			for (Corso corso : corsi) {
				sb.append(String.format("%-8s ", corso.getCodins()));
				sb.append(String.format("%-4s ", corso.getCrediti()));
				sb.append(String.format("%-45s ", corso.getNome()));
				sb.append(String.format("%-4s ", corso.getPd()));
				sb.append("\n");
			}
			txtResult.appendText(sb.toString());
    		} catch (NumberFormatException e) {
    			txtResult.setText("Inserire una matricola nel formato corretto.");
    		} catch (RuntimeException e) {
    			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
    		}
    	}

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	Corso corso= comboCorsi.getValue();
    	Corso corsovuoto= new Corso("", 0, "", 0);
    	if(corso.equals(corsovuoto))
    		txtResult.appendText("Nessun corso selezionato!\n");
    	else {
    		List<Studente> studenti= new LinkedList<>();
    		studenti= this.model.getStudentiIscrittiAlCorso(corso);
    		txtResult.appendText(studenti.toString());
    	}
    }

    @FXML
    void doCompletamentoAuto(ActionEvent event) {
    	
    	int matricola= Integer.parseInt(txtMatricola.getText());
    	
    	if (matricola==0)
    		txtResult.appendText("Inserire una matricola\n");
    		Studente s= this.model.getStudentebymatricola(matricola);
    		if(s==null)
    			txtResult.appendText("Matricola non presente!\n");
    		else {
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    		}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	Corso corso= comboCorsi.getValue();
    	if(corso==null)
    		txtResult.appendText("Nessun corso inserito!\n");
    	else {
    		boolean matr=txtMatricola.getText().matches("[0-9]");
    		if (matr==false)
    			txtResult.appendText("Caratteri non ammessi, inserire matricola valida.\n");
    		else {
    			int matricola=Integer.parseInt(txtMatricola.getText());
    			boolean iscritto = this.model.studenteiscrittoalcorso(matricola, corso);
    			if( iscritto ==true)
    				txtResult.appendText("Lo studente è iscritto correttamente al corso: " + corso.getNome()+ " \n");
    			else {
    				txtResult.appendText("Lo studente non è iscritto al corso!");
    				/*boolean iscrizione= this.model.inscriviStudenteAlCorso(matricola, corso);
    				if(iscrizione==true)
    					txtResult.appendText("Studenteiscritto correttaente!\n");
    				else txtResult.appendText("Iscrizione non riuscita!\n");*/
    			}
    				
    			
    		}
    		
    		
    		
    		
    	}
	}

	@FXML
    void doReset(ActionEvent event) {

    	txtMatricola.clear();
    	txtResult.clear();
		txtNome.clear();
		txtCognome.clear();
		comboCorsi.getSelectionModel().clearSelection();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboCorsi != null : "fx:id=\"comboCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;	
		setComboItems();
	}
	
	private void setComboItems() {
		// Ottieni tutti i corsi dal model

		corsi = model.getTuttiICorsi();
		// Aggiungi tutti i corsi alla ComboBox

		//Collections.sort( corsi);
		comboCorsi.getItems().addAll(corsi);
	}
}
