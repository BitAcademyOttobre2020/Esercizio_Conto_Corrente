package banca.domain;

import java.util.ArrayList;
import java.util.List;

import banca.data.DatabaseInterface;
import banca.data.FilesDatabase;
import banca.data.InMemoryDatabase;
import banca.domain.exception.SaldoInsufficenteException;

public class Banca {
			
	//singleton 1 solo oggetto istanziato nella classe
	
	private static Banca instance = new Banca();
	private String nome = "Bank of Java";
	private String[] codiciSegreti = {"adfhfda","asdafaf","zxcxv"};
		
	private DatabaseInterface database = new InMemoryDatabase();
	private DatabaseInterface fileDatabase = new FilesDatabase();
	
	private Banca() {
	}
	
	public Iterable<Impiegato> getImpiegato(){
		return fileDatabase.getAllImpiegati();
	}
	
	public Iterable<Cliente> getClienti() {
		return database.getAllClients();
	}

	public static Banca getInstance() {
		return instance;
	}
	
	public boolean verificaCodice(String codiceSegreto) {
		
		for(String codice : codiciSegreti) {
			if (codiceSegreto.equals(codice)) {
				return true;
			}
		}
	return false;
	}
	
	public void preleva(double amount, int idConto, int idCliente) throws SaldoInsufficenteException {
		for(Cliente cliente : database.getAllClients()) {
			int id = cliente.getId();
			if(idCliente == id) {
				ContoCorrente x = cliente.getContoById(idConto);
				x.preleva(amount);
				return;
			}
		}
		
	}
	
	public void deposita(double deposito, int idConto, int idCliente) {
		for(Cliente cliente : database.getAllClients()) {
			int id = cliente.getId();
			if(idCliente == id) {
				ContoCorrente x = cliente.getContoById(idConto);
				
				x.deposita(deposito);
				return;
			}
		}
		
	}
	
	public void bonifica(double bonifico, int idContoSorgente, int idClienteSorgente,
			int idContoDestinatario, int idClienteDestinatario) throws SaldoInsufficenteException {
		
		Cliente sorgente = null;
		Cliente destinatario = null;
		
		for(Cliente c : database.getAllClients()) {
			int id = c.getId();
			if(idClienteSorgente == id) {
				sorgente=c;
// andrebbe nell'oggetto e modificherebbe sorgente
				//c.setName("Paolo")
			}else if(idClienteDestinatario == id){
				destinatario=c;
			}
		}
		sorgente.getContoById(idContoSorgente)
			.bonifica(bonifico, destinatario.getContoById(idContoDestinatario));		
	}

	
	
	
}
