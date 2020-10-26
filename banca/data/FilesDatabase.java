package banca.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import banca.domain.Cliente;
import banca.domain.Impiegato;
import banca.domain.Sesso;

public class FilesDatabase implements DatabaseInterface {

	@Override
	public Iterable<Cliente> getAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Impiegato> getAllImpiegati() {

		Stream<String> streamList;
		List<Impiegato> listaImpiegati = new ArrayList<Impiegato>();
		
		try {
			streamList = Files.lines(Paths.get("/Users/kmaster/git/Esercizio_Conto_Corrente/banca/domain/Impiegati.txt"));
			listaImpiegati = streamList.map(s -> s.split(" ")).map(campo -> new Impiegato(campo[0], campo[1], Double.parseDouble(campo[2]),
					LocalDate.parse(campo[4]), Sesso.valueOf(campo[3]))).collect(Collectors.toList());
			
			listaImpiegati.forEach(System.out::println);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaImpiegati;
	}

	
	
}
