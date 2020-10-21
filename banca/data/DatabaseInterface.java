package banca.data;

import banca.domain.Cliente;

public interface DatabaseInterface {

	public Iterable<Cliente> getAllClients();

	
}
