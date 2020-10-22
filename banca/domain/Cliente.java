package banca.domain;

import java.util.Map;
import java.util.HashMap;
import banca.domain.*;

public class Cliente {
  // Campi del Oggetto
  private String nome; 
  private String cognome; 
  private int eta; 
  private int id; 
  // Quando referenziamo un enumatore dobbiamo utilizzare il nome dell'enumeratore
  private Sesso sesso;   
  // La mappa serve a fare delle associazioni chiave -> valore per ottenere i dati nella mappa tramite una chiave;
  private Map<Integer, ContoCorrente> conti;
  // Costruttore della classe Cliente
  public Cliente ( int id, String nome, String cognome, int eta, Sesso sesso ) {
    this.id = id;
    this.nome = nome;  
    this.cognome = cognome; 
    this.eta = eta;
    this.sesso = sesso; 
    conti = new HashMap<Integer,ContoCorrente>(); 
  }

  public void aggiungiConto ( ContoCorrente c ) {
     // Utilizzando la keyword this sto chiamando l'oggetto su cui sto lavorando in questo momento
    // c.setCliente(this); Codice da ricontrollare un attimo
    conti.put(c.getId(), c);
  }

  public ContoCorrente rimuoviConto ( int id_conto ) {
    ContoCorrente f = conti.get(id_conto);
    if ( f != null ) {
      f.setCliente(null);
      conti.remove(id_conto);
      // --->
      //  Rimuove la coppia chiave valore dalla mappa 
    }
    return f; //  Ritorna anche il conto corrente eliminato
  }

  @Override // Stiamo 'sovrascrivendo' il metodo toString() della classe Object per ritornare una stringa di nostro interesse 
  public String toString() {
    StringBuilder sb = new StringBuilder(); 
    /*
    Creiamo uno StringBuilder per creare la nostra stringa
    Tramite 'sb.append()' possiamo aggiungere contenuto alla nostra stringa
    */
    sb.append(" ID Cliente: ").append(id)
    .append(" Nome Cliente: ").append(nome)
    .append(" Cognome Cliente: ").append(cognome)
    .append(System.lineSeparator());
    //Cicliamo la Mappa dei Conti per ottenerne i dati
    for ( ContoCorrente c : conti.values() ) {
      sb.append(" ID Conto: ").append(c.getId())
      .append(" Saldo Conto: ").append(c.getSaldo()).append(System.lineSeparator());
    }
    // Questo è anche un esempio di method chaining, ossia un metodo che permette di richiamare lo stesso oggetto su cui stiamo lavorando, in questo caso lo stringbuilder
    return sb.toString();
    // Ritorno al Stringa contenuta nello String builder
  }

	public int getId() {
		return id;
	}
	
	
	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public Map<Integer, ContoCorrente> getConti() {
		return conti;
	}

	public ContoCorrente getContoById(int id) {
		return conti.get(id);
	}




}