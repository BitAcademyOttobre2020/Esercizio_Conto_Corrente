package banca.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import banca.domain.Banca;
import banca.domain.Cliente;
import banca.domain.ContoCorrente;
import banca.domain.DatiOperazione;
import banca.domain.ReportCliente;
import banca.domain.exception.SaldoInsufficenteException;

public class BankInterface {

	public static void main(String[] args) {

		Banca b = Banca.getInstance();
		Scanner sc = new Scanner(System.in);
		boolean t = true;
		// stampiamo tutti i conti clienti
		Iterable<Cliente> ic = b.getClienti();
		while (t) {
			for (Cliente c : ic) {
				System.out.println(c);
			}
			List <Cliente> cc = new ArrayList<Cliente>();
			ic.forEach(cc::add);
			List <ReportCliente> lista = cc.stream()
					.map(c -> new ReportCliente(""+c.getNome()+" "+c.getCognome() 
					,c.getConti().size() , c.getConti().values().stream()
					.mapToDouble(s -> s.getSaldo()).sum()))
					.collect(Collectors.toList());
			lista.forEach(System.out::println);
			System.out.println("Cosa vuoi fare: \n 0.termina, \n 1.preleva, \n 2.deposita, \n 3.bonifico");
			int n = sc.nextInt();
			try {
				switch (n) {
				case 0: {
					t=false;
					break;
				}
				case 1: {
					DatiOperazione dati = acquisisciOperazioneSingola(sc);
					b.preleva(dati.amount, dati.idConto, dati.idCliente);
					break;
				}
				case 2: {
					DatiOperazione dati = acquisisciOperazioneSingola(sc);
					b.deposita(dati.amount, dati.idConto, dati.idCliente);
					break;
				}
				case 3: {
					DatiOperazione dati = acquisisciOperazioneDoppia(sc);
					b.bonifica(dati.amount, dati.idConto, dati.idCliente, dati.idContoDest, dati.idClienteDest);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + n);
				}
			}catch(SaldoInsufficenteException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public static DatiOperazione acquisisciOperazioneSingola(Scanner sc) {
		DatiOperazione dati = new DatiOperazione();
		System.out.println("Inserisci Id cliente");
		dati.idCliente = sc.nextInt();
		System.out.println("Inserisci Id conto");
		dati.idConto = sc.nextInt();
		System.out.println("Quanto vuoi prelevare?");
		dati.amount = sc.nextInt();
		return dati;
	}

	public static DatiOperazione acquisisciOperazioneDoppia(Scanner sc) {
		DatiOperazione dati = acquisisciOperazioneSingola(sc);
		System.out.println("Su quale conto?");
		dati.idContoDest = sc.nextInt();
		System.out.println("A quale cliente?");
		dati.idClienteDest = sc.nextInt();
		return dati;
	}
}
