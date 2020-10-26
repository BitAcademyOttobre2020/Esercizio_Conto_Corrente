package banca.data;

import banca.domain.*;

public interface DatabaseInterface {

	public Iterable<Cliente> getAllClients();

	public Iterable<Impiegato> getAllImpiegati();
}
